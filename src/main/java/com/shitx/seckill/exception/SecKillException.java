/**
 * 
 */
package com.shitx.seckill.exception;

/**
 * 
 * 秒杀相关业务异常。类似所有业务的父异常。都可以抛出它
 * @author shitx
 *
 */
public class SecKillException extends RuntimeException{

	/**
	 * @param message
	 * @param cause
	 */
	public SecKillException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public SecKillException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
