package com.rulezzz.pkr.core.base.structures;

import static org.junit.Assert.*;

import java.util.ArrayList;
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

    @Test(expected = ArithmeticException.class)
    public void testFoldMoreCardsThenHandHas() {
        box = new PlayerBox();
        box.setHand(hand);
        box.drawCards(new Card(CardSuit.CLUBS, 'K'), new Card(CardSuit.HEART, 'J'), new Card(CardSuit.DIAMOND, '4'),
                new Card(CardSuit.DIAMOND, '2'), new Card(CardSuit.HEART, 'T'), new Card(CardSuit.SPADES, '9'),
                new Card(CardSuit.CLUBS, '3'));
    }
    
    @Test(expected = ArithmeticException.class)
    public void testFoldMoreCardsThenHandHasByList() {
        box = new PlayerBox();
        box.setHand(hand);
        List<Card> drawList = new ArrayList<Card>();
        drawList.add(new Card(CardSuit.CLUBS, 'K'));
        drawList.add(new Card(CardSuit.HEART, 'J'));
        drawList.add(new Card(CardSuit.DIAMOND, '4'));
        drawList.add(new Card(CardSuit.DIAMOND, '2'));
        drawList.add(new Card(CardSuit.HEART, 'T'));
        drawList.add(new Card(CardSuit.SPADES, '9'));
        drawList.add(new Card(CardSuit.CLUBS, '3'));
        box.drawCards(drawList);
    }

    @Test
    public void testAnte() {
        box = new PlayerBox(hand, 50);
        assertEquals(50, box.getAnte());
    }

}
