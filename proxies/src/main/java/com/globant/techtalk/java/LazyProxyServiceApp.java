package com.globant.techtalk.java;

import com.globant.techtalk.java.proxies.ProxyHelper;
import com.globant.techtalk.java.services.Service;
import com.globant.techtalk.java.services.Service1Impl;
import com.globant.techtalk.java.services.Service2Impl;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public class LazyProxyServiceApp {

	public static void main(String[] args) {
		Service service1 = new Service1Impl();
		Service service2 = createLazyService(Service2Impl::new);

		new ServiceApp(service1, service2).doStuff();
	}

	private static Service createLazyService(final Supplier<Service> target) {
		return ProxyHelper.create(Service.class, new InvocationHandler() {
			private Service instance;

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// Lazy initializes the proxy on first use (first method called)
				if (instance == null) {
					instance = target.get();
				}

				try {
					return method.invoke(instance, args);

				} catch (InvocationTargetException e) {
					throw e.getTargetException();
				}
			}

		});
	}

}
