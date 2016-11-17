/**
 * 
 */
package bean;

import org.junit.Test;

import base.UnitTestBase;

/**
 * @author shitx
 *
 */
public class TestBeanLifeCycle extends UnitTestBase {
	
	@Test
	public void test1(){
		super.getBean("lifeCycle");
	}
}
