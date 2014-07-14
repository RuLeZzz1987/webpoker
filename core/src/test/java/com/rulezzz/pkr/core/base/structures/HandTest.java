package com.rulezzz.pkr.core.base.structures;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.rulezzz.pkr.core.base.engines.GameMath;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.card.CardSuit;

public class HandTest {

    private Hand hand;

    @Before
    public void setUp() {
        hand = new Hand();
    }
    
    @Test
    public void testRemoveCardFromHand() {
        hand = new Hand(new Card(CardSuit.CLUBS, 'A', 14),
                        new Card(CardSuit.HEART, 'K', 13), 
                        new Card(CardSuit.SPADES, '6', 6), 
                        new Card(CardSuit.DIAMOND, '3', 3), 
                        new Card(CardSuit.DIAMOND, '2', 2));
        hand.removeCardByMask(1, 0, 1, 0, 1);
        assertEquals(2, hand.getCards().size());
        assertEquals(new Card(CardSuit.HEART, 'K', 13), hand.getCards().get(0));
        assertEquals(new Card(CardSuit.DIAMOND, '3', 3), hand.getCards().get(1));
    }

    @Test
    public void testHandCreate() {
        hand = new Hand(new Card(CardSuit.CLUBS, 'A', 14),
                new Card(CardSuit.HEART, 'K', 13), new Card(CardSuit.SPADES,
                        '6', 6), new Card(CardSuit.DIAMOND, '3', 3));
        assertEquals(4, hand.getCards().size());

    }

    @Test
    public void testPermutation() {
        hand.add(new Card(CardSuit.CLUBS, 'A', 14));
        hand.add(new Card(CardSuit.HEART, 'K', 13));
        hand.add(new Card(CardSuit.SPADES, '6', 6));
        hand.add(new Card(CardSuit.DIAMOND, '3', 3));
        hand.add(new Card(CardSuit.CLUBS, '2', 2));
        new GameMath().generateCombinations(hand.getCards(), 3);
    }

    @Test
    public void testDrawCards() throws IOException {
        hand.getCards().clear();
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'J'));
        hand.add(new Card(CardSuit.CLUBS, 'J'));
        hand.add(new Card(CardSuit.CLUBS, '3'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        PlayerBox box = new PlayerBox(hand, 10);
        
        box.drawCards(new Card(CardSuit.CLUBS, 'A'), new Card(CardSuit.CLUBS, '3'), new Card(CardSuit.CLUBS, '2'));
        assertEquals(new Card(CardSuit.DIAMOND, 'J'), box.getHandHigheness(0)
                .getCards().get(0));
        assertEquals(new Card(CardSuit.CLUBS, 'J'), box.getHandHigheness(0)
                .getCards().get(1));
    }
}
