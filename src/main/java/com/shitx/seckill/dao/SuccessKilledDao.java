/**
 * 
 */
package com.shitx.seckill.dao;

import org.apache.ibatis.annotations.Param;

import com.shitx.seckill.entity.SuccessKilled;

/**
 * @author shitx
 *
 */
public interface SuccessKilledDao {
	
	/**
	 * 插入购买明细，可过滤重复[手机+秒杀号码唯一确定一个ID]
	 * @param secKillId
	 * @param userPhone
	 * @return 如果影响行数为>=1,表示插入记录的行数. 如果是0表示插入失败
	 */
	int insertSuccessKilled(@Param("secKillId") long secKillId, @Param("userPhone") long userPhone);
	
	/**
	 * 根据id查询SuccessKilled并携带秒杀产品的实体
	 * @param secKillId
	 * @return
	 */
	SuccessKilled queryByIdWithSecKill(long secKillId);
}
