/**
 * 
 */
package com.shitx.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author shitx
 *
 */
public class LifeCycle1 implements DisposableBean,InitializingBean {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("start1.");
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("stop1.");
	}

}
