package com.paigeruppel.coderetreat;

import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {

	private Cell underTest;
	
	@Test
	public void aliveCellShouldReturnTrue() {
		underTest = new Cell(true);
		assertTrue(underTest.isAlive());
	}
}
