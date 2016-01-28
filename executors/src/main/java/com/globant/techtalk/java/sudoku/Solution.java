package com.globant.techtalk.java.sudoku;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class Solution extends RecursiveTask<List<int[][]>> {
	private final int[][] arr;
	private int pos;

	public Solution(int[][] arr) {
		this(arr, 0);
	}

	private Solution(int[][] arr, int pos) {
		this.arr = arr;
		this.pos = pos;
	}

	@Override
	protected List<int[][]> compute() {
		// finds next 'free' position
		while (pos < arr.length && arr[row()][column()] != 0) {
			pos++;
		}
		if (pos >= arr.length) {
			// all boxes are filled, solution found!
			return Collections.singletonList(arr);
		}

		List<Solution> partials = new LinkedList<>();
		for (int k = 1; k <= 9; k++) {
			if (!isInRow(k) && !isInColumn(k) && !isInBox(k)) {
				// this partial solution is valid

				int[][] copy = copyArr();
				copy[row()][column()] = k;

				Solution partial = new Solution(copy, pos + 1);
				partial.fork(); // forks a new task for analyze deeper this partial solution
				partials.add(partial);
			}
		}
		return partials.stream() //
			.map(Solution::join) // collects the solutions of each partial
			.flatMap(Collection::stream) //
			.collect(Collectors.toCollection(LinkedList::new));
	}

	private int row() {
		return pos / 9;
	}

	private int column() {
		return pos % 9;
	}

	private boolean isInRow(int value) {
		int row = row();
		return isInLine(value, k -> arr[row][k]);
	}

	private boolean isInColumn(int value) {
		int column = column();
		return isInLine(value, k -> arr[k][column]);
	}

	private boolean isInLine(int value, IntFunction<Integer> op) {
		for (int k = 0; k < 9; k++) {
			int v = op.apply(k);

			if (v == value) {
				return true;
			}
		}
		return false;
	}

	private boolean isInBox(int value) {
		int boxRow = (row() / 3) * 3;
		int boxColumn = (column() / 3) * 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int v = arr[boxRow + i][boxColumn + j];

				if (v == value) {
					return true;
				}
			}
		}
		return false;
	}

	private int[][] copyArr() {
		int[][] r = new int[arr.length][];
		for (int i = 0; i < r.length; i++) {
			int[] arri = arr[i];
			int[] ri = r[i] = new int[arri.length];
			System.arraycopy(arri, 0, ri, 0, arri.length);
		}
		return r;
	}

	@Override
	public String toString() {
		return Arrays.deepToString(arr);
	}

}
