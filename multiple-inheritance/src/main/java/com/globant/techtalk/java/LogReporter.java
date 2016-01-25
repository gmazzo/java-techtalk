package com.globant.techtalk.java;

import java.util.logging.Logger;

public class LogReporter implements Reporter {
	private static final Logger LOGGER = Logger.getLogger(LogReporter.class.getName());

	@Override
	public void report(String content) {
		LOGGER.info(content);
	}

}
