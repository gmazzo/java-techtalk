package com.globant.techtalk.java.services;

import java.util.ArrayList;

import com.globant.techtalk.java.TestSupport;

/**
 * This {@link Service}, is not thread-safe
 * 
 */
public class ServiceImpl extends AbstractService {

	public static void main(String[] args) {
		TestSupport.runFor(new ServiceImpl());
	}

	public ServiceImpl() {
		super(new ArrayList<>());
	}

}
