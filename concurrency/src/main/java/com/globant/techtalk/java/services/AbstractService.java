package com.globant.techtalk.java.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Base {@link Service} implementation
 *
 */
public class AbstractService implements Service {
	private final Collection<String> values;

	public AbstractService(Collection<String> values) {
		this.values = values;
	}

	@Override
	public boolean report(String value) {
		return values.add(value);
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
