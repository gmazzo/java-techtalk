package com.globant.techtalk.java;

import java.util.Arrays;
import java.util.List;

public class StreamApp1 {
	private static final List<Integer> VALUES = Arrays.asList(3, 1, 5, 9, -2, 5, 3, 10, -4, 7, -2, 0);

	public static void main(String[] args) throws Exception {
		// Show the maximum number
		System.out.println("max: " + VALUES.stream().reduce(Integer::max).get());

		// Show the minimum number
		System.out.println("min: " + VALUES.stream().reduce(Integer::min).get());

		// Show the minimum number (alternative way)
		System.out.println("min (alternative): " + VALUES.stream().reduce((a, b) -> a < b ? a : b).get());

		// Show the amount of number greater or equal than 3
		System.out.println(">=3: " + VALUES.stream().filter(n -> n >= 3).count());

		// Shows the first number greater or equal than 100, or "Not found" if none meets the condition
		System.out.println(
			">=100: " + VALUES.stream().filter(n -> n >= 100).findFirst().map(String::valueOf).orElse("Not found"));

		// Shows the first number greater or equal than 100, or throws an exception if none meets the condition (alternative way)
		System.out.println(">=100: " + VALUES.stream().filter(n -> n >= 100).findFirst().map(String::valueOf)
			.orElseThrow(() -> new IllegalArgumentException("No number is >= 100")));
	}

}
