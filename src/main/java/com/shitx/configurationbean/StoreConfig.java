/**
 * 
 */
package com.shitx.configurationbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * @author shitx
 *
 */

@Configuration   //表示这是一个配置类文件【等同于配置方式配置bean中的xml文件，里面配置各种bean】
@ImportResource("classpath:config.xml")   //引入xml文件,该文件中指定配置文件config.peroperties的路径[place-holder]
public class StoreConfig {
//	@Value("${jdbc.url}")  //注意如果配置文件中有username,则会取到当前操作系统的的登陆用户名，所以应该使用jdbc.username,而不是username
//	private String url;
//	@Value("${jdbc.username}")
//	private String username;
//	@Value("${jdbc.password}")
//	private String password;
//	
//	@Scope("singleton")
//	@Bean(name="stringStore", initMethod="init",destroyMethod="destroy")  //和Configuration一起使用，替代原来的配置文件。 在不指定bean名字时，返回的bean名字是方法的名字，囧
//	public Store getStringStore(){
//		return new StringStore();
//	}
//	
//	@Bean  
//	@Scope(value="prototype",proxyMode=ScopedProxyMode.TARGET_CLASS)
//	public MyDriverManager myDriverManager(){
//		return new MyDriverManager(url, username, password);  //这里的参数都是@Value直接从配置文件中读取到的，然后返回的对象因为有@bean，所以会被作为一个bean来使用
//	}
	
	//#########
	@Autowired
	private Store<String> s1;
	
	@Autowired
	private Store<Integer> s2;
	
	@Bean
	public StringStore stringStore(){
		return new StringStore();
	}
	
	@Bean
	public IntegerStore intergerStore(){
		return new IntegerStore();
	}
	
	@Bean(name="testbean")
	public String storeTest(){
		System.out.println("s1"+s1.getClass().getName());
		System.out.println("s2"+s2.getClass().getName());
		return "test";
	}
}
