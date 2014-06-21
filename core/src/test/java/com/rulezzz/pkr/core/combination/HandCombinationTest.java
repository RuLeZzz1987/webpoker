package com.rulezzz.pkr.core.combination;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rulezzz.pkr.core.Card;
import com.rulezzz.pkr.core.CardSuit;
import com.rulezzz.pkr.core.Hand;

public class HandCombinationTest {

    Hand hand;
    
    @Before
    public void setUp() {
        hand = new Hand();
    }
    
    @Test
    public void testIComboDoesntQualify() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'J'));
        hand.add(new Card(CardSuit.CLUBS, '6'));
        hand.add(new Card(CardSuit.CLUBS, '3'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(DoesntQualify.class, hand.getHandICombination().getClass());
    }
    
    @Test
    public void testIComboAK() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.CLUBS, '6'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        hand.add(new Card(CardSuit.CLUBS, '3'));
        hand.add(new Card(CardSuit.DIAMOND, 'K'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.CLUBS, '3'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        Collections.sort(checkKickersList);
        Collections.reverse(checkKickersList);
        assertEquals(AceKing.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
    }
    
    @Test
    public void testIComboPairAABCD() {
        hand.add(new Card(CardSuit.CLUBS, 'K'));
        hand.add(new Card(CardSuit.DIAMOND, 'K'));
        hand.add(new Card(CardSuit.CLUBS, '7'));
        hand.add(new Card(CardSuit.CLUBS, '5'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'K'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '7'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '5'));
        Collections.sort(checkKickersList);
        Collections.reverse(checkKickersList);
        assertEquals(Pair.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        
    }
    
    @Test
    public void testIComboPairABBCD() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'J'));
        hand.add(new Card(CardSuit.CLUBS, 'J'));
        hand.add(new Card(CardSuit.CLUBS, '3'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'J'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '3'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(Pair.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
    }
    
    @Test
    public void testCombinationPairABCCD() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'J'));
        hand.add(new Card(CardSuit.CLUBS, '6'));
        hand.add(new Card(CardSuit.HEART, '6'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '6'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(Pair.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
    }
    
    @Test
    public void testCombinationPairABCDD() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'J'));
        hand.add(new Card(CardSuit.CLUBS, '6'));
        hand.add(new Card(CardSuit.HEART, '2'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '6'));
        assertEquals(Pair.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
    }
}
