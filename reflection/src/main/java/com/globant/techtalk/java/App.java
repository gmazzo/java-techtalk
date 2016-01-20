package com.globant.techtalk.java;

import com.globant.techtalk.java.conditions.Condition;
import com.globant.techtalk.java.conditions.IsInteger;
import com.globant.techtalk.java.conditions.IsPhrase;
import com.globant.techtalk.java.conditions.IsWord;
import java.util.Arrays;
import java.util.List;

public class App {
	private static final Condition CONDITIONS[] = {
		new IsWord(), new IsPhrase(), new IsInteger()
	};

	public static void main(String[] args) {
		List<Object> inputs = Arrays.asList("This is a test", "House", null, 1345, 45.09);

		for (Condition condition : CONDITIONS) {
			System.out.println("--------------------");
			System.out.println("condition=" + condition.describe());

			for (Object value : inputs) {
				System.out.println("value=" + value + "; test=" + condition.test(value));
			}
		}
	}

}
