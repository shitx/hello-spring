/**
 * 
 */
package com.shitx.aop.schema.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author shitx
 * 切面类
 */
public class ShitxAspect {
	public void before(){
		System.out.println("Shitx Aspect before...");
	}

	public void after(){
		System.out.println("Shitx Aspect after...");
	}

	//在有异常时，不会正常返回，所以afterReturning不会执行
	public void afterReturning(){
		System.out.println("After Shitx Aspect returns...");
	}

	public void afterThrowing(){  
		System.out.println("After Shitx Aspect throwing...");
	}

	public Object aroundBiz(ProceedingJoinPoint pjp) throws Throwable{    //环绕通知
		Object obj = null;
		System.out.println("Shitx Aspect aroundBiz 1...");  
		obj = pjp.proceed(); //proceed执行的就是切点对应的方法，我们可以在该方法前后很方便执行某些代码
		System.out.println("Shitx Aspect aroundBiz 2...");
		return obj;
	}
	
	public Object aroundInit(ProceedingJoinPoint pjp,String bizName, int times) throws Throwable{    //环绕通知
		System.out.println("Shitx Aspect aroundInit:" + bizName + " " + times);
		Object obj = null;
		System.out.println("Shitx Aspect aroundInit 1...");  
		obj = pjp.proceed(); //proceed执行的就是切点对应的方法，我们可以在该方法前后很方便执行某些代码
		System.out.println("Shitx Aspect aroundInit 2...");
		return obj;
	}
}
