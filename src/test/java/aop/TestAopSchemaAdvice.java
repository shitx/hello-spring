/**
 * 
 */
package aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.shitx.annotation.BeanAnnotation;
import com.shitx.aop.schema.biz.AspectBiz;
import com.shitx.aop.schema.biz.Fit;
import com.shitx.autowiring.AutoWiringService;
import com.shitx.bean.BeanScope;

import base.UnitTestBase;

/**
 * @author shitx
 *
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAopSchemaAdvice extends UnitTestBase{

	/**
	 * @param springXmlpath
	 */
	public TestAopSchemaAdvice() {
		super("classpath:spring-aop-schema-advice.xml");
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testBiz(){
		AspectBiz aspectBiz = super.getBean("aspectBiz");
		aspectBiz.biz();
	}
	
	@Test
	public void testInit(){
		AspectBiz aspectBiz = super.getBean("aspectBiz");
		aspectBiz.init("shitxService", 3);
	}
	
	@Test
	public void testFit(){
		Fit fit = (Fit)super.getBean("aspectBiz");  //
		fit.filter();
	}
}
