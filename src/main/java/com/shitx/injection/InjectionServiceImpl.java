/**
 * 
 */
package com.shitx.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shitx
 *   [1]Autowired适合用来注解那些总所周知的解析依赖性接口，比如，BeanFactory,ApplicationContext,Environment,ResourceLoader,ApplicationEventPublisher, MessageSource
 *   [2]Autowired还可以通过添加注解给需要该类型的数组的字段或者方法，以提供ApplicationContext中所有该类型的bean
 *   [3]Autowired还可以用于装配key为string的Map<String,Catalog>,key为beanid，value为bean对象.如果希望在map中有序，那么实现springframework.core.Ordered接口或者添加 @Order注解
 */

@Service
public class InjectionServiceImpl implements InjectionService {
//	@Autowired         // [1] 注解到变量
	private InjectionDAO injectionDAO;
	
	/* (non-Javadoc)
	 * @see com.shitx.injection.InjectionService#save()
	 */
	public void save(String str) {
		// TODO Auto-generated method stub
		System.out.println("Service 接受到参数：" + str);
		this.injectionDAO.save(str);
	}

	/**
	 * @param injectionDAO
	 */
	@Autowired  //[3]构造器自动注入 
	public InjectionServiceImpl(InjectionDAO injectionDAO) {
		super();
		this.injectionDAO = injectionDAO;
	}

	/**
	 * @param injectionDAO the injectionDAO to set
	 */
	//@Autowired      //[2] 注解到set方法
	public void setInjectionDAO(InjectionDAO injectionDAO) {
		this.injectionDAO = injectionDAO;
	}

}
