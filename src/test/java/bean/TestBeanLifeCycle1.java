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
public class TestBeanLifeCycle1 extends UnitTestBase {
	
	@Test
	public void test1(){
		super.getBean("lifeCycle1");
	}
}
