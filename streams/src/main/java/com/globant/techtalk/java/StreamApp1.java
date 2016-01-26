package com.globant.techtalk.java;

import java.util.Arrays;
import java.util.List;

public class StreamApp1 {
	private static final List<Integer> VALUES = Arrays.asList(3, 1, 5, 9, -2, 5, 3, 10, -4, 7, -2, 0);

	public static void main(String[] args) throws Exception {
		System.out.println("max: " + VALUES.stream().reduce(Integer::max).get());
		System.out.println("min: " + VALUES.stream().reduce(Integer::min).get());
		System.out.println("min (alternative): " + VALUES.stream().reduce((a, b) -> a < b ? a : b).get());
		System.out.println(">=3: " + VALUES.stream().filter(n -> n >= 3).count());
		System.out.println(
			">=100: " + VALUES.stream().filter(n -> n >= 100).findFirst().map(String::valueOf).orElse("Not found"));
		System.out.println(">=100: " + VALUES.stream().filter(n -> n >= 100).findFirst().map(String::valueOf).get());
	}

}
