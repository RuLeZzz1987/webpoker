package com.rulezzz.pkr.core.card;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.card.CardSuit;

public class CardTest {

    @Test
    public void testIsSameCard() {
        Card card1 = new Card(CardSuit.CLUBS, 'A');
        Card card2 = new Card(CardSuit.CLUBS, 'A');
        assertTrue(card1.equals(card2));
        
        card2 = new Card(CardSuit.CLUBS, 'K');
        assertFalse(card1.equals(card2));
        
        assertFalse(card1.equals(null));
        assertFalse(card1.equals(new Object()));
    }

    @Test
    public void testCreateCard() {
        Card card = new Card(CardSuit.CLUBS, 'A');
        assertEquals(14, card.getScore());
        
        card = new Card(CardSuit.CLUBS, 'K');
        assertEquals(13, card.getScore());
        
        card = new Card(CardSuit.CLUBS, 'Q');
        assertEquals(12, card.getScore());
        
        card = new Card(CardSuit.CLUBS, 'J');
        assertEquals(11, card.getScore());
        
        card = new Card(CardSuit.CLUBS, 'T');
        assertEquals(10, card.getScore());
        
        for (int i = 2; i <= 9; i++) {
            card = new Card(CardSuit.CLUBS, (char) (i + Card.DELTAUT_FCHAR));
            assertEquals(i, card.getScore());
        }
    }

    @Test
    public void testGetCharSuit() {
        assertEquals('h', new Card(CardSuit.HEART, 'K').getSuit().getCharSuit());
        assertEquals('s', new Card(CardSuit.SPADES, '3').getSuit().getCharSuit());
        assertEquals('d', new Card(CardSuit.DIAMOND, '7').getSuit().getCharSuit());
        assertEquals('c', new Card(CardSuit.CLUBS, 'J').getSuit().getCharSuit());
    }

    @Test
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
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetIllegalRate() {
        assertEquals(' ', new Card(CardSuit.CLUBS, 1, 1).getRate());
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
