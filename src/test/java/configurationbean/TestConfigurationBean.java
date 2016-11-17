/**
 * 
 */
package configurationbean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.shitx.bean.BeanScope;
import com.shitx.configurationbean.MyDriverManager;
import com.shitx.configurationbean.Store;
import com.shitx.configurationbean.StringStore;
import com.shitx.injection.BeanInvoker;
import com.shitx.injection.InjectionService;

import base.UnitTestBase;

/**
 * @author shitx
 *
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class TestConfigurationBean extends UnitTestBase{

	/**
	 * @param springXmlpath
	 */
	public TestConfigurationBean() {
		super("classpath:spring-*.xml");
		// TODO Auto-generated constructor stub
	}
	
//	//@Test
//	public void testSave(){
//		Store store = super.getBean("stringStore");
//		System.out.println(store.hashCode());
//	}
//	
//	@Test
//	public void testMyDriverManager(){
//		MyDriverManager myDriverManager = super.getBean("myDriverManager");
//		System.out.println(myDriverManager.getClass().getName());
//	}
	
	@Test
	public void testG(){
		StringStore stringStore = super.getBean("testbean");
//		System.out.println(store.hashCode());
	}
}
