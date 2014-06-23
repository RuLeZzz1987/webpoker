package com.rulezzz.pkr.core.engine;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rulezzz.pkr.core.engine.Card;
import com.rulezzz.pkr.core.engine.CardSuit;

public class CardTest {

    @Test
    public void testIsSameCard() {
        Card card1 = new Card(CardSuit.CLUBS, 'A');
        Card card2 = new Card(CardSuit.CLUBS, 'A');
        assertEquals(true, card1.equals(card2));
        card2 = new Card(CardSuit.DIAMOND, 'A');
        assertEquals(true, card1.equals(card2));
        card2 = new Card(CardSuit.CLUBS, 'K');
        assertEquals(false, card1.equals(card2));
        card2 = null;
        assertEquals(false, card1.equals(card2));
        Object card3 = new Object();
        assertEquals(false, card1.equals(card3));

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
        assertEquals('h', new Card(CardSuit.HEART, 'K').getSuit().getCharSuit());
        assertEquals('s', new Card(CardSuit.SPADES, '3').getSuit().getCharSuit());
        assertEquals('d', new Card(CardSuit.DIAMOND, '7').getSuit().getCharSuit());
        assertEquals('c', new Card(CardSuit.CLUBS, 'J').getSuit().getCharSuit());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRate() {
        assertEquals(' ', new Card(CardSuit.CLUBS, 1, 1).getRate());
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
    public void testGetRate() {
        Card card = new Card(CardSuit.DIAMOND, '2');
        assertEquals('2', card.getRate());
        card = new Card(CardSuit.DIAMOND, '3');
        assertEquals('3', card.getRate());
        card = new Card(CardSuit.DIAMOND, '4');
        assertEquals('4', card.getRate());
        card = new Card(CardSuit.DIAMOND, '5');
        assertEquals('5', card.getRate());
        card = new Card(CardSuit.DIAMOND, '6');
        assertEquals('6', card.getRate());
        card = new Card(CardSuit.DIAMOND, '7');
        assertEquals('7', card.getRate());
        card = new Card(CardSuit.DIAMOND, '8');
        assertEquals('8', card.getRate());
        card = new Card(CardSuit.DIAMOND, '9');
        assertEquals('9', card.getRate());
        card = new Card(CardSuit.DIAMOND, 'T');
        assertEquals('T', card.getRate());
        card = new Card(CardSuit.DIAMOND, 'J');
        assertEquals('J', card.getRate());
        card = new Card(CardSuit.DIAMOND, 'Q');
        assertEquals('Q', card.getRate());
        card = new Card(CardSuit.DIAMOND, 'K');
        assertEquals('K', card.getRate());
        card = new Card(CardSuit.DIAMOND, 'A');
        assertEquals('A', card.getRate());
    }

    @Test
    public void testGetStringCard() {
        assertEquals("cA", new Card(CardSuit.CLUBS, 'A').toString());
        assertEquals("cJ", new Card(CardSuit.CLUBS, 'J').toString());
        assertEquals("hT", new Card(CardSuit.HEART, 'T').toString());
        assertEquals("d9", new Card(CardSuit.DIAMOND, '9').toString());
        assertEquals("sK", new Card(CardSuit.SPADES, 'K').toString());
        assertEquals("h8", new Card(CardSuit.HEART, '8').toString());
        assertEquals("d2", new Card(CardSuit.DIAMOND, '2').toString());
        assertEquals("s3", new Card(CardSuit.SPADES, '3').toString());
        assertEquals("c4", new Card(CardSuit.CLUBS, '4').toString());
        assertEquals("h5", new Card(CardSuit.HEART, '5').toString());
        assertEquals("d6", new Card(CardSuit.DIAMOND, '6').toString());
        assertEquals("s7", new Card(CardSuit.SPADES, '7').toString());
        assertEquals("cQ", new Card(CardSuit.CLUBS, 'Q').toString());
    }
}
