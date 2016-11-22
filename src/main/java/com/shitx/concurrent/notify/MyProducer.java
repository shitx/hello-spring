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
public class MyProducer implements Runnable{
	private static  Logger logger = LoggerFactory.getLogger(MyProducer.class);
	
	private MyQueue queue;
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		int cnt = 0;
		while(true){
			String task = Thread.currentThread().getName() + " task:" + cnt;
			cnt ++;
			queue.put(task); //阻塞
			logger.info(task);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
	}

	/**
	 * @param queue
	 */
	public MyProducer(MyQueue queue) {
		super();
		this.queue = queue;
	}

}
