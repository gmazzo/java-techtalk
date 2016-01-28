package com.globant.techtalk.java.services;

import java.util.List;

import com.globant.techtalk.java.TestSupport;

/**
 * A thread-safe {@link Service}, using <code>synchronized</code>
 *
 */
public class SyncServiceImpl extends ServiceImpl {

	public static void main(String[] args) {
		TestSupport.runFor(new SyncServiceImpl());
	}

	@Override
	public synchronized boolean report(String value) {
		return super.report(value);
	}

	@Override
	public synchronized List<String> collect() {
		return super.collect();
	}

}
