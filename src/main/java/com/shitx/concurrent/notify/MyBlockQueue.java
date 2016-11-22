/**
 * 
 */
package com.shitx.concurrent.notify;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shitx
 *
 *  synchronized和lock区别
 *  1  最大的区别：synchronized不可以中断。如果A得到了O的锁，B在等待O的锁，B使用Synchronized方法获取O的锁，那么A不释放，则B永远等待。如果采用Lock,我们可以通过调用O.lockInterruptibly()表示它是可以被中断的，不会无穷等待
 *    【这里要掌握lock的好几种方式,lock, trylock trylock(time,unit)】
 *   2 synchronized是在 JVM层面实现，在代码执行出现异常或者代码执行完毕，自动释放锁！  lock则必须在使用finally里 unlock
 *   3 在竞争不激烈时，可以无穷等待（无需中断等待）可采用synchronized，竞争激烈时，lock的性能依然很好，所以建议采用。
 *   4 lock的释放必须由程序完成。  synchronized正常执行由线程释放，否则由jvm释放。
 *   5 synchronized是java关键字，lock是一个普通类。
 *   6 synchronized是非公平的，且不可修改。 而lock ,readwritelock都可以设置为公平【默认非公平】。
 *   7 二者都是可以重入的，比如C,D2个方法都有synchronized修饰，如果不是可重入会导致死锁。
 *   
 *   ReentrantLock：【最常用的锁实现类】互斥
 *  1 可被中断[这个其实也是OBJECT类带的方法特性]
 *  
 *  读写锁
 *  读是非互斥，写互斥
 *  
 *  自旋锁
 *  不挂起当前线程，而是使用该时间片，轮询锁状态。适合于cpu是多核，且等待该锁的时间短，若干个时钟周期，比采用挂起方式【非自旋】上下文切换的代价更小，则可以采用。否则还是让出cpu时间片，等待信号来唤醒。
 *  lock readwritelock，synchronized都是非自旋
 */
public class MyBlockQueue implements MyQueue {
	private static Logger logger = LoggerFactory.getLogger(MyBlockQueue.class);
	// 竞争该锁
	private Lock lock = new ReentrantLock(true);
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	private final Object[] items = new Object[100]; // 设置缓存队列的长度
	private volatile int putPos = 0;
	private volatile int takePos = 0;
	private volatile int count = 0;


	@Override
	public Object take() {
		logger.info("take" + Thread.currentThread().getName());
		Object x =null;

		lock.lock();  //[1]在使用条件变量之前，或者锁的等待之前，必须将其锁住，或者将其包含在synchronized。这样才能安全使用。【临界区，等待区的概念】  
//		try {   //[2]锁的另外一种等待方式，可被中断。但是只有在等待锁的过程才可以被中断，获取到锁以后就无法被中断了。
//			lock.lockInterruptibly();
//		} catch (InterruptedException e1) {
//			logger.info("i do not want to wait any more, returning");
//			return null;
//		}
//		boolean ok = lock.tryLock(); //【3】 如果锁空闲，立刻获取并返回true，否则立刻返回false。 不等待
//		try {
//			lock.tryLock(1000, TimeUnit.MILLISECONDS);  //【4】获取锁时加上一个时间限制。
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try {
			while (count == 0) {
//				lock.lock(); //重复获取锁是没有意义的
//				notEmpty.wait(); //在条件变量上wait会报错。具体错误是在调用notEmpty对象的wait方法之前，并没有获取到它的monitor。
				notEmpty.await(); //在被唤醒时，会接着这个断点继续执行。所以接下来还是到判断count是否为0   Object采用 wait notify notifyAll  Condition 采用 await signal signalAll实现对线程更加精细的控制。
				//比如这里put take分开，如果采用object的方法则无法实现区分，只能一次锁住全部  一次唤醒全部，不区分具体的线程。
				//所以这里建议采用条件变量方式，而不是采用OBJECT的方法。   此外这里也不采用synchronized，方便被中断
			}
			x = items[takePos++];
			if (takePos == items.length) {
				takePos = 0;
			}
			count--;
			notFull.signal();
		} catch (Exception e) {
			logger.error(e.getMessage() + e);
		} finally {
			lock.unlock();
		}
		return x;
	}

	/* (non-Javadoc)
	 * @see com.shitx.concurrent.notify.MyQueue#put(java.lang.Object)
	 */
	@Override
	public void put(Object data) {
		logger.info("put" + Thread.currentThread().getName());
		lock.lock();
		try {
			while (count == items.length) {
				notFull.await();
			}
			items[putPos++] = data;
			if (putPos == items.length) {
				putPos = 0;
			}
			count ++;
			notEmpty.signal();
		} catch (Exception e) {
			logger.error(e.getMessage() + e);
		} finally {
			lock.unlock();
		}

	}
}
