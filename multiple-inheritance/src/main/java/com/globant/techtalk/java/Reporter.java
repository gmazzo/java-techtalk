package com.globant.techtalk.java;

public interface Reporter {

	default void report(Object content) {
		report(String.valueOf(content));
	}

	void report(String content);

}
