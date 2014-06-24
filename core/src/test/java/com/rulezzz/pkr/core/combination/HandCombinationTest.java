package com.rulezzz.pkr.core.combination;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rulezzz.pkr.core.engine.CardSuit;
import com.rulezzz.pkr.core.engine.Hand;

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
        assertEquals(0, hand.getHandICombination().getHighness());
        assertEquals("Doesn't qualifier", hand.getHandICombination().getName());
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
        assertEquals(1, hand.getHandICombination().getHighness());
        assertEquals("Ace & King", hand.getHandICombination().getName());
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
        assertEquals(2, hand.getHandICombination().getHighness());
        assertEquals("Pair", hand.getHandICombination().getName());
        
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
        assertEquals(2, hand.getHandICombination().getHighness());
        assertEquals("Pair", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboPairABCCD() {
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
        assertEquals(2, hand.getHandICombination().getHighness());
        assertEquals("Pair", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboPairABCDD() {
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
        assertEquals(2, hand.getHandICombination().getHighness());
        assertEquals("Pair", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboTwoPairsAABBC() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'A'));
        hand.add(new Card(CardSuit.CLUBS, '6'));
        hand.add(new Card(CardSuit.HEART, '6'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(TwoPairs.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(3, hand.getHandICombination().getHighness());
        assertEquals("Two Pairs", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboTwoPairsAABCC() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'A'));
        hand.add(new Card(CardSuit.CLUBS, '7'));
        hand.add(new Card(CardSuit.HEART, '6'));
        hand.add(new Card(CardSuit.CLUBS, '6'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '7'));
        assertEquals(TwoPairs.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(3, hand.getHandICombination().getHighness());
        assertEquals("Two Pairs", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboTwoPairsABBCC() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, '7'));
        hand.add(new Card(CardSuit.CLUBS, '7'));
        hand.add(new Card(CardSuit.HEART, '6'));
        hand.add(new Card(CardSuit.CLUBS, '6'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '7'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'A'));
        assertEquals(TwoPairs.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(3, hand.getHandICombination().getHighness());
        assertEquals("Two Pairs", hand.getHandICombination().getName());
    }
    
    @Test
    public void testCombinationSetAAABC() {
        hand.add(new Card(CardSuit.CLUBS, '7'));
        hand.add(new Card(CardSuit.DIAMOND, '7'));
        hand.add(new Card(CardSuit.SPADES, '7'));
        hand.add(new Card(CardSuit.CLUBS, '5'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '7'));
        checkKickersList.add(new Card(CardSuit.SPADES, '5'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(TreeOfKind.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(4, hand.getHandICombination().getHighness());
        assertEquals("Tree of a Kind", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboSetABBBC() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'J'));
        hand.add(new Card(CardSuit.CLUBS, 'J'));
        hand.add(new Card(CardSuit.SPADES, 'J'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'J'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(TreeOfKind.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(4, hand.getHandICombination().getHighness());
        assertEquals("Tree of a Kind", hand.getHandICombination().getName());
    }
        
    @Test
    public void testIComboSetABCCC() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'J'));
        hand.add(new Card(CardSuit.DIAMOND, '2'));
        hand.add(new Card(CardSuit.SPADES, '2'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        assertEquals(TreeOfKind.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(4, hand.getHandICombination().getHighness());
        assertEquals("Tree of a Kind", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboFullHouseAAACC() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'A'));
        hand.add(new Card(CardSuit.HEART, 'A'));
        hand.add(new Card(CardSuit.SPADES, '2'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        assertEquals(FullHouse.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(7, hand.getHandICombination().getHighness());
        assertEquals("Full House", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboFullHouseAACCC() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'A'));
        hand.add(new Card(CardSuit.SPADES, '2'));
        hand.add(new Card(CardSuit.HEART, '2'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        assertEquals(FullHouse.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(7, hand.getHandICombination().getHighness());
        assertEquals("Full House", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboFourOfaKindAAAAB() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'A'));
        hand.add(new Card(CardSuit.HEART, 'A'));
        hand.add(new Card(CardSuit.SPADES, 'A'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        assertEquals(FourOfKind.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(8, hand.getHandICombination().getHighness());
        assertEquals("Four Of a Kind", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboFourOfaKindABBBB() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, '2'));
        hand.add(new Card(CardSuit.HEART, '2'));
        hand.add(new Card(CardSuit.SPADES, '2'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        assertEquals(FourOfKind.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(8, hand.getHandICombination().getHighness());
        assertEquals("Four Of a Kind", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboStraight() {
        hand.add(new Card(CardSuit.CLUBS, 'K'));
        hand.add(new Card(CardSuit.DIAMOND, 'Q'));
        hand.add(new Card(CardSuit.HEART, 'J'));
        hand.add(new Card(CardSuit.SPADES, 'T'));
        hand.add(new Card(CardSuit.CLUBS, '9'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'K'));
        assertEquals(Straight.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(5, hand.getHandICombination().getHighness());
        assertEquals("Straight", hand.getHandICombination().getName());

    }
    
    @Test
    public void testIComboWheelStraight() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, '5'));
        hand.add(new Card(CardSuit.HEART, '4'));
        hand.add(new Card(CardSuit.SPADES, '3'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '5'));
        assertEquals(Straight.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(5, hand.getHandICombination().getHighness());
        assertEquals("Straight", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboStraightFlush() {
        hand.add(new Card(CardSuit.CLUBS, 'K'));
        hand.add(new Card(CardSuit.CLUBS, 'Q'));
        hand.add(new Card(CardSuit.CLUBS, 'J'));
        hand.add(new Card(CardSuit.CLUBS, 'T'));
        hand.add(new Card(CardSuit.CLUBS, '9'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.CLUBS, 'K'));
        assertEquals(StraightFlush.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(9, hand.getHandICombination().getHighness());
        assertEquals("Straight Flush", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboWheelStraightFlush() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.CLUBS, '5'));
        hand.add(new Card(CardSuit.CLUBS, '4'));
        hand.add(new Card(CardSuit.CLUBS, '3'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.CLUBS, '5'));
        assertEquals(StraightFlush.class, hand.getHandICombination().getClass());
        assertEquals(checkKickersList, hand.getHandICombination().getKickersList());
        assertEquals(9, hand.getHandICombination().getHighness());
        assertEquals("Straight Flush", hand.getHandICombination().getName());
    }
    
    @Test
    public void testIComboRoyalFlush() {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.CLUBS, 'K'));
        hand.add(new Card(CardSuit.CLUBS, 'Q'));
        hand.add(new Card(CardSuit.CLUBS, 'J'));
        hand.add(new Card(CardSuit.CLUBS, 'T'));
        assertEquals(RoyalFlush.class, hand.getHandICombination().getClass());
        assertEquals(10, hand.getHandICombination().getHighness());
        assertEquals("Royal Flush", hand.getHandICombination().getName());
        
    }
    
    @Test
    public void testICombo(){
        
    }
}
