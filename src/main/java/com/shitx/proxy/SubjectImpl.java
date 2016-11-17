/**
 * 
 */
package com.shitx.proxy;

/**
 * @author shitx
 *
 */
public class SubjectImpl implements Subject {

	/* (non-Javadoc)
	 * @see com.shitx.proxy.Subject#say()
	 */
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("I am :" + this.getClass().getName());
	}
}
