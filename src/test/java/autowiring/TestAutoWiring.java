/**
 * 
 */
package autowiring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.shitx.autowiring.AutoWiringService;
import com.shitx.bean.BeanScope;

import base.UnitTestBase;

/**
 * @author shitx
 *
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAutoWiring extends UnitTestBase{

	/**
	 * @param springXmlpath
	 */
	public TestAutoWiring() {
		super("classpath:spring-*.xml");
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testSay2(){
		AutoWiringService autoWiringService = super.getBean("autoWiringService");
		autoWiringService.say("hello");
	}
}
