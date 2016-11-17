/**
 * 
 */
package com.shitx.configurationbean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author shitx
 *
 */
public class StringStore implements Store<String> {

	
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("This  init");
	}



	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("This is destroy");
	}

}
