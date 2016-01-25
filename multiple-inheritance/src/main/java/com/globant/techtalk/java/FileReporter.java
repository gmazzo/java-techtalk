package com.globant.techtalk.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReporter implements Reporter, AutoCloseable {
	private final FileWriter writer;

	public FileReporter(File file) {
		try {
			this.writer = new FileWriter(file, true);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void report(String content) {
		try {
			writer.append(content);
			writer.append("\n");
			writer.flush();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() throws Exception {
		writer.close();
	}

}
