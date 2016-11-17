/**
 * 
 */
package bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.shitx.bean.BeanScope;

import base.UnitTestBase;

/**
 * @author shitx
 *
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanScope extends UnitTestBase{

	/**
	 * @param springXmlpath
	 */
	public TestBeanScope() {
		super("classpath*:spring-beanscope.xml");
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testSay(){
		BeanScope beanScopse = super.getBean("beanScope");
		beanScopse.say();
		BeanScope beanScope2 = super.getBean("beanScope");
		beanScope2.say();
	}
	
	@Test
	public void testSay2(){
		BeanScope beanScope2 = super.getBean("beanScope");
		beanScope2.say();
	}
}
