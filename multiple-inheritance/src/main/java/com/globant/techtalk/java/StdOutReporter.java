package com.globant.techtalk.java;

public class StdOutReporter implements Reporter {

	@Override
	public void report(String content) {
		System.out.println(content);
	}

}
