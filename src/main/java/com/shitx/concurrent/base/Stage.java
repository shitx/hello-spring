/**
 * 
 */
package com.shitx.concurrent.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shitx
 *
 */
public class Stage extends Thread{
	private static  Logger logger = LoggerFactory.getLogger(Stage.class);
	
	public void run(){
		ArmyRunnable ArmyTaskOfSuiDynasty = new ArmyRunnable();
		ArmyRunnable ArmyTaskOfRevolt= new ArmyRunnable();
		
		Thread ArmyOfSuiDynasty = new Thread(ArmyTaskOfSuiDynasty,"隋军");
		Thread ArmyOfRevolt = new Thread(ArmyTaskOfRevolt,"农民");
		
		//启动线程
		ArmyOfSuiDynasty.start();
		ArmyOfRevolt.start();
		
		//stage线程休眠
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			logger.info(e.getMessage()+e);
		}
		
		ArmyTaskOfSuiDynasty.keepRunning = false;
		ArmyTaskOfRevolt.keepRunning = false;
		
		try {
			ArmyOfRevolt.join();
		} catch (Exception e) {
			logger.info(e.getMessage()+e);
		}
	}
	
	public static void main(String args[]){
		new Stage().start();
	}
}
