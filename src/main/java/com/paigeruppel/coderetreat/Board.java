package com.paigeruppel.coderetreat;

import static com.paigeruppel.coderetreat.CellState.*;

public class Board {

	private CellState[][] grid;

	public Board(CellState[][] grid) {
		this.grid = grid;
	}

	public void tick() {
		CellState[][] nextGrid = initializeNextGrid();

		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if (countNeighbors(row, col, ALIVE) == 2 && grid[row][col] == ALIVE) {
					nextGrid[row][col] = ALIVE;
				}
				if (countNeighbors(row, col, ALIVE) == 3) {
					nextGrid[row][col] = ALIVE;
				}
				if (grid[row][col] == DEAD && countNeighbors(row, col, ALIVE) == 4) {
					nextGrid[row][col] = ZOMBIE;
				}
				if (grid[row][col] == ALIVE && countNeighbors(row, col, ZOMBIE) == 2) {
					nextGrid[row][col] = ZOMBIE;
				}
				if (grid[row][col] == ZOMBIE && countNeighbors(row, col, ALIVE) > 0) {
					nextGrid[row][col] = ZOMBIE;
				}
			}
		}

		grid = nextGrid;
	}

	public int countNeighbors(int row, int col, CellState state) {
		int livingNeighbors = 0;
		livingNeighbors += lookUp(row, col, state);
		livingNeighbors += lookDown(row, col, state);
		livingNeighbors += lookUpRight(row, col, state);
		livingNeighbors += lookRight(row, col, state);
		livingNeighbors += lookDownRight(row, col, state);
		livingNeighbors += lookUpLeft(row, col, state);
		livingNeighbors += lookLeft(row, col, state);
		livingNeighbors += lookDownLeft(row, col, state);
		return livingNeighbors;
	}

	public CellState[][] getGrid() {
		return grid;
	}

	private CellState[][] initializeNextGrid() {
		CellState[][] nextGrid = new CellState[grid.length][grid[0].length];
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				nextGrid[row][col] = DEAD;
			}
		}

		return nextGrid;
	}

	private int lookUp(int row, int col, CellState state) {
		if (row == 0) {
			return 0;
		}
		if (grid[row - 1][col] == state) {
			return 1;
		}
		return 0;
	}

	private int lookDown(int row, int col, CellState state) {
		if (row == grid.length - 1) {
			return 0;
		}
		if (grid[row + 1][col] == state) {
			return 1;
		}
		return 0;
	}

	private int lookUpRight(int row, int col, CellState state) {
		if (row == 0 || col == grid[0].length - 1) {
			return 0;
		}
		if (grid[row - 1][col + 1] == state) {
			return 1;
		}
		return 0;
	}

	private int lookRight(int row, int col, CellState state) {
		if (col == grid[0].length - 1) {
			return 0;
		}
		if (grid[row][col + 1] == state) {
			return 1;
		}
		return 0;
	}

	private int lookDownRight(int row, int col, CellState state) {
		if (row == grid.length - 1 || col == grid[0].length - 1) {
			return 0;
		}
		if (grid[row + 1][col + 1] == state) {
			return 1;
		}
		return 0;
	}

	private int lookUpLeft(int row, int col, CellState state) {
		if (row == 0 || col == 0) {
			return 0;
		}
		if (grid[row - 1][col - 1] == state) {
			return 1;
		}
		return 0;
	}

	private int lookLeft(int row, int col, CellState state) {
		if (col == 0) {
			return 0;
		}
		if (grid[row][col - 1] == state) {
			return 1;
		}
		return 0;
	}

	private int lookDownLeft(int row, int col, CellState state) {
		if (row == grid.length - 1 || col == 0) {
			return 0;
		}
		if (grid[row + 1][col - 1] == state) {
			return 1;
		}
		return 0;
	}

}
