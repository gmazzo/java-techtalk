package com.globant.techtalk.java.workers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public abstract class ThreadPoolWorker implements Worker, AutoCloseable {
	private final ExecutorService executor;

	public ThreadPoolWorker(ExecutorService executor) {
		this.executor = executor;
	}

	@Override
	public <U> Future<U> process(Callable<U> task) throws Exception {
		return executor.submit(task);
	}

	@Override
	public void close() throws Exception {
		executor.shutdownNow();
	}

}
