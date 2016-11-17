/**
 * 
 */
package aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.shitx.annotation.BeanAnnotation;
import com.shitx.aop.aspectj.biz.ShitxBiz;
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
public class TestAopAspectj extends UnitTestBase{

	/**
	 * @param springXmlpath
	 */
	public TestAopAspectj() {
		super("classpath:spring-aop-aspectj.xml");
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void test(){
		ShitxBiz biz = super.getBean("shitxBiz");
		biz.save("This is test.");
//		biz.save("This is test.");
	}
}
