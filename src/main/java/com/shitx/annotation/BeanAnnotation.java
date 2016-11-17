/**
 * 
 */
package com.shitx.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author shitx
 *
 */

@Scope("prototype")
@Component
//@Component("bean")
public class BeanAnnotation {
	
	public void say(String word){
		System.out.println("BeanAnnotation:" + word);
	}
	
	public void myHashCode(){
		System.out.println("hashcode:"+this.hashCode());
	}
}
