/**
 * 
 */
package com.shitx.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author shitx
 *
 */
public class ProxyHandler implements InvocationHandler,MyProxy {
	/**
	 * @param sbl
	 */
	private Object proxid; 
	
	public ProxyHandler(Object proxid) {
		this.proxid = proxid;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		this.before();
		Object retValue = method.invoke(proxid, args);  //这里是proxid，不要用proxy，proxid其实是之前构造函数里实际传进来的对象，也就是被代理的对象
		if(null != retValue){
			System.out.println("after invoke method.." + retValue.toString());
		}else{
			System.out.println("after invoke method..");
		}
		this.after();
		return retValue;
	}

	public void before() {
		System.out.println("Before, proxid obj hashcode is :" + this.proxid.hashCode() + "  " + "do some initilize work...");
	}

	public void after() {
		System.out.println("After, proxid obj hashcode is :" + this.proxid.hashCode() + "  " + "do some destroy work...");
	}

}
