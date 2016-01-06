package com.globant.techtalk.java.conditions;

public class IsPhrase implements NotNull, IsText {

	@Override
	public String describe() {
		return Condition.join("phrase", NotNull.super.describe(), IsText.super.describe());
	}

	@Override
	public boolean test(Object value) {
		return NotNull.super.test(value) && IsText.super.test(value) && value.toString().matches("\\w+(\\s\\w+)+.");
	}

}
