package com.globant.techtalk.java;

import java.util.LinkedList;
import java.util.List;

public final class TestSupport {

	public static void runFor(Service service) {
		List<ServiceThread> allThreads = new LinkedList<>();

		// creates the collector thread
		allThreads.add(new CollectorThread(service));

		// creates the worker threads
		for (int i = 0; i < 10; i++) {
			allThreads.add(new WorkerThread(service));
		}

		// starts the threads
		allThreads.forEach(Thread::start);

		long start = System.currentTimeMillis();

		// await until all threads are finished
		allThreads.stream().forEach(ServiceThread::await);

		System.out.println((System.currentTimeMillis() - start) + "ms: finished");
	}

	private TestSupport() {
	}

}

class CollectorThread extends ServiceThread {

	CollectorThread(Service service) {
		super(service);
	}

	@Override
	void doStuff() {
		try {
			Thread.sleep(1000);

		} catch (InterruptedException e) {
		}

		List<String> values = service.collect();
		System.out.println(values);
		running = !values.isEmpty();
	}

}

class WorkerThread extends CollectorThread {
	private int i = 1;

	WorkerThread(Service service) {
		super(service);
	}

	@Override
	void doStuff() {
		String value = "value" + (i++ % 100);

		service.add(value);
		if (i % 7 == 0) {
			service.remove(value);
		}

		if (i > 1000000) {
			running = false;
		}
	}

}

abstract class ServiceThread extends Thread {
	final Service service;
	boolean running = true;

	ServiceThread(Service service) {
		this.service = service;

		setDaemon(true);
	}

	abstract void doStuff();

	@Override
	public final void run() {
		while (running) {
			doStuff();
		}
	}

	public void await() {
		try {
			interrupt();
			join();

		} catch (InterruptedException e) {
		}
	}

}
