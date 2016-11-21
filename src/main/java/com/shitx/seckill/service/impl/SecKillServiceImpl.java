/**
 * 
 */
package com.shitx.seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  //日志以后统一用slf4j
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import com.shitx.seckill.service.SecKillService;
import com.sun.org.apache.regexp.internal.RE;
import com.shitx.seckill.dao.SecKillDao;
import com.shitx.seckill.dao.SuccessKilledDao;
import com.shitx.seckill.dao.cache.RedisDao;
import com.shitx.seckill.dto.Exposer;
import com.shitx.seckill.dto.SecKillExecution;
import com.shitx.seckill.entity.SecKill;
import com.shitx.seckill.entity.SuccessKilled;
import com.shitx.seckill.enums.SecKillStatEnum;
import com.shitx.seckill.exception.RepeatKillException;
import com.shitx.seckill.exception.SecKillCloseException;
import com.shitx.seckill.exception.SecKillException;

/**
 * @author shitx
 *   注解类型   
 *   #Componet  组件  如果不清楚它到底是什么，那么就用它
 *   #Service   服务
 *   #Dao
 *   #Contoller
 */

@Service
public class SecKillServiceImpl implements SecKillService {
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	private RedisDao redisDao;
	
	//自动注入依赖
	@Autowired  //@Resource @Inject 【j2ee标准】
	private SecKillDao secKillDao;

	@Autowired
	private SuccessKilledDao successKilledDao;
	
	//用来进行md5混淆
	private final String salt = "sgasgjagj$85u836u363";

	public List<SecKill> getSecKillList() {
		return secKillDao.queryAll(0, 4);
	}

	public SecKill getById(Long secKillId) {
		return secKillDao.queryById(secKillId);
	}


	public Exposer exportSecKillUrl(Long secKillId) {
		/**
		 * 秒杀接口暴露【存在很多用户同时访问restful服务器，tomcat服务器会不断去请求mysql，实际上我们可以把数据缓存在tomcat容器所在的机器】
		 * 优化方式：缓存优化
		 * 先从缓存获取，如果没有则读数据库
		 */
		//从缓存获取
		SecKill secKill = redisDao.getSecKill(secKillId);
		if(secKill == null){
			//2访问数据库
			secKill = secKillDao.queryById(secKillId);
			if(secKill == null){
				return new Exposer(false, secKillId);
			}else{
				//3放入缓存
				redisDao.putSecKill(secKill);
			}
		}
		
		Date startTime = secKill.getStartTime();
		Date endTime = secKill.getEndTime();
		Date nowTime = new Date();
		
		if(nowTime.getTime() < startTime.getTime()
				|| nowTime.getTime() > endTime.getTime()){
			return new Exposer(false, secKillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		//转化特定字符串的过程，不可逆
		String md5 = getMD5(secKillId);
		return new Exposer(true, md5, secKillId);  //把md5和id都给了用户。用户如果每次都必须带这id和密码来请求渺少、
		//如果只带了ID,或者带了ID和错误的MD5,我们认为它是非法请求
	}

	private String getMD5(Long secKillId){
		String base = secKillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	
	@Transactional
	/**
	 *  使用注解控制事务方法的优点：
	 *  1 开发团队达成一致的约定，明确标注事务方法的编程风格
	 *  2 保证事务方法的执行时间尽可能短，不要穿插其他的网络操作（比如操作缓存，http/RPC,如果一定要做这些网络操作，把这些操作放到新的方法中，不要开启事务）
	 *  3 不是所有的方法都需要开启事务。一旦别人的规范不一定，那么导致不需要的地方也被动加了事务。如果是只读，或者只有一条需要修改，那么不要开启事务！
	 */
	public SecKillExecution executeSecKill(Long secKillId, Long userPhone, String md5)
			throws SecKillException, SecKillCloseException, RepeatKillException {
		//最重要的方法，执行秒杀
		//判断用户输入的ID和MD5是否对应上
		if(md5 == null || !md5.equals(getMD5(secKillId))){
			throw new SecKillException("seckill data rewrite");
		}
		//执行秒杀逻辑：减库存，并记录购买行为
		Date nowTime = new Date();
		
		try {
			//【优化1】调整顺序，先插入记录，然后再减库存，插入明细的失败可能性比较小，而且能阻挡一部分重复秒杀.
			// 先减库存，后插入明细，整个过程都要获取库存的行锁！     如果先插入明细，则不需要获取行锁，只有再减库存要获取。  第一种方法行锁锁住时间是2（TLL + x *GC）,第二种为（TTL + x * GC）.
			// 当然对单个秒杀事件而言，总的时间是 2(TTL +x * GC)。只不过是行锁时间长度不一样，导致并发量不同。  注意看到这里第二种行锁锁住时间至少也是（TTL + x * GC）,原因是因为行锁申请，事务控制在客户端（TOMCAT）
			// 发起，如果把行锁申请和事务开启结束操作都以存储过程的形式在数据库端执行。则行锁加锁的时间几乎为0【mysql本身并发很快，主要瓶颈在网络延迟】。这是【优化2：深度优化】
			// 其实就是插入明细可以并发执行。  而减库存则无法在行锁上并发。   注：行锁是在要执行和该行有关操作时获取，在事务提交时释放。因此要在合适的位置放置要得到行锁的代码，在事务种靠后比较好 
			//记录购买行为
			int insertCount = successKilledDao.insertSuccessKilled(secKillId, userPhone);
			//唯一，如果重复插入，则回被忽略，返回0
			if(insertCount <= 0){
				throw new RepeatKillException("seckill repeated");
			}else{
				//减去库存，热点商品竞争
				int updateCount = secKillDao.reduceNumber(secKillId, nowTime);
				if(updateCount <= 0){ //rollback
					//没有更新记录,秒杀结束【可能是库存结束，或者是时间结束】
					throw new SecKillCloseException("seckill is closed");
				}else{
					//秒杀成功  commit
					SuccessKilled successKilled = successKilledDao.queryByIdWithSecKill(secKillId,userPhone);
					return new SecKillExecution(secKillId, SecKillStatEnum.SUCCESS, successKilled);
				}
			}
		}catch(SecKillCloseException e1){  //e1,e2的逻辑是我们自己定义的，当然不想被转化为统一的exception
			//否则对用户来说，就没有什么区别，不知道到底是哪个异常，所以拿到它就throw!
			throw e1;
		}catch (RepeatKillException e2) {
			throw e2;
		}catch (Exception e) { //Exception也是RuntimeException的父类，所以任何异常都回被这里catch
			logger.error(e.getMessage(), e);
			//所有的编译异常，转化为运行时异常
			throw new SecKillException("seckill inner error:" + e.getMessage());
		}
	}

	/**
	 * 通过使用储存过程，而不是spring aop事务管理来控制事务，减少行级锁持有时间，提高并发能力
	 */
	@Override
	public SecKillExecution executeSecKillProducedure(Long secKillId, Long userPhone, String md5) {
		if(md5 == null || !md5.equals(getMD5(secKillId))){
			return new SecKillExecution(secKillId, SecKillStatEnum.DATE_REWRITE);
		}
		Date killTime = new Date();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("secKillId", secKillId);
		map.put("phone", userPhone);
		map.put("killTime", killTime);
		map.put("result", null);
		try {
			secKillDao.killByProcedure(map);
			//获取result 采用common-connections包
			int result = MapUtils.getInteger(map, "result", -2);
			if(result == 1){
				SuccessKilled sk = successKilledDao.queryByIdWithSecKill(secKillId,userPhone);
				return new SecKillExecution(secKillId, SecKillStatEnum.SUCCESS);
			}else{
				return new SecKillExecution(secKillId, SecKillStatEnum.stateOf(result));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new SecKillExecution(secKillId, SecKillStatEnum.INNER_ERROR);
		}
	}
}
