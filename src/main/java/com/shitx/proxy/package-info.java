/**
 * 
 */
/**
 * @author shitx
 * 本包不用到spring,只测试动态代理的使用的方法
 * 这里实现的是proxyHandler是java.lang.reflect.InvocationHandler接口;  在spring里也有这个接口。
 * 另外spring里的aop切面其实就能很好实现before,after，around需求。和本例种用代理实现初始化和销毁的附加工作，而不改变已有代码是一致的
 */
package com.shitx.proxy;

