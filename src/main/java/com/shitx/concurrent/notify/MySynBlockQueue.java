/**
 * 
 */
package com.shitx.concurrent.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shitx
 *
 */
public class MySynBlockQueue implements MyQueue {
	private static Logger logger = LoggerFactory.getLogger(MySynBlockQueue.class);
	// 竞争该锁
	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	private final Object[] items = new Object[2]; // 设置缓存队列的长度
	private volatile int putPos = 0;
	private volatile int takePos = 0;
	private volatile int count = 0;

	public Object take() {
		logger.info("take" + Thread.currentThread().getName());
		Object x =null;
		synchronized (lock) {
			while (count == 0) {
				try {
					logger.info("take count="+count);
					lock.wait();
				} catch (InterruptedException e) {
					logger.info(e.getMessage()+e);
				}
			}
			x = items[takePos++];
			if (takePos == items.length) {
				takePos = 0;
			}
			count--;
			lock.notify(); 
		}
		return x;
	}

	public void put(Object data) {
		logger.info("put" + Thread.currentThread().getName());
		synchronized (lock) {
				while (count == items.length) {
					try {
						logger.info("put count="+count);
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				items[putPos++] = data;
				if (putPos == items.length) {
					putPos = 0;
				}
				count ++;
				lock.notify();  //如果不唤醒，极端情况，大家都在wait状态，那么就会造成死锁
			}
		}
}
