/**
 * 
 */
package com.shitx.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shitx
 *
 */
public class Actress implements Runnable {
	private static  Logger logger = LoggerFactory.getLogger(Actress.class);

	@Override
	public void run(){
		logger.info(Thread.currentThread().getName() + "是一个演员");
		int count = 0;
		boolean keepRunning = true;
		while(keepRunning){
			logger.info(Thread.currentThread().getName() + "登台演出：" + (++count));
			if(count == 100){
				keepRunning = false;
			}
			if(count % 10 == 0){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					logger.info(e.getMessage()+e);
				}
			}
		}
		logger.info(Thread.currentThread().getName()+"的演出结束了");
	}

}
