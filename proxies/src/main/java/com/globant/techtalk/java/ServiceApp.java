package com.globant.techtalk.java;

import com.globant.techtalk.java.services.Service;
import com.globant.techtalk.java.services.Service1Impl;
import com.globant.techtalk.java.services.Service2Impl;

public class ServiceApp {
	private final static long START = System.currentTimeMillis();
	private final Service service1;
	private final Service service2;

	public ServiceApp() {
		this(new Service1Impl(), new Service2Impl());
	}

	public ServiceApp(Service service1, Service service2) {
		this.service1 = service1;
		this.service2 = service2;
	}

	public static void main(String[] args) {
		new ServiceApp().doStuff();
	}

	public void doStuff() {
		System.out.println((System.currentTimeMillis() - START) + "ms: pinging service#1");
		service1.doSomething();
		service1.doSomethingElse();

		System.out.println((System.currentTimeMillis() - START) + "ms: waiting...");
		try {
			Thread.sleep(500);

		} catch (InterruptedException e) {
		}

		System.out.println((System.currentTimeMillis() - START) + "ms: pinging service#2");
		service2.doSomething();
		service2.doSomethingElse();

		System.out.println((System.currentTimeMillis() - START) + "ms: done!");
	}

}
