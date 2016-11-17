/**
 * 
 */
package aware;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.shitx.aware.ShitxBeanNameAware;
import com.shitx.bean.BeanScope;

import base.UnitTestBase;

/**
 * @author shitx
 *
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanAware extends UnitTestBase{

	/**
	 * @param springXmlpath
	 */
	public TestBeanAware() {
		super("classpath:spring-*.xml");
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testSay2(){
		ShitxBeanNameAware a = super.getBean("shitxBeanAware");
		System.out.println("a=" + a.hashCode());
	}
}
