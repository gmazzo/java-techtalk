package com.globant.techtalk.java.conditions;

public class IsInteger implements NotNull, IsNumeric {

	@Override
	public String describe() {
		return Condition.join("int", NotNull.super.describe(), IsNumeric.super.describe());
	}

	@Override
	public boolean test(Object value) {
		return NotNull.super.test(value) && IsNumeric.super.test(value) && test(((Number) value).doubleValue());
	}

	private boolean test(double value) {
		return value == Math.ceil(value);
	}

}
