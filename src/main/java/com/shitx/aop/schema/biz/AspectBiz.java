/**
 * 
 */
package com.shitx.aop.schema.biz;

/**
 * @author shitx
 * 业务类
 */
public class AspectBiz {
	public void biz(){
		System.out.println("AspectBiz.biz ...");
//		throw new RuntimeException();
	}
	
	public void init(String bizName, int times){
		System.out.println("AspectBiz init:" + bizName + " " + times);
	}
}
