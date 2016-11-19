/**
 * 
 */
package com.shitx.seckill.enums;

/**
 * @author shitx
 * @date 2016年11月19日
 * 使用枚举来表述常量数据类型   强烈建议将  数据字典放到枚举！！！  多出使用，可读性增强
 */
public enum SecKillStatEnum {
	SUCCESS(1,"秒杀成功"),
	END(0,"秒杀结束"),
	REPEAT_KILL(-1,"重复秒杀"),
	INNER_ERROR(-2,"系统异常"),
	DATE_REWRITE(-3,"数据篡改");

	private int state;

	private String stateInfo;
	
	public static SecKillStatEnum stateOf(int index){
		for(SecKillStatEnum state : values()){
			if(state.getState() == index){
				return state;
			}
		}
		return null;
	}
	
	
	/**
	 * @param state
	 * @param stateInfo
	 */
	private SecKillStatEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
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
	
	
}
