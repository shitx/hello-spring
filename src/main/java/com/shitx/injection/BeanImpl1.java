/**
 * 
 */
package com.shitx.injection;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author shitx
 *
 */

@Order(value = 2)  //Order数组只针对List注解有效【我们希望它有效】 对map无效，因为map本身是有hash的
@Component
public class BeanImpl1 implements BeanInterface {

	/* (non-Javadoc)
	 * @see com.shitx.injection.BeanInterface#say()
	 */


}
