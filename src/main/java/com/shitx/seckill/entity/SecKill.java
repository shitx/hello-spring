/**
 * 
 */
package com.shitx.seckill.entity;

import java.util.Date;

/**
 * @author shitx * 
 * 实体类其实对应了数据库中的表的1记录
 *
 */
public class SecKill {
	private long secKillId;

	private String name;
	
	private int number;
	
	private Date startTime;
	
	private Date endTime;
	
	private Date createTime;

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

	@Override
	public String toString() {
		return "SecKill [seckilled=" + secKillId + ", name=" + name + ", number=" + number + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createTime=" + createTime + "]";
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



}
