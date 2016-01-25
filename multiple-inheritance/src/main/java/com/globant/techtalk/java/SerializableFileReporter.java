package com.globant.techtalk.java;

import java.io.File;

public class SerializableFileReporter extends FileReporter implements SerializableReporter {

	public SerializableFileReporter(File file) {
		super(file);
	}

}
