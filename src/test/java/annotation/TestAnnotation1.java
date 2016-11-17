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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import base.UnitTestBase;

/**
 * @author shitx
 *
 */

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:spring-annotation.xml"}) //容器根据xml加载对应beans
public class TestAnnotation1{

	@Autowired
	BeanAnnotation beanAnnotation;
	
	@Autowired
	BeanAnnotation beanScope;
	
	@Test
	public void testAnnotation(){
		beanAnnotation.say("This is test0");
		beanAnnotation.myHashCode();
		beanScope.myHashCode();
	}
	
}
