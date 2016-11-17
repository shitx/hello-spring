/**
 * 
 */
package com.shitx.injection;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author shitx
 *
 */
@Component
public class BeanInvoker {
	@Autowired
	private List<BeanInterface> list;
	
	@Autowired
	private Map<String,BeanInterface> map;
	
	@Autowired
	@Qualifier("beanImpl1") //在有多个bean满足自动装配时，该注解限制装配的bean,如果找不到满足的报错。相当于是过滤.  该种情况下，如果不加限定，会有多个bean满足，则报错
	private BeanInterface beanInterface;
	
	public void say(){
		System.out.println("list...........................");
		if(null != list){
			for(BeanInterface bean: list){
				System.out.println(bean.getClass().getName());
			}
		}else{
			System.out.println("bean list is null....");
		}
		
		System.out.println("map..........................");
		if(null != map && map.size() > 0){
			for(Map.Entry<String, BeanInterface> entry: map.entrySet()){
				System.out.println(entry.getKey() + "  " + entry.getValue().getClass().getName());
			}
		}else{
			System.out.println("bean map is null....");
		}
		
		System.out.println("bean qualifier test...");
		if(null != beanInterface){
			System.out.println(beanInterface.getClass().getName());
		}
		else{
			System.out.println("bean interface is null....");
		}
	}
}
