/**
 * 
 */
package com.shitx.concurrent.notify;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量完成线程之间同步
 * @author shitx
 *
 */
public class MySemaphore {


	public static void main(String args[]){
		ExecutorService pool = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(3,true); //final只读，不可以被修改

		for(int i = 0; i <2; i++){
			Runnable runnable = new Runnable() {
				@SuppressWarnings("static-access")
				public void run() {
						try {
							semaphore.acquire(); //等待，可被中断.  注意信号量不可以小于0.  同时可以多次获取信号量
//							semaphore.acquire();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Thread "+Thread.currentThread().getName()+" 进入" +"当前系统的并发数是："+(3-semaphore.availablePermits()));
						try {
							Thread.currentThread().sleep(new Random().nextInt(1000));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Thread "+Thread.currentThread().getName()+" 即将离开");
						semaphore.release();//释放信号。可以多次释放，即使该线程没有获取过信号
//						semaphore.release();
					    System.out.println("Thread "+Thread.currentThread().getName()+" 已经离开，当前系统的并发数是："+(3-semaphore.availablePermits()));
				}
			};
			pool.execute(runnable);
		}
	}
}
