/**
 * 
 */
package annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.shitx.annotation.BeanAnnotation;
import com.shitx.autowiring.AutoWiringService;
import com.shitx.bean.BeanScope;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import base.UnitTestBase;

/**
 * @author shitx
 *
 */

@RunWith(BlockJUnit4ClassRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class) 
public class TestAnnotation extends UnitTestBase{

	/**
	 * @param springXmlpath
	 */
	public TestAnnotation() {
		super("classpath:spring-annotation.xml");
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testAnnotation(){
		BeanAnnotation beanAnnotation = super.getBean("beanAnnotation");
		beanAnnotation.say("This is test0");
		beanAnnotation.myHashCode();
		BeanAnnotation beanScope = super.getBean("beanAnnotation");
		beanScope.myHashCode();
	}
	
	@Test
	public void testScope(){
		BeanAnnotation beanScope = super.getBean("beanAnnotation");
		beanScope.myHashCode();
		beanScope.say("This is test1");
	}
	
	@Test
	public void testScope1(){
		BeanAnnotation beanScope = super.getBean("beanAnnotation");
		beanScope.myHashCode();
		beanScope.say("This is test2");
	}
}
