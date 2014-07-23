package com.rulezzz.pkr.core.combination;

import static org.junit.Assert.*;
import static com.rulezzz.pkr.core.combination.samples.ComboSamples.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;







import org.junit.Test;

import com.rulezzz.pkr.core.base.structures.Hand;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.card.CardSuit;


public class HandCombinationTest {
   
    private Hand getCloneHand(List<Card> cards) {
        Hand result = new Hand();
        result.add(cards);
        return result;
    }
    
    @Test
    public void testAceKingToString() {
        System.out.println(getDoesntQualifyOne().toString());
        System.out.println(getAceKingHigher().toString());
        System.out.println(getPairAABCD().toString());
        System.out.println(getTwoPairsAABBC().toString());
        System.out.println(getSetAAABC().toString());
        System.out.println(getStraight().toString());
        System.out.println(getStraightWheel().toString());
        System.out.println(getFlushHigher().toString());
        System.out.println(getFullHouseAAABB().toString());
        System.out.println(getCareAAAAB().toString());
        System.out.println(getStraightFlush().toString());
        System.out.println(getRoyalFlushTwo().toString());
    }
    
    @Test
    public void testIComboDoesntQualify() {
        assertEquals(DoesntQualify.class, getDoesntQualifyOne().getHandAbstractCombination().getClass());
        assertEquals(0, getDoesntQualifyOne().getHandAbstractCombination().getHighness());
        assertEquals("Doesn't qualifier", getDoesntQualifyOne().getHandAbstractCombination().getName());
        assertEquals(-1, getDoesntQualifyOne().compareTo(getAceKingHigher()));
        assertEquals(-1, getDoesntQualifyOne().compareTo(getPairAABCD()));
        assertEquals(-1, getDoesntQualifyOne().compareTo(getTwoPairsAABBC()));
        assertEquals(-1, getDoesntQualifyOne().compareTo(getSetAAABC()));
        assertEquals(-1, getDoesntQualifyOne().compareTo(getStraight()));
        assertEquals(-1, getDoesntQualifyOne().compareTo(getFlushHigher()));
        assertEquals(-1, getDoesntQualifyOne().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getDoesntQualifyOne().compareTo(getCareAAAAB()));
        assertEquals(-1, getDoesntQualifyOne().compareTo(getStraightFlush()));
        assertEquals(-1, getDoesntQualifyOne().compareTo(getRoyalFlushTwo()));
        assertEquals(0, getDoesntQualifyOne().compareTo(getDoesntQualifyTwo()));
        assertTrue(getDoesntQualifyOne().equals(getDoesntQualifyTwo()));
        assertFalse(getDoesntQualifyOne().equals(getRoyalFlushTwo()));
        assertFalse(getDoesntQualifyOne().equals(null));
        
    }

    @Test
    public void testIComboAK() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.HEART, 'Q'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '3'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        Collections.sort(checkKickersList);
        Collections.reverse(checkKickersList);
        assertEquals(AceKing.class, getAceKingHigher().getHandAbstractCombination().getClass());
        assertEquals(checkKickersList, getAceKingHigher().getHandAbstractCombination().getKickersList());
        assertEquals(1, getAceKingHigher().getHandAbstractCombination().getHighness());
        assertEquals("Ace & King", getAceKingHigher().getHandAbstractCombination().getName());
        assertEquals(1, getAceKingHigher().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getAceKingHigher().compareTo(getAceKingLower()));
        assertEquals(0, getAceKingHigher().compareTo(getCloneHand(getAceKingHigher().getCards()))); 
        assertEquals(-1, getAceKingHigher().compareTo(getPairAABCD()));
        assertEquals(-1, getAceKingHigher().compareTo(getTwoPairsAABBC()));
        assertEquals(-1, getAceKingHigher().compareTo(getSetAAABC()));
        assertEquals(-1, getAceKingHigher().compareTo(getStraight()));
        assertEquals(-1, getAceKingHigher().compareTo(getFlushHigher()));
        assertEquals(-1, getAceKingHigher().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getAceKingHigher().compareTo(getCareAAAAB()));
        assertEquals(-1, getAceKingHigher().compareTo(getStraightFlush()));
        assertEquals(-1, getAceKingHigher().compareTo(getRoyalFlushTwo()));
        assertTrue(getAceKingHigher().equals(getCloneHand(getAceKingHigher().getCards())));
        assertFalse(getAceKingHigher().equals(getRoyalFlushTwo()));
        assertFalse(getAceKingHigher().equals(null));
        assertEquals(1, getAceKingHigher().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testIComboPairAABCD() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'K'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '7'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '5'));
        Collections.sort(checkKickersList);
        Collections.reverse(checkKickersList);
        assertEquals(Pair.class, getPairAABCD().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getPairAABCD().getHandICombination().getKickersList());
        assertEquals(2, getPairAABCD().getHandAbstractCombination().getHighness());
        assertEquals("Pair", getPairAABCD().getHandAbstractCombination().getName());
        assertEquals(1, getPairAABCD().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getPairAABCD().compareTo(getAceKingLower()));
        assertEquals(1, getPairAABCD().compareTo(getPairABBCD()));
        assertEquals(0, getPairAABCD().compareTo(getCloneHand(getPairAABCD().getCards()))); 
        assertEquals(-1, getPairAABCD().compareTo(getTwoPairsAABBC()));
        assertEquals(-1, getPairAABCD().compareTo(getSetAAABC()));
        assertEquals(-1, getPairAABCD().compareTo(getStraight()));
        assertEquals(-1, getPairAABCD().compareTo(getFlushHigher()));
        assertEquals(-1, getPairAABCD().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getPairAABCD().compareTo(getCareAAAAB()));
        assertEquals(-1, getPairAABCD().compareTo(getStraightFlush()));
        assertEquals(-1, getPairAABCD().compareTo(getRoyalFlushTwo()));
        assertTrue(getPairAABCD().equals(getCloneHand(getPairAABCD().getCards())));
        assertFalse(getPairAABCD().equals(getRoyalFlushTwo()));
        assertFalse(getPairAABCD().equals(null));
        assertEquals(1, getPairAABCD().getHandAbstractCombination().getMultiplier());
        
    }
    
    @Test
    public void testIComboPairABBCD() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'J'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '3'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(Pair.class, getPairABBCD().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getPairABBCD().getHandICombination().getKickersList());
        assertEquals(2, getPairABBCD().getHandAbstractCombination().getHighness());
        assertEquals("Pair", getPairABBCD().getHandAbstractCombination().getName());
        assertEquals(1, getPairABBCD().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getPairABBCD().compareTo(getAceKingLower()));
        assertEquals(1, getPairABBCD().compareTo(getPairABCDD()));
        assertEquals(0, getPairABBCD().compareTo(getCloneHand(getPairABBCD().getCards())));
        assertEquals(-1, getPairABBCD().compareTo(getPairAABCD()));
        assertEquals(-1, getPairABBCD().compareTo(getTwoPairsAABBC()));
        assertEquals(-1, getPairABBCD().compareTo(getSetAAABC()));
        assertEquals(-1, getPairABBCD().compareTo(getStraight()));
        assertEquals(-1, getPairABBCD().compareTo(getFlushHigher()));
        assertEquals(-1, getPairABBCD().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getPairABBCD().compareTo(getCareAAAAB()));
        assertEquals(-1, getPairABBCD().compareTo(getStraightFlush()));
        assertEquals(-1, getPairABBCD().compareTo(getRoyalFlushTwo()));
        assertTrue(getPairABBCD().equals(getCloneHand(getPairABBCD().getCards())));
        assertFalse(getPairABBCD().equals(getPairAABCD()));
        assertFalse(getPairABBCD().equals(null));
        assertEquals(1, getPairABBCD().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testIComboPairABCCD() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '6'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(Pair.class, getPairABCCD().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getPairABCCD().getHandICombination().getKickersList());
        assertEquals(2, getPairABCCD().getHandAbstractCombination().getHighness());
        assertEquals("Pair", getPairABCCD().getHandAbstractCombination().getName());
        assertEquals(1, getPairABCCD().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getPairABCCD().compareTo(getAceKingLower()));
        assertEquals(1, getPairABCCD().compareTo(getPairABCDD()));
        assertEquals(0, getPairABCCD().compareTo(getCloneHand(getPairABCCD().getCards())));
        assertEquals(-1, getPairABCCD().compareTo(getPairAABCD()));
        assertEquals(-1, getPairABCCD().compareTo(getTwoPairsAABBC()));
        assertEquals(-1, getPairABCCD().compareTo(getSetAAABC()));
        assertEquals(-1, getPairABCCD().compareTo(getStraight()));
        assertEquals(-1, getPairABCCD().compareTo(getFlushHigher()));
        assertEquals(-1, getPairABCCD().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getPairABCCD().compareTo(getCareAAAAB()));
        assertEquals(-1, getPairABCCD().compareTo(getStraightFlush()));
        assertEquals(-1, getPairABCCD().compareTo(getRoyalFlushTwo()));
        assertTrue(getPairABCCD().equals(getCloneHand(getPairABCCD().getCards())));
        assertFalse(getPairABCCD().equals(getPairAABCD()));
        assertFalse(getPairABCCD().equals(null));
        assertEquals(1, getPairABCCD().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testICombogetPairABCDD() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '6'));
        assertEquals(Pair.class, getPairABCDD().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getPairABCDD().getHandICombination().getKickersList());
        assertEquals(2, getPairABCDD().getHandAbstractCombination().getHighness());
        assertEquals("Pair", getPairABCDD().getHandAbstractCombination().getName());
        assertEquals(1, getPairABCDD().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getPairABCDD().compareTo(getAceKingLower()));
        assertEquals(0, getPairABCDD().compareTo(getCloneHand(getPairABCDD().getCards())));
        assertEquals(-1, getPairABCDD().compareTo(getPairAABCD()));
        assertEquals(-1, getPairABCDD().compareTo(getTwoPairsAABBC()));
        assertEquals(-1, getPairABCDD().compareTo(getSetAAABC()));
        assertEquals(-1, getPairABCDD().compareTo(getStraight()));
        assertEquals(-1, getPairABCDD().compareTo(getFlushHigher()));
        assertEquals(-1, getPairABCDD().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getPairABCDD().compareTo(getCareAAAAB()));
        assertEquals(-1, getPairABCDD().compareTo(getStraightFlush()));
        assertEquals(-1, getPairABCDD().compareTo(getRoyalFlushTwo()));
        assertTrue(getPairABCDD().equals(getCloneHand(getPairABCDD().getCards())));
        assertFalse(getPairABCDD().equals(getStraightFlush()));
        assertFalse(getPairABCDD().equals(null));
        assertEquals(1, getPairABCDD().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testICombogetTwoPairsAABBC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(TwoPairs.class, getTwoPairsAABBC().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getTwoPairsAABBC().getHandICombination().getKickersList());
        assertEquals(3, getTwoPairsAABBC().getHandAbstractCombination().getHighness());
        assertEquals("Two Pairs", getTwoPairsAABBC().getHandAbstractCombination().getName());
        assertEquals(1, getTwoPairsAABBC().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getTwoPairsAABBC().compareTo(getAceKingLower()));
        assertEquals(1, getTwoPairsAABBC().compareTo(getPairABCDD()));
        assertEquals(1, getTwoPairsAABBC().compareTo(getTwoPairsABBCC()));
        assertEquals(0, getTwoPairsAABBC().compareTo(getCloneHand(getTwoPairsAABBC().getCards())));
        assertEquals(-1, getTwoPairsAABBC().compareTo(getSetAAABC()));
        assertEquals(-1, getTwoPairsAABBC().compareTo(getStraight()));
        assertEquals(-1, getTwoPairsAABBC().compareTo(getFlushHigher()));
        assertEquals(-1, getTwoPairsAABBC().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getTwoPairsAABBC().compareTo(getCareAAAAB()));
        assertEquals(-1, getTwoPairsAABBC().compareTo(getStraightFlush()));
        assertEquals(-1, getTwoPairsAABBC().compareTo(getRoyalFlushTwo()));
        assertTrue(getTwoPairsAABBC().equals(getCloneHand(getTwoPairsAABBC().getCards())));
        assertFalse(getTwoPairsAABBC().equals(getPairABCDD()));
        assertFalse(getTwoPairsAABBC().equals(null));
        assertEquals(2, getTwoPairsAABBC().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testICombogetTwoPairsAABCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '7'));
        assertEquals(TwoPairs.class, getTwoPairsAABCC().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getTwoPairsAABCC().getHandICombination().getKickersList());
        assertEquals(3, getTwoPairsAABCC().getHandAbstractCombination().getHighness());
        assertEquals("Two Pairs", getTwoPairsAABCC().getHandAbstractCombination().getName());
        assertEquals(1, getTwoPairsAABCC().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getTwoPairsAABCC().compareTo(getAceKingLower()));
        assertEquals(1, getTwoPairsAABCC().compareTo(getPairABCDD()));
        assertEquals(1, getTwoPairsAABCC().compareTo(getTwoPairsABBCC()));
        assertEquals(0, getTwoPairsAABCC().compareTo(getCloneHand(getTwoPairsAABCC().getCards())));
        assertEquals(-1, getTwoPairsAABCC().compareTo(getSetAAABC()));
        assertEquals(-1, getTwoPairsAABCC().compareTo(getStraight()));
        assertEquals(-1, getTwoPairsAABCC().compareTo(getFlushHigher()));
        assertEquals(-1, getTwoPairsAABCC().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getTwoPairsAABCC().compareTo(getCareAAAAB()));
        assertEquals(-1, getTwoPairsAABCC().compareTo(getStraightFlush()));
        assertEquals(-1, getTwoPairsAABCC().compareTo(getRoyalFlushTwo()));
        assertTrue(getTwoPairsAABCC().equals(getCloneHand(getTwoPairsAABCC().getCards())));
        assertFalse(getTwoPairsAABCC().equals(getPairABCDD()));
        assertFalse(getTwoPairsAABCC().equals(null));
        assertEquals(2, getTwoPairsAABCC().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testICombogetTwoPairsABBCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '7'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'A'));
        assertEquals(TwoPairs.class, getTwoPairsABBCC().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getTwoPairsABBCC().getHandICombination().getKickersList());
        assertEquals(3, getTwoPairsABBCC().getHandAbstractCombination().getHighness());
        assertEquals("Two Pairs", getTwoPairsABBCC().getHandAbstractCombination().getName());
        assertEquals(1, getTwoPairsABBCC().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getTwoPairsABBCC().compareTo(getAceKingLower()));
        assertEquals(1, getTwoPairsABBCC().compareTo(getPairABCDD()));
        assertEquals(0, getTwoPairsABBCC().compareTo(getCloneHand(getTwoPairsABBCC().getCards())));
        assertEquals(-1, getTwoPairsABBCC().compareTo(getSetAAABC()));
        assertEquals(-1, getTwoPairsABBCC().compareTo(getStraight()));
        assertEquals(-1, getTwoPairsABBCC().compareTo(getFlushHigher()));
        assertEquals(-1, getTwoPairsABBCC().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getTwoPairsABBCC().compareTo(getCareAAAAB()));
        assertEquals(-1, getTwoPairsABBCC().compareTo(getStraightFlush()));
        assertEquals(-1, getTwoPairsABBCC().compareTo(getRoyalFlushTwo()));
        assertTrue(getTwoPairsABBCC().equals(getCloneHand(getTwoPairsABBCC().getCards())));
        assertFalse(getTwoPairsABBCC().equals(getPairABCDD()));
        assertFalse(getTwoPairsABBCC().equals(null));
        assertEquals(2, getTwoPairsABBCC().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testCombinationgetSetAAABC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '7'));
        checkKickersList.add(new Card(CardSuit.SPADES, '5'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(ThreeOfKind.class, getSetAAABC().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getSetAAABC().getHandICombination().getKickersList());
        assertEquals(4, getSetAAABC().getHandAbstractCombination().getHighness());
        assertEquals("Three of a Kind", getSetAAABC().getHandAbstractCombination().getName());
        assertEquals(1, getSetAAABC().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getSetAAABC().compareTo(getAceKingLower()));
        assertEquals(1, getSetAAABC().compareTo(getPairABCDD()));
        assertEquals(1, getSetAAABC().compareTo(getTwoPairsABBCC()));
        assertEquals(0, getSetAAABC().compareTo(getCloneHand(getSetAAABC().getCards())));
        assertEquals(-1, getSetAAABC().compareTo(getSetABBBC()));
        assertEquals(-1, getSetAAABC().compareTo(getStraight()));
        assertEquals(-1, getSetAAABC().compareTo(getFlushHigher()));
        assertEquals(-1, getSetAAABC().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getSetAAABC().compareTo(getCareAAAAB()));
        assertEquals(-1, getSetAAABC().compareTo(getStraightFlush()));
        assertEquals(-1, getSetAAABC().compareTo(getRoyalFlushTwo()));
        assertTrue(getSetAAABC().equals(getCloneHand(getSetAAABC().getCards())));
        assertFalse(getSetAAABC().equals(getPairABCDD()));
        assertFalse(getSetAAABC().equals(null));
        assertEquals(3, getSetAAABC().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testIComboSetABBBC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'J'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(ThreeOfKind.class, getSetABBBC().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getSetABBBC().getHandICombination().getKickersList());
        assertEquals(4, getSetABBBC().getHandAbstractCombination().getHighness());
        assertEquals("Three of a Kind", getSetABBBC().getHandAbstractCombination().getName());
        assertEquals(1, getSetABBBC().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getSetABBBC().compareTo(getAceKingLower()));
        assertEquals(1, getSetABBBC().compareTo(getPairABCDD()));
        assertEquals(1, getSetABBBC().compareTo(getTwoPairsABBCC()));
        assertEquals(1, getSetABBBC().compareTo(getSetABCCC()));
        assertEquals(0, getSetABBBC().compareTo(getCloneHand(getSetABBBC().getCards())));
        assertEquals(-1, getSetABBBC().compareTo(getStraight()));
        assertEquals(-1, getSetABBBC().compareTo(getFlushHigher()));
        assertEquals(-1, getSetABBBC().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getSetABBBC().compareTo(getCareAAAAB()));
        assertEquals(-1, getSetABBBC().compareTo(getStraightFlush()));
        assertEquals(-1, getSetABBBC().compareTo(getRoyalFlushTwo()));
        assertTrue(getSetABBBC().equals(getCloneHand(getSetABBBC().getCards())));
        assertFalse(getSetABBBC().equals(getPairABCDD()));
        assertFalse(getSetABBBC().equals(null));
        assertEquals(3, getSetABBBC().getHandAbstractCombination().getMultiplier());
    }
        
    @Test
    public void testIComboSetABCCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        assertEquals(ThreeOfKind.class, getSetABCCC().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getSetABCCC().getHandICombination().getKickersList());
        assertEquals(4, getSetABCCC().getHandAbstractCombination().getHighness());
        assertEquals("Three of a Kind", getSetABCCC().getHandAbstractCombination().getName());
        assertEquals(1, getSetABCCC().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getSetABCCC().compareTo(getAceKingLower()));
        assertEquals(1, getSetABCCC().compareTo(getPairABCDD()));
        assertEquals(1, getSetABCCC().compareTo(getTwoPairsABBCC()));
        assertEquals(0, getSetABCCC().compareTo(getCloneHand(getSetABCCC().getCards())));
        assertEquals(-1, getSetABCCC().compareTo(getSetAAABC()));
        assertEquals(-1, getSetABCCC().compareTo(getStraight()));
        assertEquals(-1, getSetABCCC().compareTo(getFlushHigher()));
        assertEquals(-1, getSetABCCC().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getSetABCCC().compareTo(getCareAAAAB()));
        assertEquals(-1, getSetABCCC().compareTo(getStraightFlush()));
        assertEquals(-1, getSetABCCC().compareTo(getRoyalFlushTwo()));
        assertTrue(getSetABCCC().equals(getCloneHand(getSetABCCC().getCards())));
        assertFalse(getSetABCCC().equals(getPairABCDD()));
        assertFalse(getSetABCCC().equals(null));
        assertEquals(3, getSetABCCC().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testICombogetStraight() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'K'));
        assertEquals(Straight.class, getStraight().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getStraight().getHandICombination().getKickersList());
        assertEquals(5, getStraight().getHandAbstractCombination().getHighness());
        assertEquals("Straight", getStraight().getHandAbstractCombination().getName());
        assertEquals(1, getStraight().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getStraight().compareTo(getAceKingLower()));
        assertEquals(1, getStraight().compareTo(getPairABCDD()));
        assertEquals(1, getStraight().compareTo(getTwoPairsABBCC()));
        assertEquals(1, getStraight().compareTo(getSetAAABC()));
        assertEquals(1, getStraight().compareTo(getStraightWheel()));
        assertEquals(0, getStraight().compareTo(getCloneHand(getStraight().getCards())));
        assertEquals(-1, getStraight().compareTo(getFlushHigher()));
        assertEquals(-1, getStraight().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getStraight().compareTo(getCareAAAAB()));
        assertEquals(-1, getStraight().compareTo(getStraightFlush()));
        assertEquals(-1, getStraight().compareTo(getRoyalFlushTwo()));
        assertTrue(getStraight().equals(getCloneHand(getStraight().getCards())));
        assertFalse(getStraight().equals(getPairABCDD()));
        assertFalse(getStraight().equals(null));
        assertEquals(4, getStraight().getHandAbstractCombination().getMultiplier());

    }
    
    @Test
    public void testIComboWheelgetStraight() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '5'));
        assertEquals(Straight.class, getStraightWheel().getHandAbstractCombination().getClass());
        assertEquals(checkKickersList, getStraightWheel().getHandAbstractCombination().getKickersList());
        assertEquals(5, getStraightWheel().getHandAbstractCombination().getHighness());
        assertEquals("Straight", getStraightWheel().getHandAbstractCombination().getName());
        assertEquals(1, getStraightWheel().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getStraightWheel().compareTo(getAceKingLower()));
        assertEquals(1, getStraightWheel().compareTo(getPairABCDD()));
        assertEquals(1, getStraightWheel().compareTo(getTwoPairsABBCC()));
        assertEquals(1, getStraightWheel().compareTo(getSetAAABC()));
        assertEquals(0, getStraightWheel().compareTo(getCloneHand(getStraightWheel().getCards())));
        assertEquals(-1, getStraightWheel().compareTo(getStraight()));
        assertEquals(-1, getStraightWheel().compareTo(getFlushHigher()));
        assertEquals(-1, getStraightWheel().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getStraightWheel().compareTo(getCareAAAAB()));
        assertEquals(-1, getStraightWheel().compareTo(getStraightFlush()));
        assertEquals(-1, getStraightWheel().compareTo(getRoyalFlushTwo()));
        assertTrue(getStraightWheel().equals(getCloneHand(getStraightWheel().getCards())));
        assertFalse(getStraightWheel().equals(getPairABCDD()));
        assertFalse(getStraightWheel().equals(null));
        assertEquals(4, getStraightWheel().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testIComboIsFlush(){
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'J'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.SPADES, '3'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        assertEquals(Flush.class, getFlushHigher().getHandAbstractCombination().getClass());
        assertEquals(checkKickersList, getFlushHigher().getHandAbstractCombination().getKickersList());
        assertEquals(6, getFlushHigher().getHandAbstractCombination().getHighness());
        assertEquals("Flush", getFlushHigher().getHandAbstractCombination().getName());
        assertEquals(1, getFlushHigher().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getFlushHigher().compareTo(getAceKingLower()));
        assertEquals(1, getFlushHigher().compareTo(getPairABCDD()));
        assertEquals(1, getFlushHigher().compareTo(getTwoPairsABBCC()));
        assertEquals(1, getFlushHigher().compareTo(getSetAAABC()));
        assertEquals(1, getFlushHigher().compareTo(getStraight()));
        assertEquals(1, getFlushHigher().compareTo(getFlushLower()));
        assertEquals(0, getFlushHigher().compareTo(getCloneHand(getFlushHigher().getCards())));
        assertEquals(-1, getFlushHigher().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getFlushHigher().compareTo(getCareAAAAB()));
        assertEquals(-1, getFlushHigher().compareTo(getStraightFlush()));
        assertEquals(-1, getFlushHigher().compareTo(getRoyalFlushTwo()));
        assertTrue(getFlushHigher().equals(getCloneHand(getFlushHigher().getCards())));
        assertFalse(getFlushHigher().equals(getPairABCDD()));
        assertFalse(getFlushHigher().equals(null));
        assertEquals(5, getFlushHigher().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testIComboFullHouseAAACC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        assertEquals(FullHouse.class, getFullHouseAAABB().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getFullHouseAAABB().getHandICombination().getKickersList());
        assertEquals(7, getFullHouseAAABB().getHandAbstractCombination().getHighness());
        assertEquals("Full House", getFullHouseAAABB().getHandAbstractCombination().getName());
        assertEquals(1, getFullHouseAAABB().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getFullHouseAAABB().compareTo(getAceKingLower()));
        assertEquals(1, getFullHouseAAABB().compareTo(getPairABCDD()));
        assertEquals(1, getFullHouseAAABB().compareTo(getTwoPairsABBCC()));
        assertEquals(1, getFullHouseAAABB().compareTo(getSetAAABC()));
        assertEquals(1, getFullHouseAAABB().compareTo(getStraight()));
        assertEquals(1, getFullHouseAAABB().compareTo(getFlushLower()));
        assertEquals(1, getFullHouseAAABB().compareTo(getFullHouseAABBB()));
        assertEquals(0, getFullHouseAAABB().compareTo(getCloneHand(getFullHouseAAABB().getCards())));
        assertEquals(-1, getFullHouseAAABB().compareTo(getCareAAAAB()));
        assertEquals(-1, getFullHouseAAABB().compareTo(getStraightFlush()));
        assertEquals(-1, getFullHouseAAABB().compareTo(getRoyalFlushTwo()));
        assertTrue(getFullHouseAAABB().equals(getCloneHand(getFullHouseAAABB().getCards())));
        assertFalse(getFullHouseAAABB().equals(getPairABCDD()));
        assertFalse(getFullHouseAAABB().equals(null));
        assertEquals(7, getFullHouseAAABB().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testIComboFullHouseAACCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        assertEquals(FullHouse.class, getFullHouseAABBB().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getFullHouseAABBB().getHandICombination().getKickersList());
        assertEquals(7, getFullHouseAABBB().getHandAbstractCombination().getHighness());
        assertEquals("Full House", getFullHouseAABBB().getHandAbstractCombination().getName());
        assertEquals(1, getFullHouseAABBB().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getFullHouseAABBB().compareTo(getAceKingLower()));
        assertEquals(1, getFullHouseAABBB().compareTo(getPairABCDD()));
        assertEquals(1, getFullHouseAABBB().compareTo(getTwoPairsABBCC()));
        assertEquals(1, getFullHouseAABBB().compareTo(getSetAAABC()));
        assertEquals(1, getFullHouseAABBB().compareTo(getStraight()));
        assertEquals(1, getFullHouseAABBB().compareTo(getFlushLower()));
        assertEquals(0, getFullHouseAABBB().compareTo(getCloneHand(getFullHouseAABBB().getCards())));
        assertEquals(-1, getFullHouseAABBB().compareTo(getFullHouseAAABB()));
        assertEquals(-1, getFullHouseAABBB().compareTo(getCareAAAAB()));
        assertEquals(-1, getFullHouseAABBB().compareTo(getStraightFlush()));
        assertEquals(-1, getFullHouseAABBB().compareTo(getRoyalFlushTwo()));
        assertTrue(getFullHouseAABBB().equals(getCloneHand(getFullHouseAABBB().getCards())));
        assertFalse(getFullHouseAABBB().equals(getPairABCDD()));
        assertFalse(getFullHouseAABBB().equals(null));
        assertEquals(7, getFullHouseAABBB().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testIComboFourOfaKindAAAAB() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        assertEquals(FourOfKind.class, getCareAAAAB().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getCareAAAAB().getHandICombination().getKickersList());
        assertEquals(8, getCareAAAAB().getHandAbstractCombination().getHighness());
        assertEquals("Four of a Kind", getCareAAAAB().getHandAbstractCombination().getName());
        assertEquals(1, getCareAAAAB().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getCareAAAAB().compareTo(getAceKingLower()));
        assertEquals(1, getCareAAAAB().compareTo(getPairABCDD()));
        assertEquals(1, getCareAAAAB().compareTo(getTwoPairsABBCC()));
        assertEquals(1, getCareAAAAB().compareTo(getSetAAABC()));
        assertEquals(1, getCareAAAAB().compareTo(getStraight()));
        assertEquals(1, getCareAAAAB().compareTo(getFlushLower()));
        assertEquals(1, getCareAAAAB().compareTo(getFullHouseAAABB()));
        assertEquals(1, getCareAAAAB().compareTo(getCareABBBB()));
        assertEquals(0, getCareAAAAB().compareTo(getCloneHand(getCareAAAAB().getCards())));
        assertEquals(-1, getCareAAAAB().compareTo(getStraightFlush()));
        assertEquals(-1, getCareAAAAB().compareTo(getRoyalFlushTwo()));
        assertTrue(getCareAAAAB().equals(getCloneHand(getCareAAAAB().getCards())));
        assertFalse(getCareAAAAB().equals(getPairABCDD()));
        assertFalse(getCareAAAAB().equals(null));
        assertEquals(20, getCareAAAAB().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testIComboFourOfaKindABBBB() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        assertEquals(FourOfKind.class, getCareABBBB().getHandAbstractCombination().getClass());
        //assertEquals(checkKickersList, getCareABBBB().getHandICombination().getKickersList());
        assertEquals(8, getCareABBBB().getHandAbstractCombination().getHighness());
        assertEquals("Four of a Kind", getCareABBBB().getHandAbstractCombination().getName());
        assertEquals(1, getCareABBBB().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getCareABBBB().compareTo(getAceKingLower()));
        assertEquals(1, getCareABBBB().compareTo(getPairABCDD()));
        assertEquals(1, getCareABBBB().compareTo(getTwoPairsABBCC()));
        assertEquals(1, getCareABBBB().compareTo(getSetAAABC()));
        assertEquals(1, getCareABBBB().compareTo(getStraight()));
        assertEquals(1, getCareABBBB().compareTo(getFlushLower()));
        assertEquals(1, getCareABBBB().compareTo(getFullHouseAAABB()));
        assertEquals(0, getCareABBBB().compareTo(getCloneHand(getCareABBBB().getCards())));
        assertEquals(-1, getCareABBBB().compareTo(getCareAAAAB()));
        assertEquals(-1, getCareABBBB().compareTo(getStraightFlush()));
        assertEquals(-1, getCareABBBB().compareTo(getRoyalFlushTwo()));
        assertTrue(getCareABBBB().equals(getCloneHand(getCareABBBB().getCards())));
        assertFalse(getCareABBBB().equals(getPairABCDD()));
        assertFalse(getCareABBBB().equals(null));
        assertEquals(20, getCareABBBB().getHandAbstractCombination().getMultiplier());
    }

    @Test
    public void testICombogetStraightFlush() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.CLUBS, 'K'));
        assertEquals(StraightFlush.class, getStraightFlush().getHandAbstractCombination().getClass());
        assertEquals(checkKickersList, getStraightFlush().getHandAbstractCombination().getKickersList());
        assertEquals(9, getStraightFlush().getHandAbstractCombination().getHighness());
        assertEquals("Straight Flush", getStraightFlush().getHandAbstractCombination().getName());
        assertEquals(1, getStraightFlush().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getStraightFlush().compareTo(getAceKingLower()));
        assertEquals(1, getStraightFlush().compareTo(getPairABCDD()));
        assertEquals(1, getStraightFlush().compareTo(getTwoPairsABBCC()));
        assertEquals(1, getStraightFlush().compareTo(getSetAAABC()));
        assertEquals(1, getStraightFlush().compareTo(getStraight()));
        assertEquals(1, getStraightFlush().compareTo(getFlushLower()));
        assertEquals(1, getStraightFlush().compareTo(getFullHouseAAABB()));
        assertEquals(1, getStraightFlush().compareTo(getCareAAAAB()));
        assertEquals(1, getStraightFlush().compareTo(getStraightFlushWheel()));
        assertEquals(0, getStraightFlush().compareTo(getCloneHand(getStraightFlush().getCards())));
        assertEquals(-1, getStraightFlush().compareTo(getRoyalFlushTwo()));
        assertTrue(getStraightFlush().equals(getCloneHand(getStraightFlush().getCards())));
        assertFalse(getStraightFlush().equals(getPairABCDD()));
        assertFalse(getStraightFlush().equals(null));
        assertEquals(50, getStraightFlush().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testIComboWheelgetStraightFlush() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.CLUBS, '5'));
        assertEquals(StraightFlush.class, getStraightFlushWheel().getHandAbstractCombination().getClass());
        assertEquals(checkKickersList, getStraightFlushWheel().getHandAbstractCombination().getKickersList());
        assertEquals(9, getStraightFlushWheel().getHandAbstractCombination().getHighness());
        assertEquals("Straight Flush", getStraightFlushWheel().getHandAbstractCombination().getName());
        assertEquals(1, getStraightFlushWheel().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getStraightFlushWheel().compareTo(getAceKingLower()));
        assertEquals(1, getStraightFlushWheel().compareTo(getPairABCDD()));
        assertEquals(1, getStraightFlushWheel().compareTo(getTwoPairsABBCC()));
        assertEquals(1, getStraightFlushWheel().compareTo(getSetAAABC()));
        assertEquals(1, getStraightFlushWheel().compareTo(getStraight()));
        assertEquals(1, getStraightFlushWheel().compareTo(getFlushLower()));
        assertEquals(1, getStraightFlushWheel().compareTo(getFullHouseAAABB()));
        assertEquals(1, getStraightFlushWheel().compareTo(getCareAAAAB()));
        assertEquals(0, getStraightFlushWheel().compareTo(getCloneHand(getStraightFlushWheel().getCards())));
        assertEquals(-1, getStraightFlushWheel().compareTo(getStraightFlush()));
        assertEquals(-1, getStraightFlushWheel().compareTo(getRoyalFlushTwo()));
        assertTrue(getStraightFlushWheel().equals(getCloneHand(getStraightFlushWheel().getCards())));
        assertFalse(getStraightFlushWheel().equals(getPairABCDD()));
        assertFalse(getStraightFlushWheel().equals(null));
        assertEquals(50, getStraightFlushWheel().getHandAbstractCombination().getMultiplier());
    }
    
    @Test
    public void testIComboRoyalFlush() {
        assertEquals(RoyalFlush.class, getRoyalFlushOne().getHandAbstractCombination().getClass());
        assertEquals(10, getRoyalFlushOne().getHandAbstractCombination().getHighness());
        assertEquals("Royal Flush", getRoyalFlushOne().getHandAbstractCombination().getName());
        assertEquals(1, getRoyalFlushOne().compareTo(getDoesntQualifyOne()));
        assertEquals(1, getRoyalFlushOne().compareTo(getAceKingLower()));
        assertEquals(1, getRoyalFlushOne().compareTo(getPairABCDD()));
        assertEquals(1, getRoyalFlushOne().compareTo(getTwoPairsABBCC()));
        assertEquals(1, getRoyalFlushOne().compareTo(getSetAAABC()));
        assertEquals(1, getRoyalFlushOne().compareTo(getStraight()));
        assertEquals(1, getRoyalFlushOne().compareTo(getFlushLower()));
        assertEquals(1, getRoyalFlushOne().compareTo(getFullHouseAAABB()));
        assertEquals(1, getRoyalFlushOne().compareTo(getCareAAAAB()));
        assertEquals(1, getRoyalFlushOne().compareTo(getStraightFlushWheel()));
        assertEquals(1, getRoyalFlushOne().compareTo(getStraightFlush()));
        assertEquals(0, getRoyalFlushOne().compareTo(getRoyalFlushTwo()));
        assertTrue(getRoyalFlushOne().equals(getRoyalFlushTwo()));
        assertFalse(getRoyalFlushOne().equals(getPairABCDD()));
        assertFalse(getRoyalFlushOne().equals(null));
        assertEquals(100, getRoyalFlushOne().getHandAbstractCombination().getMultiplier());
        
    }
}
