/**
 * 
 */
package com.shitx.aop.aspectj.biz;

import org.springframework.stereotype.Service;

/**
 * @author shitx
 * 业务类
 */

@Service
public class ShitxBiz {
	public String save(String arg){
		System.out.println("shitxBiz save:" + arg);
		throw new RuntimeException("save failed");
//		return "save success!";
	}
}
