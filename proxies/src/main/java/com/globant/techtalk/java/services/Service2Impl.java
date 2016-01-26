package com.globant.techtalk.java.services;

/**
 * This {@link Service} takes about 2secs to initialize
 */
public class Service2Impl extends Service1Impl {

	public Service2Impl() {
		try {
			Thread.sleep(2000);

		} catch (InterruptedException e) {
		}
	}

}
