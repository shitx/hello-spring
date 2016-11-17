/**
 * 
 */
package com.shitx.aware;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author shitx
 *
 */
public class ShitxBeanNameAware implements BeanNameAware {
	private String beanName = "";
	

	/**
	 * @return the beanName
	 */
	public String getBeanName() {
		return beanName;
	}


	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
	 */
	public void setBeanName(String beanName) {
		//实现beanNameAware接口，可以将对应到该类的bean名字自动注入
		this.beanName = beanName;
	}
}
