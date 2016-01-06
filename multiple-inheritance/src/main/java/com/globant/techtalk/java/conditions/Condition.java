package com.globant.techtalk.java.conditions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Condition {

	String describe();

	boolean test(Object value);

	static String join(String... descriptions) {
		return Stream.of(descriptions).collect(Collectors.joining(", "));
	}

}

interface NotNull extends Condition {

	@Override
	default String describe() {
		return "not null";
	}

	@Override
	default boolean test(Object value) {
		return value != null;
	}

}

interface IsText extends Condition {

	@Override
	default String describe() {
		return "text";
	}

	@Override
	default boolean test(Object value) {
		return value instanceof String;
	}

}

interface IsNumeric extends Condition {

	@Override
	default String describe() {
		return "numeric";
	}

	@Override
	default boolean test(Object value) {
		return value instanceof Number;
	}

}