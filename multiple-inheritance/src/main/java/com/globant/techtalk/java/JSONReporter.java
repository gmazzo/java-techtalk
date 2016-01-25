package com.globant.techtalk.java;

import com.google.gson.Gson;

public interface JSONReporter extends Reporter {
	Gson GSON = new Gson();

	@Override
	default void report(Object content) {
		report(GSON.toJson(content));
	}

}
