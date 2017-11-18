package com.paigeruppel.coderetreat;

import static com.paigeruppel.coderetreat.CellState.ALIVE;
import static com.paigeruppel.coderetreat.CellState.DEAD;
import static com.paigeruppel.coderetreat.CellState.ZOMBIE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
public class BoardTest {

	private Board underTest;

	@Before
	public void setup() {
		CellState[][] blinker = { { DEAD, DEAD, DEAD, DEAD, DEAD }, 
								{ DEAD, DEAD, ALIVE, DEAD, DEAD },
								{ DEAD, DEAD, ALIVE, DEAD, DEAD }, 
								{ DEAD, DEAD, ALIVE, DEAD, DEAD },
								{ DEAD, DEAD, DEAD, DEAD, DEAD } };

		underTest = new Board(blinker);
	}

	@Test
	public void shouldReturn2LivingNeighborsForCoord22() {
		assertThat(underTest.countNeighbors(2, 2, ALIVE), is(2));
	}

	@Test
	public void shouldReturn1LivingNeighborsForCoordThreeTwo() {
		assertThat(underTest.countNeighbors(3, 2, ALIVE), is(1));
	}

	@Test
	public void shouldReturn3LivingNeighborsForCoordTwoOne() {
		assertThat(underTest.countNeighbors(2, 1, ALIVE), is(3));
	}

	@Test
	public void shouldReturn3LivingNeighborsForCoordTwoThree() {
		assertThat(underTest.countNeighbors(2, 3, ALIVE), is(3));
	}

	@Test
	public void shouldReturn1LivingNeighborForCoordZeroTwo() {
		assertThat(underTest.countNeighbors(0, 2, ALIVE), is(1));
	}

	@Test
	public void shouldReturn1LivingNeighborForCoord42() {
		assertThat(underTest.countNeighbors(4, 2, ALIVE), is(1));
	}

	@Test
	public void shouldReturn0LivingNeighborForCoord20() {
		assertThat(underTest.countNeighbors(2, 0, ALIVE), is(0));
	}

	@Test
	public void shouldReturn0LivingNeighborForCoord24() {
		assertThat(underTest.countNeighbors(2, 4, ALIVE), is(0));
	}

	@Test
	public void tickIfACellIsAliveAndHasTwoLivingNeighborsShouldRemainAlive() {
		CellState[][] grid = {{ALIVE, ALIVE, ALIVE}};
		CellState[][] result = {{DEAD, ALIVE, DEAD}};
		underTest = new Board(grid);
		underTest.tick();
		assertThat(underTest.getGrid(), is(result));
	}

	@Test
	public void tickIfACellIsDeadAndHasTwoLivingNeighborsShouldRemainDead() {
		CellState[][] grid = {{ALIVE, DEAD, ALIVE}};
		CellState[][] result = {{DEAD, DEAD, DEAD}};
		underTest = new Board(grid);
		underTest.tick();
		assertThat(underTest.getGrid(), is(result));
	}
	
	@Test
	public void tickIfACellHasIsAliveAndHasExactlyThreeLivingNeighborsShouldStayAlive() {
		CellState[][] grid = {{ALIVE, ALIVE, ALIVE},
								{DEAD,ALIVE,DEAD}};
		CellState[][] result = {{ALIVE, ALIVE, ALIVE},
								{ALIVE,ALIVE,ALIVE}};
		underTest = new Board(grid);
		underTest.tick();
		assertThat(underTest.getGrid(), is(result));
	}
	
	@Test
	public void tickIfACellIsDeadAndHasExactlyThreeNeighborsItShouldComeAlive() {
		CellState[][] grid = {{ALIVE, ALIVE, ALIVE},
								{DEAD,ALIVE,DEAD}};
		CellState[][] result = {{ALIVE, ALIVE, ALIVE},
								{ALIVE,ALIVE,ALIVE}};
		underTest = new Board(grid);
		underTest.tick();
		assertThat(underTest.getGrid(), is(result));
	}
	
	@Test
	public void tickTestAlreadyPassing() {
		CellState[][] grid = {{ALIVE, ALIVE, ALIVE},
								{ALIVE,ALIVE,ALIVE}};
		CellState[][] result = {{ALIVE, DEAD, ALIVE},
								{ALIVE,DEAD,ALIVE}};
		underTest = new Board(grid);
		underTest.tick();
		assertThat(underTest.getGrid(), is(result));
	}
	
	@Test
	public void tick3x3BlinkerTestAlreadyPassing() {
		CellState[][] grid = {{DEAD, ALIVE, DEAD},
								{DEAD,ALIVE,DEAD},
								{DEAD,ALIVE,DEAD}};
		CellState[][] result = {{DEAD,DEAD, DEAD},
								{ALIVE,ALIVE,ALIVE},
								{DEAD,DEAD,DEAD}};
		underTest = new Board(grid);
		underTest.tick();
		assertThat(underTest.getGrid(), is(result));
	}
	
	@Test
	public void ifCellIsDeadAndHasExacltyFourNeighborsItBecomesAZombie() {
		CellState[][] grid = {{DEAD, ALIVE, DEAD},
							  {ALIVE,DEAD,ALIVE},
							  {DEAD,ALIVE,DEAD}};
		CellState[][] result = {{DEAD,ALIVE, DEAD},
								{ALIVE,ZOMBIE,ALIVE},
								{DEAD,ALIVE,DEAD}};
		underTest = new Board(grid);
		underTest.tick();
		assertThat(underTest.getGrid(), is(result));
	}
	
	@Test
	public void ifCellIsAliveAndHasExacltyTwoZombieNeighborsItBecomesAZombie() {
		CellState[][] grid = {{DEAD, ALIVE, ZOMBIE},
							  {ALIVE,ALIVE,ZOMBIE},
							  {DEAD,ALIVE,DEAD}};
		CellState[][] result = {{ALIVE,ZOMBIE, ZOMBIE},
								{ALIVE,ZOMBIE,ZOMBIE},
								{ALIVE,ALIVE,DEAD}};
		underTest = new Board(grid);
		underTest.tick();
		assertThat(underTest.getGrid(), is(result));
	}
	
	@Test
	public void ifCellIsAZombieAndHasNoLiveNeighborsItBecomesDead() {
		CellState[][] grid = {{ALIVE,ZOMBIE, ZOMBIE},
								{ALIVE,ZOMBIE,ZOMBIE},
								{ALIVE,ALIVE,DEAD}};
		CellState[][] result = {{ZOMBIE,ZOMBIE, DEAD},
								{ZOMBIE,ZOMBIE,ZOMBIE},
								{ALIVE,ZOMBIE,DEAD}};
		underTest = new Board(grid);
		underTest.tick();
		assertThat(underTest.getGrid(), is(result));
	}
	
	
}
