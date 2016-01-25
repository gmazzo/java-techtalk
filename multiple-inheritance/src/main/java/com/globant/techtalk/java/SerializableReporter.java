package com.globant.techtalk.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public interface SerializableReporter extends Reporter {

	@Override
	default void report(Object content) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(content);
			}
			report(baos.toString());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
