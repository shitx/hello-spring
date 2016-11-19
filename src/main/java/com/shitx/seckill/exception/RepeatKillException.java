/**
 * 
 */
package com.shitx.seckill.exception;

/**
 * @author shitx
 * 必须继承运行期异常，而不是编译异常。  因为事务是只能回滚运行期异常的
 */
public class RepeatKillException extends SecKillException{

	/**
	 * @param message
	 * @param cause
	 */
	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public RepeatKillException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
