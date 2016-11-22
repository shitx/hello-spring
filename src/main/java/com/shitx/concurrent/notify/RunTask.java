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
public class RunTask {
	private static  Logger logger = LoggerFactory.getLogger(RunTask.class);
	
	@SuppressWarnings("null")
	public static void main(String args[]){
		
		MyQueue queue = new MyBlockQueue();
		int producesCnt = 1;
		int consumersCnt = 1;
		for(int i = 0; i < producesCnt; i ++){
			Thread th = new Thread(new MyProducer(queue));
			th.setName("producer"+ i);
			th.start();
			logger.info(th.getName()+ "started");
		}
		for(int i = 0; i < consumersCnt; i ++){
			Thread th = new Thread(new MyConsumer(queue));
			th.setName("consumer"+ i);
			th.start();
			logger.info(th.getName()+ "started");
		}
	}
}
