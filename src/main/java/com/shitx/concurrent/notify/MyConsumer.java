/**
 * 
 */
package com.shitx.concurrent.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shitx
 *
 */
public class MyConsumer implements Runnable{
	private static  Logger logger = LoggerFactory.getLogger(MyConsumer.class);
	
	private MyQueue queue;
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(true){
			Object task = queue.take(); //阻塞
			String info = Thread.currentThread().getName() + " do task:"  + task.toString();
			logger.info(info);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
	}

	/**
	 * @param queue
	 */
	public MyConsumer(MyQueue queue) {
		super();
		this.queue = queue;
	}

}
