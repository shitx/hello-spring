/**
 * 
 */
package com.shitx.concurrent.notify;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shitx
 *
 */
public class MyReadWriteLock {
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);

	//	public synchronized void task(){  //synchronized方式同步，在A线程进入方法后，B线程等待。A读完毕后B才能读。但是实际上A线程读的时候，B应该也是可以读的！所以下面有个利用读写锁来提高效率的
	//        long start = System.currentTimeMillis();
	//        while(System.currentTimeMillis() - start <= 1) {
	//            System.out.println(Thread.currentThread().getName()+"正在进行读操作");
	//        }
	//        System.out.println(Thread.currentThread().getName()+"读操作完毕");
	//	}

	@SuppressWarnings("static-access")
	public void readTask(){  
		readWriteLock.readLock().lock();  //获取读锁
		try {
			long start = System.currentTimeMillis();
			while(System.currentTimeMillis() - start <= 5) {
				System.out.println(Thread.currentThread().getName()+"正在进行读操作");  //读锁可以被很多的线程得到！
				//在读了以后要写到另外一个地方
//				readWriteLock.writeLock().lock();  //写锁只有在读锁和写锁读空的时候才可能被获取！  这么用会导致死锁
//				Thread.currentThread().yield(); //让出cpu，从运行变成就绪，重新和其他线程竞争读锁。此时依然可能是该线程获取到锁继续执行。
				try {
					readWriteLock.readLock().unlock(); 
					Thread.currentThread().sleep(100); //sleep方法不会释放得到的锁！所以如果在这种情况下，我们要自己释放。。。
					Thread.currentThread().yield(); //yield也不会释放锁资源，只是释放cpu资源，重新竞争
					readWriteLock.readLock().lock(); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+"读操作完毕");
		} finally {
			readWriteLock.readLock().unlock();  //读完毕后释放
		}
	}
	
	public void writeTask(){
		readWriteLock.writeLock().lock();
		try {
			long start = System.currentTimeMillis();
			while(System.currentTimeMillis() - start <=5) {
				System.out.println(Thread.currentThread().getName()+"正在进行写操作");  //写锁只有在读锁和写锁读空的时候才可能被获取
			}
			System.out.println(Thread.currentThread().getName()+"写操作完毕");
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	public static void main(String args[]){
		final MyReadWriteLock myReadWriteLock = new MyReadWriteLock();

		Thread t1 = new Thread(){
			public void run(){
				myReadWriteLock.readTask();
			}
		};
		t1.setName("thread1");
		t1.start();

		Thread t2 = new Thread(){
			public void run(){
				myReadWriteLock.readTask();
			}
		};
		t2.setName("thread2");
		t2.start();
		
		Thread t3 = new Thread(){
			public void run(){
				myReadWriteLock.writeTask();
			}
		};
		t3.setName("thread3");
		t3.start();
	}
}
