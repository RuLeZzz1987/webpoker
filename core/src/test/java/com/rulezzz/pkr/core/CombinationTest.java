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
		assertEquals(null, combo.getCombCode());
		assertEquals(null, combo.toString());
		assertEquals(-1, combo.getHighness());
	}
	
	@Test
	public void testHashCodeCombination(){
		Combination combo = new Combination("6 14 13 6 3 2 ");
		assertEquals(31 + combo.getCombCode().hashCode(), combo.hashCode());
		combo = new Combination("draw");
		assertEquals(31, combo.hashCode());
	}
	
	@Test
	public void testEqualsCombination() {
		Combination combo = new Combination("6 14 13 6 3 2 ");
		Combination combo2 = new Combination("6 14 13 6 3 2 ");
		assertEquals(true, combo.equals(combo2));
		combo2 = new Combination("1 14 13 6 3 2 ");
		assertEquals(false, combo.equals(combo2));
		assertEquals(true, combo.equals(combo));
		combo2 = null;
		assertEquals(false, combo.equals(combo2));
		combo2 = new Combination("draw");
		assertEquals(false, combo2.equals(combo));
		Object comb = new Object();
		assertEquals(false, combo.equals(comb));
		combo = new Combination("draw");
		assertEquals(true, combo2.equals(combo));
	}
}
