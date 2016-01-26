package com.globant.techtalk.java;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class ExecutorsWithLambdas extends ExecutorsApp {

	public static void main(String[] args) throws Exception {
		List<Callable<?>> tasks = new LinkedList<>();
		tasks.add(sampleLambdaTask("Task#1", 1000));
		tasks.add(sampleLambdaTask("Task#2", 3000));
		tasks.add(sampleLambdaTask("Task#3", 2000));
		tasks.add(sampleLambdaTask("Task#4", 1500));
		tasks.add(sampleLambdaTask("Task#5", 2500));
		tasks.add(sampleLambdaTask("Task#6", 500));

		ExecutorsWithLambdas app = new ExecutorsWithLambdas();
		app.process(LinearWorker::new, tasks);
		app.process(FixedThreadPoolWorker::new, tasks);
		app.process(UnlimitedThreadPoolWorker::new, tasks);
	}

	private void process(Supplier<Worker> workerSupplier, List<Callable<?>> tasks) throws Exception {
		process(workerSupplier.get(), tasks);
	}

	/**
	 * This lambda will return an {@link Callable} light implementation (no new
	 * inner anonymous class will be created by the compiler)
	 */
	private static <U> Callable<U> sampleLambdaTask(final U result, final long millis) {
		return () -> {
			Thread.sleep(millis);
			return result;
		};
	}

}
