package com.rulezzz.pkr.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class CombinationTest {

	@Test(expected = IllegalArgumentException.class)
	public void testSetErrorCombinationName() {
		new Combination("K 14 13 6 3 2 ");
	}
	
	@Test
	public void testCompareCombinations(){
		assertEquals(1, new Combination("6 14 13 6 3 2 ").compareTo(new Combination("1 14 13 6 3 2 ")));
		assertEquals(-1, new Combination("1 6 3 2 ").compareTo(new Combination("2 13 13 7 5 2 ")));
		assertEquals(0, new Combination("5 13 ").compareTo(new Combination("5 13 ")));		
	}
	
	@Test
	public void testDrawStatusCombination(){
		Combination combo = new Combination("draw");
		assertEquals("draw", combo.getCombCode());
		assertEquals("draw", combo.toString());
		assertEquals(-1, combo.getHighness());
	}
}
