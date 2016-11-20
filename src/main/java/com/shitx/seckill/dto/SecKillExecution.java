/**
 * 
 */
package com.shitx.seckill.dto;

import com.shitx.seckill.entity.SuccessKilled;
import com.shitx.seckill.enums.SecKillStatEnum;

/**
 * 
 * 封装秒杀执行后的结果
 * @author shitx
 *
 */
public class SecKillExecution {
	private Long secKillId;
	
	//秒杀执行结果状态
	private int state;
	
	//状态标识
	private String stateInfo;
	
	private SuccessKilled successKilled;

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SecKillExecution [secKillId=" + secKillId + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", successKilled=" + successKilled + "]";
	}

	/**
	 * @param secKillId
	 * @param state
	 * @param stateInfo
	 */
	public SecKillExecution(Long secKillId, SecKillStatEnum statEnum) {
		super();
		this.secKillId = secKillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
	}

	/**
	 * @param secKillId
	 * @param state
	 * @param stateInfo
	 * @param successKilled
	 */
	public SecKillExecution(Long secKillId, SecKillStatEnum statEnum, SuccessKilled successKilled) {
		super();
		this.secKillId = secKillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	/**
	 * @return the secKillId
	 */
	public Long getSecKillId() {
		return secKillId;
	}

	/**
	 * @param secKillId the secKillId to set
	 */
	public void setSecKillId(Long secKillId) {
		this.secKillId = secKillId;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the stateInfo
	 */
	public String getStateInfo() {
		return stateInfo;
	}

	/**
	 * @param stateInfo the stateInfo to set
	 */
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	/**
	 * @return the successKilled
	 */
	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	/**
	 * @param successKilled the successKilled to set
	 */
	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	
	
}
