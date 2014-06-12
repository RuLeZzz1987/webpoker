package com.rulezzz.pkr.core;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



public class PlayerBoxTest {
    
    private Hand hand;
    private PlayerBox box;
    
    @Before
    public void setUp() {
        hand = new Hand();
        hand.add(new Card(CardSuit.CLUBS, 'K'));
        hand.add(new Card(CardSuit.HEART, 'J'));
        hand.add(new Card(CardSuit.SPADES, '6'));
        hand.add(new Card(CardSuit.CLUBS, '6'));
        hand.add(new Card(CardSuit.DIAMOND, '4'));
    }
    
    @Test (expected = ArithmeticException.class)
    public void testDrawCards() {
        box = new PlayerBox();
        box.setHand(hand);
        List<Boolean> drawList = new LinkedList<Boolean>();
        drawList.add(false);
        drawList.add(false);
        drawList.add(true);
        drawList.add(true);
        drawList.add(false);
        box.drawCards(drawList);
        assertEquals(3, box.getCountOfNeededCards());
        assertEquals(2, box.getHand().getCards().size());
        List<Card> newCards = new LinkedList<Card>();
        newCards.add(new Card(CardSuit.HEART, 'Q'));
        newCards.add(new Card(CardSuit.DIAMOND, 'Q'));
        newCards.add(new Card(CardSuit.HEART, '3'));
        box.setCardsAfterDraw(newCards);
        assertEquals(5, box.getHand().getCards().size());
        drawList.remove(0);
        box.drawCards(drawList);
    }
    
    @Test
    public void testAnte() {
        box = new PlayerBox(hand, 50);
        assertEquals(50, box.getAnte());
    }
        
}
