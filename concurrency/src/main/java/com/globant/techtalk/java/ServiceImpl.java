package com.globant.techtalk.java;

import java.util.LinkedList;

/**
 * This {@link Service}, is not thread-safe
 *
 */
public class ServiceImpl extends AbstractService {

	public static void main(String[] args) {
		TestSupport.runFor(new ServiceImpl());
	}

	public ServiceImpl() {
		super(new LinkedList<>());
	}

}
