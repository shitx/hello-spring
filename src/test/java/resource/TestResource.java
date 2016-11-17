/**
 * 
 */
package resource;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.shitx.bean.BeanScope;
import com.shitx.resource.MyResource;

import base.UnitTestBase;

/**
 * @author shitx
 *
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class TestResource extends UnitTestBase{

	/**
	 * @param springXmlpath
	 */
	public TestResource() {
		super("classpath:spring-*.xml");
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testResource(){
		MyResource resource = super.getBean("myResource");
		try {
			System.out.println("GetByClasspath:");
			resource.getResourceByClasspath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testResource1(){
		MyResource resource = super.getBean("myResource");
		try {
			System.out.println("GetByFilepath:");
			resource.getResourceByFilepath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	public void testResource2(){
		MyResource resource = super.getBean("myResource");
		try {
			System.out.println("GetByUrl:");
			resource.getResourceByUrl();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testResource3(){
		MyResource resource = super.getBean("myResource");
		try {
			System.out.println("GetByDefaultl:");
			resource.getResourceByDefault();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testResource0(){
		//自己new出的对象是没有被注入成员变量的。因此下面会输出null.  使用只能是通过配置为bean或者注解为bean.否则调用也是没有意义的，因为内部是空的
		MyResource resource = new MyResource();
		System.out.println("I am testResource1:"+resource.getApplicationContext());
	}
}
