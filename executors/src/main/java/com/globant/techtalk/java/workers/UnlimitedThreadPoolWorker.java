package com.globant.techtalk.java.workers;

import java.util.concurrent.Executors;

public class UnlimitedThreadPoolWorker extends ThreadPoolWorker {

	public UnlimitedThreadPoolWorker() {
		super(Executors.newCachedThreadPool());
	}

}
