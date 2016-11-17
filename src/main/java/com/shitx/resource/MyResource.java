/**
 * 
 */
package com.shitx.resource;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

/**
 * @author shitx
 *
 */
public class MyResource implements ApplicationContextAware {
	ApplicationContext applicationContext;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}
	
	public void getResourceByClasspath() throws IOException{
		Resource  resource = applicationContext.getResource("classpath:config.txt");
		System.out.println(resource.getFilename());
		System.out.println(resource.contentLength());
	}
	
	public void getResourceByFilepath() throws IOException{
		Resource  resource = applicationContext.getResource("file:E:\\workspace\\eclipse\\hello-spring\\src\\test\\resources\\config.txt");
		System.out.println(resource.getFilename());
		System.out.println(resource.contentLength());
	}
	
	public void getResourceByUrl() throws IOException{
		Resource  resource = applicationContext.getResource("url:http://test.qutke.com/charts/57bd82f55ddee488e5ac79e0");
		System.out.println(resource.getFilename());
		System.out.println(resource.contentLength());
	}
	
	public void getResourceByDefault() throws IOException{
		Resource  resource = applicationContext.getResource("config.txt");//没有前缀时依赖于applicationContext的创建环境，这里applicationContext是通过单元测试内的UnitTestBase传进来的参数（是通过classpath:）
		System.out.println(resource.getFilename());
		System.out.println(resource.contentLength());
	}

	/**
	 * @return the applicationContext，一般不需要get方法
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
