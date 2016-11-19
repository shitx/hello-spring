/**
 * 
 */
package com.shitx.seckill.exception;

/**
 * 
 * 秒杀关闭异常。秒杀关闭后如果还执行则报该异常，秒杀关闭可能是因为没有商品了，或者时间到了或者时间每到
 * @author shitx
 *
 */
public class SecKillCloseException extends SecKillException{

	/**
	 * @param message
	 * @param cause
	 */
	public SecKillCloseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public SecKillCloseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
