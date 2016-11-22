/**
 * 
 */
package com.shitx.concurrent.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shitx.concurrent.Actor;

import sun.net.www.http.KeepAliveCache;

/**
 * @author shitx
 *
 */
public class ArmyRunnable implements Runnable {
	private static  Logger logger = LoggerFactory.getLogger(ArmyRunnable.class);
	
	//volatile保证了线程可以正确读取其他线程写入的值
    protected volatile boolean keepRunning= true ;
	
	@Override
	public void run() {
		while(keepRunning){
			//5连击
			for(int i = 0; i < 5; i ++){
				logger.info(Thread.currentThread().getName() + "进攻对方["+i+"]");
				//让出处理器时间，重新开始竞争资源
			}
			Thread.yield();
		}

	}

}
