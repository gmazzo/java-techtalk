package com.globant.techtalk.java.workers;

import java.util.concurrent.Executors;

public class FixedThreadPoolWorker extends ThreadPoolWorker {

	public FixedThreadPoolWorker() {
		super(Executors.newFixedThreadPool(3));
	}

}
