/**
 * 
 */
package com.shitx.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author shitx
 *
 */
public class ShitxApplicationContxtAware implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//再此自动注入了application-context,通过它能访问到容器内所有的上下文信息[aware 提供了可以直接访问上下文信息的接口]
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
		//System.out.println(applicationContext.getBean("shitxApplicationContextAware").hashCode());
	}
	
	public void a(){
		//通过aware方式自动注入了applicationContext,通过它就可以获取到容器的所有东西
		this.applicationContext.containsBean("xxx");
	}
}
