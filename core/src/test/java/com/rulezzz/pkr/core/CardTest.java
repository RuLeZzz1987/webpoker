package com.rulezzz.pkr.core;

import static org.junit.Assert.*;
import org.junit.Test;

import com.rulezzz.pkr.core.Card;
import com.rulezzz.pkr.core.CardSuit;

public class CardTest {

	@Test
	public void testIsSameCard() {
		Card card1 = new Card(CardSuit.CLUBS, 'A');
		Card card2 = new Card(CardSuit.CLUBS, 'A');
		assertEquals(true, card1.equals(card2));
		card2 = new Card(CardSuit.DIAMOND, 'A');
		assertEquals(false, card1.equals(card2));
		card2 = new Card(CardSuit.CLUBS, 'K');
		assertEquals(false, card1.equals(card2));

	}

	@Test
	public void testCreateCard() {
		Card card1 = new Card(CardSuit.CLUBS, 'A');
		assertEquals(14, card1.getScore());
		card1 = new Card(CardSuit.CLUBS, 'K');
		assertEquals(13, card1.getScore());
		card1 = new Card(CardSuit.CLUBS, 'Q');
		assertEquals(12, card1.getScore());
		card1 = new Card(CardSuit.CLUBS, 'J');
		assertEquals(11, card1.getScore());
		card1 = new Card(CardSuit.CLUBS, 'T');
		assertEquals(10, card1.getScore());
		for (int i = 2; i <= 9; i++) {
			card1 = new Card(CardSuit.CLUBS, (char) (i + 48));
			assertEquals(i, card1.getScore());
		}
	}

	@Test
	public void testGetCharSuit() {
		assertEquals('h', new Card(CardSuit.HEART, 'K').getCharSuit());
		assertEquals('s', new Card(CardSuit.SPADES, '3').getCharSuit());
		assertEquals('d', new Card(CardSuit.DIAMOND, '7').getCharSuit());
		assertEquals('c', new Card(CardSuit.CLUBS, 'J').getCharSuit());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetRate() {
		assertEquals('2', new Card(CardSuit.CLUBS, 2, 2).getRate());
		assertEquals('3', new Card(CardSuit.CLUBS, 3, 3).getRate());
		assertEquals('4', new Card(CardSuit.CLUBS, 4, 4).getRate());
		assertEquals('5', new Card(CardSuit.CLUBS, 5, 5).getRate());
		assertEquals('6', new Card(CardSuit.CLUBS, 6, 6).getRate());
		assertEquals('7', new Card(CardSuit.CLUBS, 7, 7).getRate());
		assertEquals('8', new Card(CardSuit.CLUBS, 8, 8).getRate());
		assertEquals('9', new Card(CardSuit.CLUBS, 9, 9).getRate());
		assertEquals('T', new Card(CardSuit.CLUBS, 10, 10).getRate());
		assertEquals('J', new Card(CardSuit.CLUBS, 11, 11).getRate());
		assertEquals('Q', new Card(CardSuit.CLUBS, 12, 12).getRate());
		assertEquals('K', new Card(CardSuit.CLUBS, 13, 13).getRate());
		assertEquals('A', new Card(CardSuit.CLUBS, 14, 14).getRate());
		assertEquals(' ', new Card(CardSuit.CLUBS, 15, 15).getRate());
	}

	@Test
	public void testGetStringCard(){
		assertEquals("cA", new Card(CardSuit.CLUBS, 'A').getStringCard());
		assertEquals("cJ", new Card(CardSuit.CLUBS, 'J').getStringCard());
		assertEquals("hT", new Card(CardSuit.HEART, 'T').getStringCard());
		assertEquals("d9", new Card(CardSuit.DIAMOND, '9').getStringCard());
		assertEquals("sK", new Card(CardSuit.SPADES, 'K').getStringCard());
		assertEquals("h8", new Card(CardSuit.HEART, '8').getStringCard());
		assertEquals("d2", new Card(CardSuit.DIAMOND, '2').getStringCard());
		assertEquals("s3", new Card(CardSuit.SPADES, '3').getStringCard());
		assertEquals("c4", new Card(CardSuit.CLUBS, '4').getStringCard());
		assertEquals("h5", new Card(CardSuit.HEART, '5').getStringCard());
		assertEquals("d6", new Card(CardSuit.DIAMOND, '6').getStringCard());
		assertEquals("s7", new Card(CardSuit.SPADES, '7').getStringCard());
		assertEquals("cQ", new Card(CardSuit.CLUBS, 'Q').getStringCard());
	}
}
