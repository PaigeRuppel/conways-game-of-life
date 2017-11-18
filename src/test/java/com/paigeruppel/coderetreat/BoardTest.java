package com.paigeruppel.coderetreat;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static com.paigeruppel.coderetreat.CellState.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board underTest;

	@Before
	public void setup() {
		CellState[][] grid = { { DEAD, DEAD, DEAD, DEAD, DEAD }, { DEAD, DEAD, ALIVE, DEAD, DEAD },
		{ DEAD, DEAD, ALIVE, DEAD, DEAD }, { DEAD, DEAD, ALIVE, DEAD, DEAD }, { DEAD, DEAD, DEAD, DEAD, DEAD } };

		underTest = new Board(grid);
	}

	@Test
	public void shouldReturnCoord00False() {
		assertEquals(DEAD, underTest.getGridCoords(0, 0));
	}
	
	@Test
	public void shouldReturnCoord12True() {
		assertEquals(ALIVE, underTest.getGridCoords(1, 2));
	}
	
	@Test
	public void shouldReturn2LivingNeighborsForCoord22() {
		assertThat(underTest.countLivingNeighbors(2,2), is(2));
	}

	@Test
	public void shouldReturn1LivingNeighborsForCoordThreeTwo() {
		assertThat(underTest.countLivingNeighbors(3,2), is(1));
	}
	
	@Test
	public void shouldReturn3LivingNeighborsForCoordTwoOne() {
		assertThat(underTest.countLivingNeighbors(2, 1), is(3));
	}

	@Test
	public void shouldReturn3LivingNeighborsForCoordTwoThree() {
		assertThat(underTest.countLivingNeighbors(2, 3), is(3));
	}

	@Test
	public void shouldReturn1LivingNeighborForCoordZeroTwo() {
		assertThat(underTest.countLivingNeighbors(0, 2), is(1));
	}
	
	@Test
	public void shouldReturn1LivingNeighborForCoord42() {
		assertThat(underTest.countLivingNeighbors(4, 2), is(1));
	}
	
	@Test
	public void shouldReturn0LivingNeighborForCoord20() {
		assertThat(underTest.countLivingNeighbors(2, 0), is(0));
	}
	
	@Test
	public void shouldReturn0LivingNeighborForCoord24() {
		assertThat(underTest.countLivingNeighbors(2, 4), is(0));
	}
	
	@Test
	public void tickOnBlinkerShouldRotateLivingCells90Degrees() {
		underTest.tick();
		
		assertThat(underTest.getGridCoords(1, 1), is(DEAD));
		assertThat(underTest.getGridCoords(1, 2), is(DEAD));
		assertThat(underTest.getGridCoords(1, 3), is(DEAD));
		assertThat(underTest.getGridCoords(2, 1), is(ALIVE));
		assertThat(underTest.getGridCoords(2, 2), is(ALIVE));
		assertThat(underTest.getGridCoords(2, 3), is(ALIVE));
		assertThat(underTest.getGridCoords(3, 1), is(DEAD));
		assertThat(underTest.getGridCoords(3, 2), is(DEAD));
		assertThat(underTest.getGridCoords(3, 3), is(DEAD));
	}
	

	@Test
	public void twoTicksOnBlinkerShouldRestoreToInitialState() {
		underTest.tick();
		underTest.tick();
		
		assertThat(underTest.getGridCoords(1, 1), is(DEAD));
		assertThat(underTest.getGridCoords(1, 2), is(ALIVE));
		assertThat(underTest.getGridCoords(1, 3), is(DEAD));
		assertThat(underTest.getGridCoords(2, 1), is(DEAD));
		assertThat(underTest.getGridCoords(2, 2), is(ALIVE));
		assertThat(underTest.getGridCoords(2, 3), is(DEAD));
		assertThat(underTest.getGridCoords(3, 1), is(DEAD));
		assertThat(underTest.getGridCoords(3, 2), is(ALIVE));
		assertThat(underTest.getGridCoords(3, 3), is(DEAD));
	}
	
}
