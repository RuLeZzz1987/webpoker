package com.rulezzz.pkr.core.combination;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rulezzz.pkr.core.base.structures.Hand;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.card.CardSuit;

public class CombinationCompareTest {

    private Hand hand, otherHand;

    @Test
    public void testAceKingCompare(){
        hand = new Hand();
        otherHand = new Hand();
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'K'));
        hand.add(new Card(CardSuit.HEART, '5'));
        hand.add(new Card(CardSuit.SPADES, '3'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        otherHand.add(new Card(CardSuit.HEART, 'A'));
        otherHand.add(new Card(CardSuit.SPADES, 'K'));
        otherHand.add(new Card(CardSuit.CLUBS, 'J'));
        otherHand.add(new Card(CardSuit.DIAMOND, 'T'));
        otherHand.add(new Card(CardSuit.CLUBS, '7'));
        assertEquals(-1, hand.compareTo(otherHand));
        
        
    }
}
