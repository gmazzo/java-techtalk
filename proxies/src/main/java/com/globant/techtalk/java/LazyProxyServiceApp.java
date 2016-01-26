package com.globant.techtalk.java;

import com.globant.techtalk.java.services.Service;
import com.globant.techtalk.java.services.Service1Impl;
import com.globant.techtalk.java.services.Service2Impl;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

public class LazyProxyServiceApp {

	public static void main(String[] args) {
		Service service1 = new Service1Impl();
		Service service2 = createLazyService(Service2Impl::new);

		new ServiceApp(service1, service2).doStuff();
	}

	/**
	 * JDK {@link Proxy}s are limited to interfaces only. You cannot create a
	 * proxy of a {@link Class}!
	 */
	private static Service createLazyService(final Supplier<Service> target) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class<?>[] interfaces = {
			Service.class
		};

		return (Service) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
			private Service service;

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// Lazy initializes the proxy on first use (first method called)
				if (service == null) {
					service = target.get();
				}

				try {
					return method.invoke(service, args);

				} catch (InvocationTargetException e) {
					throw e.getTargetException();
				}
			}

		});
	}

}
