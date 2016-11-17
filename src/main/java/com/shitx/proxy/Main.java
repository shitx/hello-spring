/**
 * 
 */
package com.shitx.proxy;

import java.lang.reflect.Proxy;

/**
 * @author shitx
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SubjectImpl sbl = new SubjectImpl();  //实际在使用的被代理类的对象
		Subject proxy = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), //第一个参数是classloader    //将其转化为接口类的对象
				new Class[]{Subject.class}, 
				new ProxyHandler(sbl));  //proxy实际指向的是SubjectImpl的对象【sbl】，我们用subject接口引用它。我们想在sbl调用say方法前后做点事，但是不想知道sbl内部用的什么东西，也不想改subjectImpl的代码。
					//同时，有可能sbl的代码是隐藏或者需要保护的，加1层代理，在执行方法前后我们做自己要做的事儿， 见proxy的实现。  对外部用户而言，如果之前他们用subject引用了sbl对象，那代理对外部用户而言是没有
					//代码变动的，只不过现在返回的值发生了变化。   好处是不影响已有的代码。2头都不会影响。
		proxy.say();  //在这里调用了被代理类的某个方法
	}

}
