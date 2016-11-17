/**
 * 
 */
package com.shitx.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author shitx             
 * 学习AOP步骤： 三步走【aspectj配置，   pointcut ,            advice】
 * 源代码本地路径: E:\others\学习\WEB\imooc\imooc-spring-3750
 * 源代码网络路径: http://img.mukewang.com/down/545040d70001107900000000.zip
 */

@Component //[因为Aspect是无法被包扫描自动发现的，base-package,所以才需要此项，表示它是bean,Aspect才会生效]
@Aspect //[声明是一个切面]
public class shitxAspect {
	
	@Pointcut("execution(* com.shitx.aop.aspectj.biz.*Biz.*(..))") //声明这是一个切点.和配置文件完成同样的工作，注意不要在这里做任何的操作，仅仅是定义一个点而已.写了也会被忽略
	public void pointcut(){}
	
	@Pointcut("within(com.shitx.aop.aspectj.biz.*)") //声明这是一个切点，在对应包内的方法执行
	public void bizPointcut(){}
	
	@Before("execution(* com.shitx.aop.aspectj.biz.*Biz.*(..))")  //和配置文件before方法执行是一样的，注意1个切点可以有多个before方法， 在每个切点对应的方法执行之前，这里所有满足条件的@before都会执行
	public void before(){     //[#S2]
		System.out.println("Before.");
	}
	
	@Before(value="pointcut() && args(arg)")   //这是基于参数传入，还可以基于annotaion传入，不过要自定义注解，这里不介绍
	public void beforeWithParam(String arg){     
		System.out.println("BeforeWithParam." + arg);
	}
	@AfterReturning(pointcut="bizPointcut()",returning="returnValue")  //必须获取返回值。要指定切点。【用于获取返回值】 [#S6]  这是在finally之后执行的
	public void afterReturning(Object returnValue){
		System.out.println("afterReturning:" + returnValue);
	}
	
//	@AfterReturning(pointcut="bizPointcut()")  //必须要指定切点。不获取返回值
//	public void afterReturning(){
//		System.out.println("afterReturning: no return");
//	}
	
	@AfterThrowing(pointcut="bizPointcut()",throwing="e")  //after throwing advice 【用于处于异常】   ？？它很特殊，在around1  after之后出现，当它出现的时候，afterreturning不会执行了
	public void afterThrowing(RuntimeException e){
		System.out.println("afterThrowing" + e.getMessage());
	}
	
	@After(value="execution(* com.shitx.aop.aspectj.biz.*Biz.*(..))")  //after advice 事后通知finally 【用于释放链接】 [#S5] //因为它是finally，所以它其实是在around2之后
	public void after(){
		System.out.println("after： now to release reource");
	}
	
	@Around(value="bizPointcut()")  // 
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("Around 1：");  //[#S1]
		Object retVal = pjp.proceed(); //即使该方法是void类型，也是一种特殊的返回 [#S3]   在它之前有around1,before
		System.out.println("Around 2：");  //[#S4]  在它之后有after afterreturning
		System.out.println("Around : " + retVal);
		return  retVal;
	}
}
