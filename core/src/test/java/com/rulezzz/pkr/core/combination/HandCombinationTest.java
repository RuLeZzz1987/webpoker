package com.rulezzz.pkr.core.combination;

import static org.junit.Assert.*;
import static com.rulezzz.pkr.core.combination.ComboSamples.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import org.junit.Test;

import com.rulezzz.pkr.core.basestructures.Card;
import com.rulezzz.pkr.core.basestructures.CardSuit;
import com.rulezzz.pkr.core.basestructures.Hand;


public class HandCombinationTest {
   
    private Hand getCloneHand(List<Card> cards) {
        Hand result = new Hand();
        result.add(cards);
        return result;
    }
    
    @Test
    public void testIComboDoesntQualify() {
        assertEquals(DoesntQualify.class, getDoesntQualifyOne().getHandICombination().getClass());
        assertEquals(0, getDoesntQualifyOne().getHandICombination().getHighness());
        assertEquals("Doesn't qualifier", getDoesntQualifyOne().getHandICombination().getName());
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
        assertEquals(true, getDoesntQualifyOne().equals(getDoesntQualifyTwo()));
        assertEquals(false, getDoesntQualifyOne().equals(getRoyalFlushTwo()));
        assertEquals(false, getDoesntQualifyOne().equals(null));
        
    }

    @Test
    public void testIComboAK() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.HEART, 'Q'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '3'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        Collections.sort(checkKickersList);
        Collections.reverse(checkKickersList);
        assertEquals(AceKing.class, getAceKingHigher().getHandICombination().getClass());
        assertEquals(checkKickersList, getAceKingHigher().getHandICombination().getKickersList());
        assertEquals(1, getAceKingHigher().getHandICombination().getHighness());
        assertEquals("Ace & King", getAceKingHigher().getHandICombination().getName());
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
        assertEquals(true, getAceKingHigher().equals(getCloneHand(getAceKingHigher().getCards())));
        assertEquals(false, getAceKingHigher().equals(getRoyalFlushTwo()));
        assertEquals(false, getAceKingHigher().equals(null));
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
        assertEquals(Pair.class, getPairAABCD().getHandICombination().getClass());
        assertEquals(checkKickersList, getPairAABCD().getHandICombination().getKickersList());
        assertEquals(2, getPairAABCD().getHandICombination().getHighness());
        assertEquals("Pair", getPairAABCD().getHandICombination().getName());
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
        assertEquals(true, getPairAABCD().equals(getCloneHand(getPairAABCD().getCards())));
        assertEquals(false, getPairAABCD().equals(getRoyalFlushTwo()));
        assertEquals(false, getPairAABCD().equals(null));
        
    }
    
    @Test
    public void testIComboPairABBCD() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'J'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '3'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(Pair.class, getPairABBCD().getHandICombination().getClass());
        assertEquals(checkKickersList, getPairABBCD().getHandICombination().getKickersList());
        assertEquals(2, getPairABBCD().getHandICombination().getHighness());
        assertEquals("Pair", getPairABBCD().getHandICombination().getName());
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
        assertEquals(true, getPairABBCD().equals(getCloneHand(getPairABBCD().getCards())));
        assertEquals(false, getPairABBCD().equals(getPairAABCD()));
        assertEquals(false, getPairABBCD().equals(null));
    }
    
    @Test
    public void testIComboPairABCCD() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '6'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(Pair.class, getPairABCCD().getHandICombination().getClass());
        assertEquals(checkKickersList, getPairABCCD().getHandICombination().getKickersList());
        assertEquals(2, getPairABCCD().getHandICombination().getHighness());
        assertEquals("Pair", getPairABCCD().getHandICombination().getName());
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
        assertEquals(true, getPairABCCD().equals(getCloneHand(getPairABCCD().getCards())));
        assertEquals(false, getPairABCCD().equals(getPairAABCD()));
        assertEquals(false, getPairABCCD().equals(null));
    }
    
    @Test
    public void testICombogetPairABCDD() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '6'));
        assertEquals(Pair.class, getPairABCDD().getHandICombination().getClass());
        assertEquals(checkKickersList, getPairABCDD().getHandICombination().getKickersList());
        assertEquals(2, getPairABCDD().getHandICombination().getHighness());
        assertEquals("Pair", getPairABCDD().getHandICombination().getName());
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
        assertEquals(true, getPairABCDD().equals(getCloneHand(getPairABCDD().getCards())));
        assertEquals(false, getPairABCDD().equals(getStraightFlush()));
        assertEquals(false, getPairABCDD().equals(null));
    }
    
    @Test
    public void testICombogetTwoPairsAABBC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(TwoPairs.class, getTwoPairsAABBC().getHandICombination().getClass());
        assertEquals(checkKickersList, getTwoPairsAABBC().getHandICombination().getKickersList());
        assertEquals(3, getTwoPairsAABBC().getHandICombination().getHighness());
        assertEquals("Two Pairs", getTwoPairsAABBC().getHandICombination().getName());
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
        assertEquals(true, getTwoPairsAABBC().equals(getCloneHand(getTwoPairsAABBC().getCards())));
        assertEquals(false, getTwoPairsAABBC().equals(getPairABCDD()));
        assertEquals(false, getTwoPairsAABBC().equals(null));
    }
    
    @Test
    public void testICombogetTwoPairsAABCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '7'));
        assertEquals(TwoPairs.class, getTwoPairsAABCC().getHandICombination().getClass());
        assertEquals(checkKickersList, getTwoPairsAABCC().getHandICombination().getKickersList());
        assertEquals(3, getTwoPairsAABCC().getHandICombination().getHighness());
        assertEquals("Two Pairs", getTwoPairsAABCC().getHandICombination().getName());
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
        assertEquals(true, getTwoPairsAABCC().equals(getCloneHand(getTwoPairsAABCC().getCards())));
        assertEquals(false, getTwoPairsAABCC().equals(getPairABCDD()));
        assertEquals(false, getTwoPairsAABCC().equals(null));
    }
    
    @Test
    public void testICombogetTwoPairsABBCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '7'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'A'));
        assertEquals(TwoPairs.class, getTwoPairsABBCC().getHandICombination().getClass());
        assertEquals(checkKickersList, getTwoPairsABBCC().getHandICombination().getKickersList());
        assertEquals(3, getTwoPairsABBCC().getHandICombination().getHighness());
        assertEquals("Two Pairs", getTwoPairsABBCC().getHandICombination().getName());
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
        assertEquals(true, getTwoPairsABBCC().equals(getCloneHand(getTwoPairsABBCC().getCards())));
        assertEquals(false, getTwoPairsABBCC().equals(getPairABCDD()));
        assertEquals(false, getTwoPairsABBCC().equals(null));
    }
    
    @Test
    public void testCombinationgetSetAAABC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '7'));
        checkKickersList.add(new Card(CardSuit.SPADES, '5'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(TreeOfKind.class, getSetAAABC().getHandICombination().getClass());
        assertEquals(checkKickersList, getSetAAABC().getHandICombination().getKickersList());
        assertEquals(4, getSetAAABC().getHandICombination().getHighness());
        assertEquals("Tree of a Kind", getSetAAABC().getHandICombination().getName());
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
        assertEquals(true, getSetAAABC().equals(getCloneHand(getSetAAABC().getCards())));
        assertEquals(false, getSetAAABC().equals(getPairABCDD()));
        assertEquals(false, getSetAAABC().equals(null));
    }
    
    @Test
    public void testIComboSetABBBC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'J'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(TreeOfKind.class, getSetABBBC().getHandICombination().getClass());
        assertEquals(checkKickersList, getSetABBBC().getHandICombination().getKickersList());
        assertEquals(4, getSetABBBC().getHandICombination().getHighness());
        assertEquals("Tree of a Kind", getSetABBBC().getHandICombination().getName());
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
        assertEquals(true, getSetABBBC().equals(getCloneHand(getSetABBBC().getCards())));
        assertEquals(false, getSetABBBC().equals(getPairABCDD()));
        assertEquals(false, getSetABBBC().equals(null));
    }
        
    @Test
    public void testIComboSetABCCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        assertEquals(TreeOfKind.class, getSetABCCC().getHandICombination().getClass());
        assertEquals(checkKickersList, getSetABCCC().getHandICombination().getKickersList());
        assertEquals(4, getSetABCCC().getHandICombination().getHighness());
        assertEquals("Tree of a Kind", getSetABCCC().getHandICombination().getName());
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
        assertEquals(true, getSetABCCC().equals(getCloneHand(getSetABCCC().getCards())));
        assertEquals(false, getSetABCCC().equals(getPairABCDD()));
        assertEquals(false, getSetABCCC().equals(null));
    }
    
    @Test
    public void testICombogetStraight() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'K'));
        assertEquals(Straight.class, getStraight().getHandICombination().getClass());
        assertEquals(checkKickersList, getStraight().getHandICombination().getKickersList());
        assertEquals(5, getStraight().getHandICombination().getHighness());
        assertEquals("getStraight()", getStraight().getHandICombination().getName());
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
        assertEquals(true, getStraight().equals(getCloneHand(getStraight().getCards())));
        assertEquals(false, getStraight().equals(getPairABCDD()));
        assertEquals(false, getStraight().equals(null));

    }
    
    @Test
    public void testIComboWheelgetStraight() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '5'));
        assertEquals(Straight.class, getStraightWheel().getHandICombination().getClass());
        assertEquals(checkKickersList, getStraightWheel().getHandICombination().getKickersList());
        assertEquals(5, getStraightWheel().getHandICombination().getHighness());
        assertEquals("getStraight()", getStraightWheel().getHandICombination().getName());
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
        assertEquals(true, getStraightWheel().equals(getCloneHand(getStraightWheel().getCards())));
        assertEquals(false, getStraightWheel().equals(getPairABCDD()));
        assertEquals(false, getStraightWheel().equals(null));
    }
    
    @Test
    public void testIComboIsFlush(){
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'J'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.SPADES, '3'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        assertEquals(Flush.class, getFlushHigher().getHandICombination().getClass());
        assertEquals(checkKickersList, getFlushHigher().getHandICombination().getKickersList());
        assertEquals(6, getFlushHigher().getHandICombination().getHighness());
        assertEquals("Flush", getFlushHigher().getHandICombination().getName());
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
        assertEquals(true, getFlushHigher().equals(getCloneHand(getFlushHigher().getCards())));
        assertEquals(false, getFlushHigher().equals(getPairABCDD()));
        assertEquals(false, getFlushHigher().equals(null));
    }
    
    @Test
    public void testIComboFullHouseAAACC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        assertEquals(FullHouse.class, getFullHouseAAABB().getHandICombination().getClass());
        assertEquals(checkKickersList, getFullHouseAAABB().getHandICombination().getKickersList());
        assertEquals(7, getFullHouseAAABB().getHandICombination().getHighness());
        assertEquals("Full House", getFullHouseAAABB().getHandICombination().getName());
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
        assertEquals(true, getFullHouseAAABB().equals(getCloneHand(getFullHouseAAABB().getCards())));
        assertEquals(false, getFullHouseAAABB().equals(getPairABCDD()));
        assertEquals(false, getFullHouseAAABB().equals(null));
    }
    
    @Test
    public void testIComboFullHouseAACCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        assertEquals(FullHouse.class, getFullHouseAABBB().getHandICombination().getClass());
        assertEquals(checkKickersList, getFullHouseAABBB().getHandICombination().getKickersList());
        assertEquals(7, getFullHouseAABBB().getHandICombination().getHighness());
        assertEquals("Full House", getFullHouseAABBB().getHandICombination().getName());
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
        assertEquals(true, getFullHouseAABBB().equals(getCloneHand(getFullHouseAABBB().getCards())));
        assertEquals(false, getFullHouseAABBB().equals(getPairABCDD()));
        assertEquals(false, getFullHouseAABBB().equals(null));
    }
    
    @Test
    public void testIComboFourOfaKindAAAAB() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        assertEquals(FourOfKind.class, getCareAAAAB().getHandICombination().getClass());
        assertEquals(checkKickersList, getCareAAAAB().getHandICombination().getKickersList());
        assertEquals(8, getCareAAAAB().getHandICombination().getHighness());
        assertEquals("Four Of a Kind", getCareAAAAB().getHandICombination().getName());
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
        assertEquals(true, getCareAAAAB().equals(getCloneHand(getCareAAAAB().getCards())));
        assertEquals(false, getCareAAAAB().equals(getPairABCDD()));
        assertEquals(false, getCareAAAAB().equals(null));
    }
    
    @Test
    public void testIComboFourOfaKindABBBB() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        assertEquals(FourOfKind.class, getCareABBBB().getHandICombination().getClass());
        assertEquals(checkKickersList, getCareABBBB().getHandICombination().getKickersList());
        assertEquals(8, getCareABBBB().getHandICombination().getHighness());
        assertEquals("Four Of a Kind", getCareABBBB().getHandICombination().getName());
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
        assertEquals(true, getCareABBBB().equals(getCloneHand(getCareABBBB().getCards())));
        assertEquals(false, getCareABBBB().equals(getPairABCDD()));
        assertEquals(false, getCareABBBB().equals(null));
    }

    @Test
    public void testICombogetStraightFlush() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.CLUBS, 'K'));
        assertEquals(StraightFlush.class, getStraightFlush().getHandICombination().getClass());
        assertEquals(checkKickersList, getStraightFlush().getHandICombination().getKickersList());
        assertEquals(9, getStraightFlush().getHandICombination().getHighness());
        assertEquals("getStraight() Flush", getStraightFlush().getHandICombination().getName());
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
        assertEquals(true, getStraightFlush().equals(getCloneHand(getStraightFlush().getCards())));
        assertEquals(false, getStraightFlush().equals(getPairABCDD()));
        assertEquals(false, getStraightFlush().equals(null));
    }
    
    @Test
    public void testIComboWheelgetStraightFlush() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.CLUBS, '5'));
        assertEquals(StraightFlush.class, getStraightFlushWheel().getHandICombination().getClass());
        assertEquals(checkKickersList, getStraightFlushWheel().getHandICombination().getKickersList());
        assertEquals(9, getStraightFlushWheel().getHandICombination().getHighness());
        assertEquals("getStraight() Flush", getStraightFlushWheel().getHandICombination().getName());
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
        assertEquals(true, getStraightFlushWheel().equals(getCloneHand(getStraightFlushWheel().getCards())));
        assertEquals(false, getStraightFlushWheel().equals(getPairABCDD()));
        assertEquals(false, getStraightFlushWheel().equals(null));
    }
    
    @Test
    public void testIComboRoyalFlush() {
        assertEquals(RoyalFlush.class, getRoyalFlushOne().getHandICombination().getClass());
        assertEquals(10, getRoyalFlushOne().getHandICombination().getHighness());
        assertEquals("Royal Flush", getRoyalFlushOne().getHandICombination().getName());
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
        assertEquals(true, getRoyalFlushOne().equals(getRoyalFlushTwo()));
        assertEquals(false, getRoyalFlushOne().equals(getPairABCDD()));
        assertEquals(false, getRoyalFlushOne().equals(null));
        
    }
    
    @Test
    public void testICombo(){
        
    }
}
