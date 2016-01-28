package com.globant.techtalk.java.services;

import java.util.List;

/**
 * A thread-safe {@link Service}, using <code>synchronized</code>
 *
 */
public class SyncServiceImpl extends ServiceImpl {

	@Override
	public synchronized boolean report(String value) {
		return super.report(value);
	}

	@Override
	public synchronized List<String> collect() {
		return super.collect();
	}

}
