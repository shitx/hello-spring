/**
 * 
 */
package com.shitx.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.util.logging.resources.logging;

/**
 * @author shitx
 *
 */
public class Actor extends Thread{
	private static  Logger logger = LoggerFactory.getLogger(Actor.class);
	
	public void run(){
		logger.info(getName() + "是一个演员");
		int count = 0;
		boolean keepRunning = true;
		while(keepRunning){
			logger.info(getName() + "登台演出：" + (++count));
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
		logger.info(getName()+"的演出结束了");
	}
	
	public static void main(String args[]){
		Thread actor = new Actor();
		actor.setName("Mr.Thread");
		actor.start();
		
		Thread actress = new Thread(new Actress());
		actress.setName("Ms.Thread");
		actress.start();
	}
}
