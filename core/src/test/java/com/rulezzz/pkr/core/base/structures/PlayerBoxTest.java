package com.rulezzz.pkr.core.base.structures;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rulezzz.pkr.core.base.structures.Hand;
import com.rulezzz.pkr.core.base.structures.PlayerBox;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.card.CardSuit;

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
    
    @Test
    public void testDrawCards() {
        box = new PlayerBox();
        box.setHand(hand);
        List<Boolean> drawList = new LinkedList<Boolean>();
        drawList.add(false);
        drawList.add(false);
        drawList.add(true);
        drawList.add(true);
        drawList.add(false);
        box.drawCards(new Card(CardSuit.CLUBS, 'K'), new Card(CardSuit.HEART, 'J'), new Card(CardSuit.DIAMOND, '4'));
        assertEquals(3, box.getCountOfNeededCards());
        assertEquals(2, box.getHand().getCards().size());
        List<Card> newCards = new LinkedList<Card>();
        newCards.add(new Card(CardSuit.HEART, 'Q'));
        newCards.add(new Card(CardSuit.DIAMOND, 'Q'));
        newCards.add(new Card(CardSuit.HEART, '3'));
        box.setCardsAfterDraw(newCards);
        assertEquals(5, box.getHand().getCards().size());
    }
    
    @Test
    public void testAnte() {
        box = new PlayerBox(hand, 50);
        assertEquals(50, box.getAnte());
    }
        
}
