/**
 * 
 */
package aware;

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
public class TestAware extends UnitTestBase{

	/**
	 * @param springXmlpath
	 */
	public TestAware() {
		super("classpath:spring-*.xml");
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testSay2(){
		//System.out.println(super.getBean("shitxApplicationContextAware").hashCode());
	}
}
