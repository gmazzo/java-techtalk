package com.globant.techtalk.java;

import java.util.List;

/**
 * A thread-safe {@link Service}, using <code>syncr</code>
 *
 */
public class SyncServiceImpl extends ServiceImpl {

	public static void main(String[] args) {
		TestSupport.runFor(new SyncServiceImpl());
	}

	@Override
	public synchronized boolean add(String value) {
		return super.add(value);
	}

	@Override
	public synchronized boolean remove(String value) {
		return super.remove(value);
	}

	@Override
	public synchronized List<String> collect() {
		return super.collect();
	}

}
