/**
 * 
 */
package Injection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.shitx.bean.BeanScope;
import com.shitx.injection.BeanInvoker;
import com.shitx.injection.InjectionService;

import base.UnitTestBase;

/**
 * @author shitx
 *
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class TestInjection extends UnitTestBase{

	/**
	 * @param springXmlpath
	 */
	public TestInjection() {
		super("classpath:spring-*.xml");
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testSave(){
		InjectionService injectionService = super.getBean("injectionServiceImpl");
		injectionService.save("test data");
	}
	
	@Test
	public void testAutowireList(){
		BeanInvoker beanInvoker = super.getBean("beanInvoker");
		beanInvoker.say();
	}
}
