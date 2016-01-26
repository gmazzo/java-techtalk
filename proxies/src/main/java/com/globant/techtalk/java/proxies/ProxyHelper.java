package com.globant.techtalk.java.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import net.sf.cglib.proxy.Enhancer;

public abstract class ProxyHelper {
	private static final JDKProxyHelper JDK_PROXY_HELPER = new JDKProxyHelper();
	private static final CGLibProxyHelper CGLIB_PROXY_HELPER = new CGLibProxyHelper();

	static ProxyHelper getInstance(Class<?> targetClass) {
		return targetClass.isInterface() ? JDK_PROXY_HELPER : CGLIB_PROXY_HELPER;
	}

	public static <U> U create(Class<U> targetClass, InvocationHandler handler) {
		ProxyHelper helper = getInstance(targetClass);
		return helper.createProxy(targetClass, handler);
	}

	abstract <U> U createProxy(Class<U> targetClass, InvocationHandler handler);

}

/**
 * JDK proxies are limited to interfaces only. You cannot create a proxy of a
 * class!
 */
class JDKProxyHelper extends ProxyHelper {

	@Override
	@SuppressWarnings("unchecked")
	<U> U createProxy(Class<U> targetClass, InvocationHandler handler) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class<?>[] interfaces = {
			targetClass
		};
		return (U) Proxy.newProxyInstance(classLoader, interfaces, handler);
	}

}

/**
 * CGLib proxies can perform any kind of bytecode manipulation, it's very
 * powerful. This factory will effectively extend the target class, delegating
 * any method invocation to the given handler
 */
class CGLibProxyHelper extends ProxyHelper {

	@Override
	@SuppressWarnings("unchecked")
	<U> U createProxy(Class<U> targetClass, InvocationHandler handler) {
		return (U) Enhancer.create(targetClass, (net.sf.cglib.proxy.InvocationHandler) (proxy, method, args) -> {
			return handler.invoke(proxy, method, args);
		});
	}

}