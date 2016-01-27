package com.globant.techtalk.java;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This {@link Service} with a thread-safe list for holding the data
 *
 */
public class ConcurrentListServiceImpl extends AbstractService {

	public static void main(String[] args) {
		TestSupport.runFor(new ConcurrentListServiceImpl());
	}

	public ConcurrentListServiceImpl() {
		super(new CopyOnWriteArrayList<>());
	}

}
