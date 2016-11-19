/**
 * 
 */
package com.shitx.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  //日志以后统一用slf4j
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import com.shitx.seckill.service.SecKillService;
import com.shitx.seckill.dao.SecKillDao;
import com.shitx.seckill.dao.SuccessKilledDao;
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

	public SecKill getById(long secKillId) {
		return secKillDao.queryById(secKillId);
	}


	public Exposer exportSecKillUrl(long secKillId) {
		SecKill secKill = secKillDao.queryById(secKillId);
		if(secKill == null){
			//看出来封装返回数据的好处了，给使用者一个对象，人家根据对象去判断是否成功，而不是报个错
			return new Exposer(false, secKillId);
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

	private String getMD5(long secKillId){
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
	public SecKillExecution executeSecKill(long secKillId, long userPhone, String md5)
			throws SecKillException, SecKillCloseException, RepeatKillException {
		//最重要的方法，执行秒杀
		//判断用户输入的ID和MD5是否对应上
		if(md5 == null || !md5.equals(getMD5(secKillId))){
			throw new SecKillException("seckill data rewrite");
		}
		//执行秒杀逻辑：减库存，并记录购买行为
		Date nowTime = new Date();
		
		try {
			//减去库存
			int updateCount = secKillDao.reduceNumber(secKillId, nowTime);
			if(updateCount <= 0){
				//没有更新记录,秒杀结束【可能是库存结束，或者是时间结束】
				throw new SecKillCloseException("seckill is closed");
			}else{
				//记录购买行为
				int insertCount = successKilledDao.insertSuccessKilled(secKillId, userPhone);
				//唯一，如果重复插入，则回被忽略，返回0
				if(insertCount <= 0){
					throw new RepeatKillException("seckill repeated");
				}else{
					//秒杀成功
					SuccessKilled successKilled = successKilledDao.queryByIdWithSecKill(secKillId);
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
}
