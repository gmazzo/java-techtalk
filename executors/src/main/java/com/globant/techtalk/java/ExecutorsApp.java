package com.globant.techtalk.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.globant.techtalk.java.workers.FixedThreadPoolWorker;
import com.globant.techtalk.java.workers.LinearWorker;
import com.globant.techtalk.java.workers.UnlimitedThreadPoolWorker;
import com.globant.techtalk.java.workers.Worker;

public class ExecutorsApp {

	public static void main(String[] args) throws Exception {
		List<Callable<?>> tasks = new LinkedList<>();
		tasks.add(sampleTask("Task#1", 1000));
		tasks.add(sampleTask("Task#2", 3000));
		tasks.add(sampleTask("Task#3", 2000));
		tasks.add(sampleTask("Task#4", 1500));
		tasks.add(sampleTask("Task#5", 2500));
		tasks.add(sampleTask("Task#6", 500));

		ExecutorsApp app = new ExecutorsApp();
		app.process(new LinearWorker(), tasks);
		app.process(new FixedThreadPoolWorker(), tasks);
		app.process(new UnlimitedThreadPoolWorker(), tasks);
	}

	protected void process(Worker worker, List<Callable<?>> tasks) throws Exception {
		try {
			System.out.println(worker.getClass().getSimpleName());

			long start = System.currentTimeMillis();

			// first post all tasks into the worker
			List<Future<?>> results = new ArrayList<>(tasks.size());
			for (Callable<?> task : tasks) {
				System.out.println("|- " + (System.currentTimeMillis() - start) + "ms: posting task");

				results.add(worker.process(task));
			}

			// then we claim the results
			while (!results.isEmpty()) {
				for (Iterator<Future<?>> it = results.iterator(); it.hasNext();) {
					Future<?> future = it.next();

					if (future.isDone()) {
						it.remove();

						Object result = future.get();

						System.out.println("|- " + (System.currentTimeMillis() - start) + "ms: result " + result);
					}
				}
			}

			System.out.println("\\- " + (System.currentTimeMillis() - start) + "ms: finished");

		} finally {
			if (worker instanceof AutoCloseable) {
				((AutoCloseable) worker).close();
			}
		}
	}

	/**
	 * Returns a new {@link Callable} instance, as a closure of the current
	 * method's args. The compiler will create a new inner anonymous class for
	 * this (you will see a ExecutorsApp$1.class file)
	 */
	private static <U> Callable<U> sampleTask(final U result, final long millis) {
		return new Callable<U>() {

			@Override
			public U call() throws Exception {
				Thread.sleep(millis);
				return result;
			}

		};
	}

}
