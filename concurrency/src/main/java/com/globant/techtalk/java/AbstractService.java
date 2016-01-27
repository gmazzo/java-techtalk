package com.globant.techtalk.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Base {@link Service} implementation
 *
 */
public class AbstractService implements Service {
	private final List<String> values;

	public AbstractService(List<String> values) {
		this.values = values;
	}

	@Override
	public boolean add(String value) {
		return values.add(value);
	}

	@Override
	public boolean remove(String value) {
		return values.remove(value);
	}

	@Override
	public List<String> collect() {
		try {
			return new ArrayList<>(values);

		} finally {
			values.clear();
		}
	}

}
