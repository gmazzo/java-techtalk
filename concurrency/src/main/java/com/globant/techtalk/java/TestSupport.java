package com.globant.techtalk.java;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.globant.techtalk.java.services.Service;

public final class TestSupport {

	public static void runFor(Service service) {
		AtomicInteger reported = new AtomicInteger();
		AtomicInteger collected = new AtomicInteger();
		AtomicInteger times = new AtomicInteger();

		// creates the worker threads
		List<Thread> workers = new LinkedList<>();
		for (int i = 0; i < 100; i++) {
			workers.add(new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					String value = "value" + j;

					service.report(value);
					reported.incrementAndGet();
				}
			}));
		}

		// creates the collector thread
		Thread collector = new Thread(() -> {
			List<String> values;
			do {
				values = service.collect();

				if (!values.isEmpty()) {
					collected.addAndGet(values.size());
					times.incrementAndGet();

					System.out.println(values);
				}

			} while (!values.isEmpty() || !workers.isEmpty());
		});

		// starts the threads
		workers.forEach(Thread::start);
		collector.start();

		long start = System.currentTimeMillis();

		// await until all threads are finished
		for (Iterator<Thread> it = workers.iterator(); it.hasNext();) {
			try {
				it.next().join();

			} catch (InterruptedException e) {

			} finally {
				it.remove();
			}
		}
		try {
			collector.join();

		} catch (InterruptedException e) {
		}

		int reportedCount = reported.get();
		int collectedCount = collected.get();
		(reportedCount == collectedCount ? System.out : System.err).println((System.currentTimeMillis() - start)
			+ "ms: repored=" + reportedCount + ", collected=" + collectedCount + ", times=" + times.get());
	}

	private TestSupport() {
	}

}
