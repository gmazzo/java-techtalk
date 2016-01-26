package com.globant.techtalk.java;

import java.util.concurrent.Executors;

public class UnlimitedThreadPoolWorker extends ThreadPoolWorker {

	public UnlimitedThreadPoolWorker() {
		super(Executors.newCachedThreadPool());
	}

}
