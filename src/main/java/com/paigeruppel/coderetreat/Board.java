package com.paigeruppel.coderetreat;

import static com.paigeruppel.coderetreat.CellState.*;

public class Board {

	private CellState[][] grid;
	

	public Board(CellState[][] grid) {
		this.grid = grid;
	}
	public CellState getGridCoords(int row, int col) {
		return grid[row][col];
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

	public void tick() {
		toggleCellState(1, 2);
		toggleCellState(3, 2);
		toggleCellState(2, 1);
		toggleCellState(2, 3);
	}

	private void toggleCellState(int row, int col) {
		grid[row][col] = grid[row][col] == ALIVE? DEAD:ALIVE;
	}

	private int lookUp(int row, int col) {
		if (row == 0) {
			return 0;
		}
		if (grid[row - 1][col] == ALIVE) {
			return 1;
		}
		return 0;
	}

	private int lookDown(int row, int col) {
		if (row == grid.length - 1) {
			return 0;
		}
		if (grid[row + 1][col] == ALIVE) {
			return 1;
		}
		return 0;
	}

	private int lookUpRight(int row, int col) {
		if (row == 0 || col == grid.length - 1) {
			return 0;
		}
		if (grid[row - 1][col + 1] == ALIVE) {
			return 1;
		}
		return 0;
	}

	private int lookRight(int row, int col) {
		if (col == grid.length - 1) {
			return 0;
		}
		if (grid[row][col + 1] == ALIVE) {
			return 1;
		}
		return 0;
	}

	private int lookDownRight(int row, int col) {
		if (row == grid.length - 1 || col == grid.length - 1) {
			return 0;
		}
		if (grid[row + 1][col + 1] == ALIVE) {
			return 1;
		}
		return 0;
	}

	private int lookUpLeft(int row, int col) {
		if (row == 0 || col == 0) {
			return 0;
		}
		if (grid[row - 1][col - 1] == ALIVE) {
			return 1;
		}
		return 0;
	}

	private int lookLeft(int row, int col) {
		if (col == 0) {
			return 0;
		}
		if (grid[row][col - 1] == ALIVE) {
			return 1;
		}
		return 0;
	}

	private int lookDownLeft(int row, int col) {
		if (row == grid.length - 1 || col == 0) {
			return 0;
		}
		if (grid[row + 1][col - 1] == ALIVE) {
			return 1;
		}
		return 0;
	}
}
