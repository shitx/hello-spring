/**
 * 
 */
package com.shitx.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author shitx
 *
 */
public class ShitxArrayBlockingQueue {
	private static BlockingQueue<User> queue = new ArrayBlockingQueue<User>(10);

	/**
	 * @return the queue
	 */
	public BlockingQueue<User> getQueue() {
		return queue;
	}
}
