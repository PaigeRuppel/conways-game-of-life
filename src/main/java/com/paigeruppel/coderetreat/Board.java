package com.paigeruppel.coderetreat;

public class Board {

	private boolean[][] oscillator = { { false, false, false, false, false }, { false, false, true, false, false },
			{ false, false, true, false, false }, { false, false, true, false, false },
			{ false, false, false, false, false } };

	public boolean getOscillatorCoords(int row, int col) {
		return oscillator[row][col];
	}

	public int countLivingNeighbors(int row, int col) {
		int livingNeighbors = 0;
		livingNeighbors += lookUp(row, col);
		livingNeighbors += lookDown(row, col);
		livingNeighbors += lookUpRight(row, col);
		livingNeighbors += lookRight(row, col);
		livingNeighbors += lookDownRight(row, col);
		livingNeighbors += lookUpLeft(row, col);
		livingNeighbors += lookLeft(row, col);
		livingNeighbors += lookDownLeft(row, col);
		return livingNeighbors;
	}

	private int lookUp(int row, int col) {
		if (row == 0) {
			return 0;
		}
		if (oscillator[row - 1][col]) {
			return 1;
		}
		return 0;
	}

	private int lookDown(int row, int col) {
		if (row == oscillator.length - 1) {
			return 0;
		}
		if (oscillator[row + 1][col]) {
			return 1;
		}
		return 0;
	}

	private int lookUpRight(int row, int col) {
		if (row == 0 || col == oscillator.length - 1) {
			return 0;
		}
		if (oscillator[row - 1][col + 1]) {
			return 1;
		}
		return 0;
	}

	private int lookRight(int row, int col) {
		if (col == oscillator.length - 1) {
			return 0;
		}
		if (oscillator[row][col + 1]) {
			return 1;
		}
		return 0;
	}

	private int lookDownRight(int row, int col) {
		if (row == oscillator.length - 1 || col == oscillator.length - 1) {
			return 0;
		}
		if (oscillator[row + 1][col + 1]) {
			return 1;
		}
		return 0;
	}

	private int lookUpLeft(int row, int col) {
		if (row == 0 || col == 0) {
			return 0;
		}
		if (oscillator[row - 1][col - 1]) {
			return 1;
		}
		return 0;
	}
	
	private int lookLeft(int row, int col) {
		if (col == 0) {
			return 0;
		}
		if (oscillator[row][col - 1]) {
			return 1;
		}
		return 0;
	}
	
	private int lookDownLeft(int row, int col) {
		if (row == oscillator.length - 1 || col == 0) {
			return 0;
		}
		if (oscillator[row + 1][col - 1]) {
			return 1;
		}
		return 0;
	}
}
