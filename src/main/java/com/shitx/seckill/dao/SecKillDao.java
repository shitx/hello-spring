/**
 * 
 */
package com.shitx.seckill.dao;

import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shitx.seckill.entity.SecKill;


/**
 * @author shitx
 *
 */
public interface SecKillDao {

	/**
	 * 减库存
	 * @param secKillId
	 * @param killTime
	 * @return 如果影响行数为>=1,表示更新记录的行数. 如果是0表示更新失败
	 */
	int reduceNumber(@Param("secKillId")long secKillId, @Param("killTime") Date killTime);
	
	/**
	 * 根据ID查询秒杀的记录
	 * @param secKillId
	 * @return
	 */
	SecKill queryById(long secKillId);
	
	/**
	 * 查询所有的秒杀商品
	 * * 注意这里多个参数时，如果不加Param注解，将变成arg0,arg1，mybatis将无法识别
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<SecKill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * 使用存储过程执行秒杀
	 * @param paramMap
	 */
	void killByProcedure(Map<String, Object> paramMap);
}
