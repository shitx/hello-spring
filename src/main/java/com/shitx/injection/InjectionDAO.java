/**
 * 
 */
package com.shitx.injection;

import org.springframework.stereotype.Component;

/**
 * @author shitx
 *
 */

@Component
public class InjectionDAO {
	public void save(String str){
		System.out.println("Dava saved:"+str);
	}
}
