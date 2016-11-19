/**
 * 
 */
package com.shitx.seckill.entity;

import java.util.Date;

/**
 * @author shitx
 * 成功秒杀表的明细记录
 */
public class SuccessKilled {
	private short state;
	
	private Date createTime;
	
	private long secKillId;
	
	private long userPhone;
	
	//多对1，因为已给secKill多条成功记录
	private SecKill secKill;

	/**
	 * @return the secKill
	 */
	public SecKill getSecKill() {
		return secKill;
	}

	/**
	 * @param secKill the secKill to set
	 */
	public void setSecKill(SecKill secKill) {
		this.secKill = secKill;
	}

	@Override
	public String toString() {
		return "SuccessKilled [secKillId=" + secKillId + ", userPhone=" + userPhone + ", state=" + state
				+ ", createTime=" + createTime + ", secKill=" + secKill.toString() + "]";
	}

	/**
	 * @return the secKillId
	 */
	public long getSecKillId() {
		return secKillId;
	}

	/**
	 * @param secKillId the secKillId to set
	 */
	public void setSecKillId(long secKillId) {
		this.secKillId = secKillId;
	}

	/**
	 * @return the userPhone
	 */
	public long getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * @return the state
	 */
	public short getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(short state) {
		this.state = state;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
