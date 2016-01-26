package com.globant.techtalk.java;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * This is no-concurrent {@link Worker}, will simple call the
 * {@link Callable#call()} in the caller {@link Thread}
 * 
 * @author guillermo.mazzola
 *
 */
public class LinearWorker implements Worker {

	@Override
	public <U> Future<U> process(Callable<U> task) throws Exception {
		return CompletableFuture.completedFuture(task.call());
	}

}
