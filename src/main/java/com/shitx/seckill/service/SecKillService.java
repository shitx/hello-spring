/**
 * 
 */
package com.shitx.seckill.service;

import java.util.List;

import com.shitx.seckill.dto.Exposer;
import com.shitx.seckill.dto.SecKillExecution;
import com.shitx.seckill.entity.SecKill;
import com.shitx.seckill.exception.RepeatKillException;
import com.shitx.seckill.exception.SecKillCloseException;
import com.shitx.seckill.exception.SecKillException;

/**
 *  业务接口：站在“使用者”的角度来设计接口
 *  三个方面：  方法定义粒度，参数，返回类型（return 类型友好/异常）
 * @author shitx
 *
 */
public interface SecKillService {
	/**
	 * 查询所有的秒杀记录
	 * @return
	 */
	public List<SecKill> getSecKillList();
	
	/**
	 *  查询单个秒杀记录
	 * @param secKillId
	 * @return
	 */
	SecKill getById(long secKillId);
	
	/**
	 * 秒杀开启输出秒杀接口地址，否则输出系统时间和秒杀时间
	 * @param secKillId
	 */
	Exposer exportSecKillUrl(long secKillId);
	
	/**
	 * 执行秒杀操作.返回结果应该是封装好的对象，对象里有状态信息，以及秒杀到的产品.
	 * 抛出的异常定义在接口这里，是告诉接口的使用者：可能回抛出这些异常。
	 * @param secKillId
	 * @param userPhone
	 * @param md5
	 */
	SecKillExecution executeSecKill(long secKillId, long userPhone, String md5)
	throws SecKillException, SecKillCloseException, RepeatKillException;
}
