/**
 * 
 */
package com.shitx.concurrent.notify;

/**
 * @author shitx
 *
 */
public interface MyQueue {

	Object take();

	void put(Object data);

}