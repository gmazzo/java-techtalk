package com.globant.techtalk.java;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import com.globant.techtalk.java.sudoku.Solution;

public class SudokuSolverApp {
	private static final int SAMPLE[][] = {
		{
			0, 0, 7, 8, 5, 0, 6, 3, 4
		}, {
			8, 4, 0, 0, 0, 7, 0, 0, 0
		}, {
			0, 6, 9, 0, 0, 0, 0, 0, 0
		}, {
			0, 0, 5, 0, 6, 2, 4, 0, 0
		}, {
			0, 3, 0, 0, 0, 0, 0, 0, 0
		}, {
			6, 0, 0, 0, 8, 0, 5, 0, 0
		}, {
			0, 0, 0, 4, 0, 0, 0, 6, 0
		}, {
			0, 0, 0, 5, 0, 0, 3, 2, 0
		}, {
			7, 2, 1, 0, 3, 0, 8, 4, 5
		}
	};

	public static void main(String[] args) {
		Solution solver = new Solution(SAMPLE);

		List<int[][]> solutions = ForkJoinPool.commonPool().submit(solver).join();
		System.out.println("Found " + solutions.size() + " solutions:");
		solutions.stream().map(Arrays::deepToString).forEach(System.out::println);
	}

}
