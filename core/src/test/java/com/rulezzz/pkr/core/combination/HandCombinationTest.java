package com.rulezzz.pkr.core.combination;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rulezzz.pkr.core.basestructures.Card;
import com.rulezzz.pkr.core.basestructures.CardSuit;
import com.rulezzz.pkr.core.engine.Hand;

public class HandCombinationTest {

    private Hand doesntQualifyOne;
    private Hand doesntQualifyTwo;
    private Hand aceKingHigher;
    private Hand aceKingLower;
    private Hand pairAABCD;
    private Hand pairABBCD;
    private Hand pairABCCD;
    private Hand pairABCDD;
    private Hand twoPairsAABBC;
    private Hand twoPairsAABCC;
    private Hand twoPairsABBCC;
    private Hand setAAABC;
    private Hand setABBBC;
    private Hand setABCCC;
    private Hand straight;
    private Hand straightWheel;
    private Hand flushHigher;
    private Hand flushLower;
    private Hand fullHouseAAABB;
    private Hand fullHouseAABBB;
    private Hand careAAAAB;
    private Hand careABBBB;
    private Hand straightFlush;
    private Hand straightFlushWheel;
    private Hand royalFlushOne;
    private Hand royalFlushTwo;

    
    @Before
    public void setUp() {
        doesntQualifyOne = new Hand(new Card(CardSuit.CLUBS, 'A'),
                                    new Card(CardSuit.DIAMOND, 'J'),
                                    new Card(CardSuit.SPADES, '6'),
                                    new Card(CardSuit.HEART, '3'),
                                    new Card(CardSuit.CLUBS, '2'));
        doesntQualifyTwo = new Hand(new Card(CardSuit.CLUBS, 'K'),
                                    new Card(CardSuit.DIAMOND, 'T'),
                                    new Card(CardSuit.SPADES, '9'),
                                    new Card(CardSuit.HEART, '5'),
                                    new Card(CardSuit.CLUBS, '4'));
        aceKingHigher = new Hand(new Card(CardSuit.CLUBS, 'A'),
                                new Card(CardSuit.DIAMOND, 'K'),
                                new Card(CardSuit.HEART, 'Q'),
                                new Card(CardSuit.CLUBS, '3'),
                                new Card(CardSuit.CLUBS, '2'));
        aceKingLower = new Hand(new Card(CardSuit.SPADES, 'A'),
                                new Card(CardSuit.HEART, 'K'),
                                new Card(CardSuit.CLUBS, 'T'),
                                new Card(CardSuit.DIAMOND, '7'),
                                new Card(CardSuit.SPADES, '6'));
        pairAABCD = new Hand(new Card(CardSuit.CLUBS, 'K'),
                            new Card(CardSuit.DIAMOND, 'K'),
                            new Card(CardSuit.SPADES, '7'),
                            new Card(CardSuit.CLUBS, '5'),
                            new Card(CardSuit.CLUBS, '2'));
        pairABBCD = new Hand(new Card(CardSuit.CLUBS, 'A'),
                            new Card(CardSuit.DIAMOND, 'J'),
                            new Card(CardSuit.CLUBS, 'J'),
                            new Card(CardSuit.SPADES, '3'),
                            new Card(CardSuit.SPADES, '2'));
        pairABCCD = new Hand(new Card(CardSuit.CLUBS, 'A'),
                            new Card(CardSuit.DIAMOND, 'J'),
                            new Card(CardSuit.CLUBS, '6'),
                            new Card(CardSuit.HEART, '6'),
                            new Card(CardSuit.CLUBS, '2'));
        pairABCDD = new Hand(new Card(CardSuit.CLUBS, 'A'),
                            new Card(CardSuit.DIAMOND, 'J'),
                            new Card(CardSuit.CLUBS, '6'),
                            new Card(CardSuit.HEART, '2'),
                            new Card(CardSuit.CLUBS, '2'));
        twoPairsAABBC = new Hand(new Card(CardSuit.CLUBS, 'A'),
                            new Card(CardSuit.DIAMOND, 'A'),
                            new Card(CardSuit.CLUBS, '6'),
                            new Card(CardSuit.HEART, '6'),
                            new Card(CardSuit.CLUBS, '2'));
        twoPairsAABCC = new Hand(new Card(CardSuit.CLUBS, 'A'),
                            new Card(CardSuit.DIAMOND, 'A'),
                            new Card(CardSuit.CLUBS, '7'),
                            new Card(CardSuit.HEART, '6'),
                            new Card(CardSuit.CLUBS, '6'));
        twoPairsABBCC = new Hand(new Card(CardSuit.CLUBS, 'A'),
                            new Card(CardSuit.DIAMOND, '7'),
                            new Card(CardSuit.CLUBS, '7'),
                            new Card(CardSuit.HEART, '6'),
                            new Card(CardSuit.CLUBS, '6'));
        setAAABC = new Hand(new Card(CardSuit.CLUBS, '7'),
                            new Card(CardSuit.DIAMOND, '7'),
                            new Card(CardSuit.SPADES, '7'),
                            new Card(CardSuit.CLUBS, '5'),
                            new Card(CardSuit.CLUBS, '2'));
        setABBBC = new Hand(new Card(CardSuit.CLUBS, 'A'),
                            new Card(CardSuit.DIAMOND, 'J'),
                            new Card(CardSuit.CLUBS, 'J'),
                            new Card(CardSuit.SPADES, 'J'),
                            new Card(CardSuit.CLUBS, '2'));
        setABCCC = new Hand(new Card(CardSuit.CLUBS, 'A'),
                            new Card(CardSuit.DIAMOND, 'J'),
                            new Card(CardSuit.DIAMOND, '2'),
                            new Card(CardSuit.SPADES, '2'),
                            new Card(CardSuit.CLUBS, '2'));
        fullHouseAAABB = new Hand(new Card(CardSuit.CLUBS, 'A'),
                                new Card(CardSuit.DIAMOND, 'A'),
                                new Card(CardSuit.HEART, 'A'),
                                new Card(CardSuit.SPADES, '2'),
                                new Card(CardSuit.CLUBS, '2'));
        fullHouseAABBB = new Hand(new Card(CardSuit.CLUBS, 'A'),
                                new Card(CardSuit.DIAMOND, 'A'),
                                new Card(CardSuit.SPADES, '2'),
                                new Card(CardSuit.HEART, '2'),
                                new Card(CardSuit.CLUBS, '2'));
        careAAAAB = new Hand(new Card(CardSuit.CLUBS, 'A'),
                                new Card(CardSuit.DIAMOND, 'A'),
                                new Card(CardSuit.HEART, 'A'),
                                new Card(CardSuit.SPADES, 'A'),
                                new Card(CardSuit.CLUBS, '2'));
        careABBBB = new Hand(new Card(CardSuit.CLUBS, 'A'),
                                new Card(CardSuit.DIAMOND, '2'),
                                new Card(CardSuit.HEART, '2'),
                                new Card(CardSuit.SPADES, '2'),
                                new Card(CardSuit.CLUBS, '2'));
        straight = new Hand(new Card(CardSuit.CLUBS, 'K'),
                            new Card(CardSuit.DIAMOND, 'Q'),
                            new Card(CardSuit.HEART, 'J'),
                            new Card(CardSuit.SPADES, 'T'),
                            new Card(CardSuit.CLUBS, '9'));
        straightWheel = new Hand(new Card(CardSuit.CLUBS, 'A'),
                                new Card(CardSuit.DIAMOND, '5'),
                                new Card(CardSuit.HEART, '4'),
                                new Card(CardSuit.SPADES, '3'),
                                new Card(CardSuit.CLUBS, '2'));
        straightFlush = new Hand(new Card(CardSuit.CLUBS, 'K'),
                                new Card(CardSuit.CLUBS, 'Q'),
                                new Card(CardSuit.CLUBS, 'J'),
                                new Card(CardSuit.CLUBS, 'T'),
                                new Card(CardSuit.CLUBS, '9'));
        straightFlushWheel = new Hand(new Card(CardSuit.CLUBS, 'A'),
                                    new Card(CardSuit.CLUBS, '5'),
                                    new Card(CardSuit.CLUBS, '4'),
                                    new Card(CardSuit.CLUBS, '3'),
                                    new Card(CardSuit.CLUBS, '2'));
        royalFlushOne = new Hand(new Card(CardSuit.CLUBS, 'A'),
                                new Card(CardSuit.CLUBS, 'K'),
                                new Card(CardSuit.CLUBS, 'Q'),
                                new Card(CardSuit.CLUBS, 'J'),
                                new Card(CardSuit.CLUBS, 'T'));
        royalFlushTwo = new Hand(new Card(CardSuit.DIAMOND, 'A'),
                                new Card(CardSuit.DIAMOND, 'K'),
                                new Card(CardSuit.DIAMOND, 'Q'),
                                new Card(CardSuit.DIAMOND, 'J'),
                                new Card(CardSuit.DIAMOND, 'T'));
        flushHigher  = new Hand(new Card(CardSuit.SPADES, 'A'),
                                new Card(CardSuit.SPADES, 'J'),
                                new Card(CardSuit.SPADES, '6'),
                                new Card(CardSuit.SPADES, '3'),
                                new Card(CardSuit.SPADES, '2'));
        flushLower  = new Hand(new Card(CardSuit.HEART, 'Q'),
                                new Card(CardSuit.HEART, '9'),
                                new Card(CardSuit.HEART, '6'),
                                new Card(CardSuit.HEART, '5'),
                                new Card(CardSuit.HEART, '2'));
    }
    
    private Hand getCloneHand(List<Card> cards) {
        Hand result = new Hand();
        result.add(cards);
        return result;
    }
    
    @Test
    public void testIComboDoesntQualify() {
        assertEquals(DoesntQualify.class, doesntQualifyOne.getHandICombination().getClass());
        assertEquals(0, doesntQualifyOne.getHandICombination().getHighness());
        assertEquals("Doesn't qualifier", doesntQualifyOne.getHandICombination().getName());
        assertEquals(-1, doesntQualifyOne.compareTo(aceKingHigher));
        assertEquals(-1, doesntQualifyOne.compareTo(pairAABCD));
        assertEquals(-1, doesntQualifyOne.compareTo(twoPairsAABBC));
        assertEquals(-1, doesntQualifyOne.compareTo(setAAABC));
        assertEquals(-1, doesntQualifyOne.compareTo(straight));
        assertEquals(-1, doesntQualifyOne.compareTo(flushHigher));
        assertEquals(-1, doesntQualifyOne.compareTo(fullHouseAAABB));
        assertEquals(-1, doesntQualifyOne.compareTo(careAAAAB));
        assertEquals(-1, doesntQualifyOne.compareTo(straightFlush));
        assertEquals(-1, doesntQualifyOne.compareTo(royalFlushTwo));
        assertEquals(0, doesntQualifyOne.compareTo(doesntQualifyTwo));
        assertEquals(true, doesntQualifyOne.equals(doesntQualifyTwo));
        assertEquals(false, doesntQualifyOne.equals(royalFlushTwo));
        assertEquals(false, doesntQualifyOne.equals(null));
        
    }

    @Test
    public void testIComboAK() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.HEART, 'Q'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '3'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        Collections.sort(checkKickersList);
        Collections.reverse(checkKickersList);
        assertEquals(AceKing.class, aceKingHigher.getHandICombination().getClass());
        assertEquals(checkKickersList, aceKingHigher.getHandICombination().getKickersList());
        assertEquals(1, aceKingHigher.getHandICombination().getHighness());
        assertEquals("Ace & King", aceKingHigher.getHandICombination().getName());
        assertEquals(1, aceKingHigher.compareTo(doesntQualifyOne));
        assertEquals(1, aceKingHigher.compareTo(aceKingLower));
        assertEquals(0, aceKingHigher.compareTo(getCloneHand(aceKingHigher.getCards()))); 
        assertEquals(-1, aceKingHigher.compareTo(pairAABCD));
        assertEquals(-1, aceKingHigher.compareTo(twoPairsAABBC));
        assertEquals(-1, aceKingHigher.compareTo(setAAABC));
        assertEquals(-1, aceKingHigher.compareTo(straight));
        assertEquals(-1, aceKingHigher.compareTo(flushHigher));
        assertEquals(-1, aceKingHigher.compareTo(fullHouseAAABB));
        assertEquals(-1, aceKingHigher.compareTo(careAAAAB));
        assertEquals(-1, aceKingHigher.compareTo(straightFlush));
        assertEquals(-1, aceKingHigher.compareTo(royalFlushTwo));
        assertEquals(true, aceKingHigher.equals(getCloneHand(aceKingHigher.getCards())));
        assertEquals(false, aceKingHigher.equals(royalFlushTwo));
        assertEquals(false, aceKingHigher.equals(null));
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
        assertEquals(Pair.class, pairAABCD.getHandICombination().getClass());
        assertEquals(checkKickersList, pairAABCD.getHandICombination().getKickersList());
        assertEquals(2, pairAABCD.getHandICombination().getHighness());
        assertEquals("Pair", pairAABCD.getHandICombination().getName());
        assertEquals(1, pairAABCD.compareTo(doesntQualifyOne));
        assertEquals(1, pairAABCD.compareTo(aceKingLower));
        assertEquals(1, pairAABCD.compareTo(pairABBCD));
        assertEquals(0, pairAABCD.compareTo(getCloneHand(pairAABCD.getCards()))); 
        assertEquals(-1, pairAABCD.compareTo(twoPairsAABBC));
        assertEquals(-1, pairAABCD.compareTo(setAAABC));
        assertEquals(-1, pairAABCD.compareTo(straight));
        assertEquals(-1, pairAABCD.compareTo(flushHigher));
        assertEquals(-1, pairAABCD.compareTo(fullHouseAAABB));
        assertEquals(-1, pairAABCD.compareTo(careAAAAB));
        assertEquals(-1, pairAABCD.compareTo(straightFlush));
        assertEquals(-1, pairAABCD.compareTo(royalFlushTwo));
        assertEquals(true, pairAABCD.equals(getCloneHand(pairAABCD.getCards())));
        assertEquals(false, pairAABCD.equals(royalFlushTwo));
        assertEquals(false, pairAABCD.equals(null));
        
    }
    
    @Test
    public void testIComboPairABBCD() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'J'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '3'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(Pair.class, pairABBCD.getHandICombination().getClass());
        assertEquals(checkKickersList, pairABBCD.getHandICombination().getKickersList());
        assertEquals(2, pairABBCD.getHandICombination().getHighness());
        assertEquals("Pair", pairABBCD.getHandICombination().getName());
        assertEquals(1, pairABBCD.compareTo(doesntQualifyOne));
        assertEquals(1, pairABBCD.compareTo(aceKingLower));
        assertEquals(1, pairABBCD.compareTo(pairABCDD));
        assertEquals(0, pairABBCD.compareTo(getCloneHand(pairABBCD.getCards())));
        assertEquals(-1, pairABBCD.compareTo(pairAABCD));
        assertEquals(-1, pairABBCD.compareTo(twoPairsAABBC));
        assertEquals(-1, pairABBCD.compareTo(setAAABC));
        assertEquals(-1, pairABBCD.compareTo(straight));
        assertEquals(-1, pairABBCD.compareTo(flushHigher));
        assertEquals(-1, pairABBCD.compareTo(fullHouseAAABB));
        assertEquals(-1, pairABBCD.compareTo(careAAAAB));
        assertEquals(-1, pairABBCD.compareTo(straightFlush));
        assertEquals(-1, pairABBCD.compareTo(royalFlushTwo));
        assertEquals(true, pairABBCD.equals(getCloneHand(pairABBCD.getCards())));
        assertEquals(false, pairABBCD.equals(pairAABCD));
        assertEquals(false, pairABBCD.equals(null));
    }
    
    @Test
    public void testIComboPairABCCD() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '6'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(Pair.class, pairABCCD.getHandICombination().getClass());
        assertEquals(checkKickersList, pairABCCD.getHandICombination().getKickersList());
        assertEquals(2, pairABCCD.getHandICombination().getHighness());
        assertEquals("Pair", pairABCCD.getHandICombination().getName());
        assertEquals(1, pairABCCD.compareTo(doesntQualifyOne));
        assertEquals(1, pairABCCD.compareTo(aceKingLower));
        assertEquals(1, pairABCCD.compareTo(pairABCDD));
        assertEquals(0, pairABCCD.compareTo(getCloneHand(pairABCCD.getCards())));
        assertEquals(-1, pairABCCD.compareTo(pairAABCD));
        assertEquals(-1, pairABCCD.compareTo(twoPairsAABBC));
        assertEquals(-1, pairABCCD.compareTo(setAAABC));
        assertEquals(-1, pairABCCD.compareTo(straight));
        assertEquals(-1, pairABCCD.compareTo(flushHigher));
        assertEquals(-1, pairABCCD.compareTo(fullHouseAAABB));
        assertEquals(-1, pairABCCD.compareTo(careAAAAB));
        assertEquals(-1, pairABCCD.compareTo(straightFlush));
        assertEquals(-1, pairABCCD.compareTo(royalFlushTwo));
        assertEquals(true, pairABCCD.equals(getCloneHand(pairABCCD.getCards())));
        assertEquals(false, pairABCCD.equals(pairAABCD));
        assertEquals(false, pairABCCD.equals(null));
    }
    
    @Test
    public void testIComboPairABCDD() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '6'));
        assertEquals(Pair.class, pairABCDD.getHandICombination().getClass());
        assertEquals(checkKickersList, pairABCDD.getHandICombination().getKickersList());
        assertEquals(2, pairABCDD.getHandICombination().getHighness());
        assertEquals("Pair", pairABCDD.getHandICombination().getName());
        assertEquals(1, pairABCDD.compareTo(doesntQualifyOne));
        assertEquals(1, pairABCDD.compareTo(aceKingLower));
        assertEquals(0, pairABCDD.compareTo(getCloneHand(pairABCDD.getCards())));
        assertEquals(-1, pairABCDD.compareTo(pairAABCD));
        assertEquals(-1, pairABCDD.compareTo(twoPairsAABBC));
        assertEquals(-1, pairABCDD.compareTo(setAAABC));
        assertEquals(-1, pairABCDD.compareTo(straight));
        assertEquals(-1, pairABCDD.compareTo(flushHigher));
        assertEquals(-1, pairABCDD.compareTo(fullHouseAAABB));
        assertEquals(-1, pairABCDD.compareTo(careAAAAB));
        assertEquals(-1, pairABCDD.compareTo(straightFlush));
        assertEquals(-1, pairABCDD.compareTo(royalFlushTwo));
        assertEquals(true, pairABCDD.equals(getCloneHand(pairABCDD.getCards())));
        assertEquals(false, pairABCDD.equals(straightFlush));
        assertEquals(false, pairABCDD.equals(null));
    }
    
    @Test
    public void testIComboTwoPairsAABBC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(TwoPairs.class, twoPairsAABBC.getHandICombination().getClass());
        assertEquals(checkKickersList, twoPairsAABBC.getHandICombination().getKickersList());
        assertEquals(3, twoPairsAABBC.getHandICombination().getHighness());
        assertEquals("Two Pairs", twoPairsAABBC.getHandICombination().getName());
        assertEquals(1, twoPairsAABBC.compareTo(doesntQualifyOne));
        assertEquals(1, twoPairsAABBC.compareTo(aceKingLower));
        assertEquals(1, twoPairsAABBC.compareTo(pairABCDD));
        assertEquals(1, twoPairsAABBC.compareTo(twoPairsABBCC));
        assertEquals(0, twoPairsAABBC.compareTo(getCloneHand(twoPairsAABBC.getCards())));
        assertEquals(-1, twoPairsAABBC.compareTo(setAAABC));
        assertEquals(-1, twoPairsAABBC.compareTo(straight));
        assertEquals(-1, twoPairsAABBC.compareTo(flushHigher));
        assertEquals(-1, twoPairsAABBC.compareTo(fullHouseAAABB));
        assertEquals(-1, twoPairsAABBC.compareTo(careAAAAB));
        assertEquals(-1, twoPairsAABBC.compareTo(straightFlush));
        assertEquals(-1, twoPairsAABBC.compareTo(royalFlushTwo));
        assertEquals(true, twoPairsAABBC.equals(getCloneHand(twoPairsAABBC.getCards())));
        assertEquals(false, twoPairsAABBC.equals(pairABCDD));
        assertEquals(false, twoPairsAABBC.equals(null));
    }
    
    @Test
    public void testIComboTwoPairsAABCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '7'));
        assertEquals(TwoPairs.class, twoPairsAABCC.getHandICombination().getClass());
        assertEquals(checkKickersList, twoPairsAABCC.getHandICombination().getKickersList());
        assertEquals(3, twoPairsAABCC.getHandICombination().getHighness());
        assertEquals("Two Pairs", twoPairsAABCC.getHandICombination().getName());
        assertEquals(1, twoPairsAABCC.compareTo(doesntQualifyOne));
        assertEquals(1, twoPairsAABCC.compareTo(aceKingLower));
        assertEquals(1, twoPairsAABCC.compareTo(pairABCDD));
        assertEquals(1, twoPairsAABCC.compareTo(twoPairsABBCC));
        assertEquals(0, twoPairsAABCC.compareTo(getCloneHand(twoPairsAABCC.getCards())));
        assertEquals(-1, twoPairsAABCC.compareTo(setAAABC));
        assertEquals(-1, twoPairsAABCC.compareTo(straight));
        assertEquals(-1, twoPairsAABCC.compareTo(flushHigher));
        assertEquals(-1, twoPairsAABCC.compareTo(fullHouseAAABB));
        assertEquals(-1, twoPairsAABCC.compareTo(careAAAAB));
        assertEquals(-1, twoPairsAABCC.compareTo(straightFlush));
        assertEquals(-1, twoPairsAABCC.compareTo(royalFlushTwo));
        assertEquals(true, twoPairsAABCC.equals(getCloneHand(twoPairsAABCC.getCards())));
        assertEquals(false, twoPairsAABCC.equals(pairABCDD));
        assertEquals(false, twoPairsAABCC.equals(null));
    }
    
    @Test
    public void testIComboTwoPairsABBCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '7'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'A'));
        assertEquals(TwoPairs.class, twoPairsABBCC.getHandICombination().getClass());
        assertEquals(checkKickersList, twoPairsABBCC.getHandICombination().getKickersList());
        assertEquals(3, twoPairsABBCC.getHandICombination().getHighness());
        assertEquals("Two Pairs", twoPairsABBCC.getHandICombination().getName());
        assertEquals(1, twoPairsABBCC.compareTo(doesntQualifyOne));
        assertEquals(1, twoPairsABBCC.compareTo(aceKingLower));
        assertEquals(1, twoPairsABBCC.compareTo(pairABCDD));
        assertEquals(0, twoPairsABBCC.compareTo(getCloneHand(twoPairsABBCC.getCards())));
        assertEquals(-1, twoPairsABBCC.compareTo(setAAABC));
        assertEquals(-1, twoPairsABBCC.compareTo(straight));
        assertEquals(-1, twoPairsABBCC.compareTo(flushHigher));
        assertEquals(-1, twoPairsABBCC.compareTo(fullHouseAAABB));
        assertEquals(-1, twoPairsABBCC.compareTo(careAAAAB));
        assertEquals(-1, twoPairsABBCC.compareTo(straightFlush));
        assertEquals(-1, twoPairsABBCC.compareTo(royalFlushTwo));
        assertEquals(true, twoPairsABBCC.equals(getCloneHand(twoPairsABBCC.getCards())));
        assertEquals(false, twoPairsABBCC.equals(pairABCDD));
        assertEquals(false, twoPairsABBCC.equals(null));
    }
    
    @Test
    public void testCombinationSetAAABC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '7'));
        checkKickersList.add(new Card(CardSuit.SPADES, '5'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(TreeOfKind.class, setAAABC.getHandICombination().getClass());
        assertEquals(checkKickersList, setAAABC.getHandICombination().getKickersList());
        assertEquals(4, setAAABC.getHandICombination().getHighness());
        assertEquals("Tree of a Kind", setAAABC.getHandICombination().getName());
        assertEquals(1, setAAABC.compareTo(doesntQualifyOne));
        assertEquals(1, setAAABC.compareTo(aceKingLower));
        assertEquals(1, setAAABC.compareTo(pairABCDD));
        assertEquals(1, setAAABC.compareTo(twoPairsABBCC));
        assertEquals(0, setAAABC.compareTo(getCloneHand(setAAABC.getCards())));
        assertEquals(-1, setAAABC.compareTo(setABBBC));
        assertEquals(-1, setAAABC.compareTo(straight));
        assertEquals(-1, setAAABC.compareTo(flushHigher));
        assertEquals(-1, setAAABC.compareTo(fullHouseAAABB));
        assertEquals(-1, setAAABC.compareTo(careAAAAB));
        assertEquals(-1, setAAABC.compareTo(straightFlush));
        assertEquals(-1, setAAABC.compareTo(royalFlushTwo));
        assertEquals(true, setAAABC.equals(getCloneHand(setAAABC.getCards())));
        assertEquals(false, setAAABC.equals(pairABCDD));
        assertEquals(false, setAAABC.equals(null));
    }
    
    @Test
    public void testIComboSetABBBC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'J'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, '2'));
        assertEquals(TreeOfKind.class, setABBBC.getHandICombination().getClass());
        assertEquals(checkKickersList, setABBBC.getHandICombination().getKickersList());
        assertEquals(4, setABBBC.getHandICombination().getHighness());
        assertEquals("Tree of a Kind", setABBBC.getHandICombination().getName());
        assertEquals(1, setABBBC.compareTo(doesntQualifyOne));
        assertEquals(1, setABBBC.compareTo(aceKingLower));
        assertEquals(1, setABBBC.compareTo(pairABCDD));
        assertEquals(1, setABBBC.compareTo(twoPairsABBCC));
        assertEquals(1, setABBBC.compareTo(setABCCC));
        assertEquals(0, setABBBC.compareTo(getCloneHand(setABBBC.getCards())));
        assertEquals(-1, setABBBC.compareTo(straight));
        assertEquals(-1, setABBBC.compareTo(flushHigher));
        assertEquals(-1, setABBBC.compareTo(fullHouseAAABB));
        assertEquals(-1, setABBBC.compareTo(careAAAAB));
        assertEquals(-1, setABBBC.compareTo(straightFlush));
        assertEquals(-1, setABBBC.compareTo(royalFlushTwo));
        assertEquals(true, setABBBC.equals(getCloneHand(setABBBC.getCards())));
        assertEquals(false, setABBBC.equals(pairABCDD));
        assertEquals(false, setABBBC.equals(null));
    }
        
    @Test
    public void testIComboSetABCCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.CLUBS, 'J'));
        assertEquals(TreeOfKind.class, setABCCC.getHandICombination().getClass());
        assertEquals(checkKickersList, setABCCC.getHandICombination().getKickersList());
        assertEquals(4, setABCCC.getHandICombination().getHighness());
        assertEquals("Tree of a Kind", setABCCC.getHandICombination().getName());
        assertEquals(1, setABCCC.compareTo(doesntQualifyOne));
        assertEquals(1, setABCCC.compareTo(aceKingLower));
        assertEquals(1, setABCCC.compareTo(pairABCDD));
        assertEquals(1, setABCCC.compareTo(twoPairsABBCC));
        assertEquals(0, setABCCC.compareTo(getCloneHand(setABCCC.getCards())));
        assertEquals(-1, setABCCC.compareTo(setAAABC));
        assertEquals(-1, setABCCC.compareTo(straight));
        assertEquals(-1, setABCCC.compareTo(flushHigher));
        assertEquals(-1, setABCCC.compareTo(fullHouseAAABB));
        assertEquals(-1, setABCCC.compareTo(careAAAAB));
        assertEquals(-1, setABCCC.compareTo(straightFlush));
        assertEquals(-1, setABCCC.compareTo(royalFlushTwo));
        assertEquals(true, setABCCC.equals(getCloneHand(setABCCC.getCards())));
        assertEquals(false, setABCCC.equals(pairABCDD));
        assertEquals(false, setABCCC.equals(null));
    }
    
    @Test
    public void testIComboStraight() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'K'));
        assertEquals(Straight.class, straight.getHandICombination().getClass());
        assertEquals(checkKickersList, straight.getHandICombination().getKickersList());
        assertEquals(5, straight.getHandICombination().getHighness());
        assertEquals("Straight", straight.getHandICombination().getName());
        assertEquals(1, straight.compareTo(doesntQualifyOne));
        assertEquals(1, straight.compareTo(aceKingLower));
        assertEquals(1, straight.compareTo(pairABCDD));
        assertEquals(1, straight.compareTo(twoPairsABBCC));
        assertEquals(1, straight.compareTo(setAAABC));
        assertEquals(1, straight.compareTo(straightWheel));
        assertEquals(0, straight.compareTo(getCloneHand(straight.getCards())));
        assertEquals(-1, straight.compareTo(flushHigher));
        assertEquals(-1, straight.compareTo(fullHouseAAABB));
        assertEquals(-1, straight.compareTo(careAAAAB));
        assertEquals(-1, straight.compareTo(straightFlush));
        assertEquals(-1, straight.compareTo(royalFlushTwo));
        assertEquals(true, straight.equals(getCloneHand(straight.getCards())));
        assertEquals(false, straight.equals(pairABCDD));
        assertEquals(false, straight.equals(null));

    }
    
    @Test
    public void testIComboWheelStraight() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '5'));
        assertEquals(Straight.class, straightWheel.getHandICombination().getClass());
        assertEquals(checkKickersList, straightWheel.getHandICombination().getKickersList());
        assertEquals(5, straightWheel.getHandICombination().getHighness());
        assertEquals("Straight", straightWheel.getHandICombination().getName());
        assertEquals(1, straightWheel.compareTo(doesntQualifyOne));
        assertEquals(1, straightWheel.compareTo(aceKingLower));
        assertEquals(1, straightWheel.compareTo(pairABCDD));
        assertEquals(1, straightWheel.compareTo(twoPairsABBCC));
        assertEquals(1, straightWheel.compareTo(setAAABC));
        assertEquals(0, straightWheel.compareTo(getCloneHand(straightWheel.getCards())));
        assertEquals(-1, straightWheel.compareTo(straight));
        assertEquals(-1, straightWheel.compareTo(flushHigher));
        assertEquals(-1, straightWheel.compareTo(fullHouseAAABB));
        assertEquals(-1, straightWheel.compareTo(careAAAAB));
        assertEquals(-1, straightWheel.compareTo(straightFlush));
        assertEquals(-1, straightWheel.compareTo(royalFlushTwo));
        assertEquals(true, straightWheel.equals(getCloneHand(straightWheel.getCards())));
        assertEquals(false, straightWheel.equals(pairABCDD));
        assertEquals(false, straightWheel.equals(null));
    }
    
    @Test
    public void testIComboIsFlush(){
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'J'));
        checkKickersList.add(new Card(CardSuit.SPADES, '6'));
        checkKickersList.add(new Card(CardSuit.SPADES, '3'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        assertEquals(Flush.class, flushHigher.getHandICombination().getClass());
        assertEquals(checkKickersList, flushHigher.getHandICombination().getKickersList());
        assertEquals(6, flushHigher.getHandICombination().getHighness());
        assertEquals("Flush", flushHigher.getHandICombination().getName());
        assertEquals(1, flushHigher.compareTo(doesntQualifyOne));
        assertEquals(1, flushHigher.compareTo(aceKingLower));
        assertEquals(1, flushHigher.compareTo(pairABCDD));
        assertEquals(1, flushHigher.compareTo(twoPairsABBCC));
        assertEquals(1, flushHigher.compareTo(setAAABC));
        assertEquals(1, flushHigher.compareTo(straight));
        assertEquals(1, flushHigher.compareTo(flushLower));
        assertEquals(0, flushHigher.compareTo(getCloneHand(flushHigher.getCards())));
        assertEquals(-1, flushHigher.compareTo(fullHouseAAABB));
        assertEquals(-1, flushHigher.compareTo(careAAAAB));
        assertEquals(-1, flushHigher.compareTo(straightFlush));
        assertEquals(-1, flushHigher.compareTo(royalFlushTwo));
        assertEquals(true, flushHigher.equals(getCloneHand(flushHigher.getCards())));
        assertEquals(false, flushHigher.equals(pairABCDD));
        assertEquals(false, flushHigher.equals(null));
    }
    
    @Test
    public void testIComboFullHouseAAACC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        assertEquals(FullHouse.class, fullHouseAAABB.getHandICombination().getClass());
        assertEquals(checkKickersList, fullHouseAAABB.getHandICombination().getKickersList());
        assertEquals(7, fullHouseAAABB.getHandICombination().getHighness());
        assertEquals("Full House", fullHouseAAABB.getHandICombination().getName());
        assertEquals(1, fullHouseAAABB.compareTo(doesntQualifyOne));
        assertEquals(1, fullHouseAAABB.compareTo(aceKingLower));
        assertEquals(1, fullHouseAAABB.compareTo(pairABCDD));
        assertEquals(1, fullHouseAAABB.compareTo(twoPairsABBCC));
        assertEquals(1, fullHouseAAABB.compareTo(setAAABC));
        assertEquals(1, fullHouseAAABB.compareTo(straight));
        assertEquals(1, fullHouseAAABB.compareTo(flushLower));
        assertEquals(1, fullHouseAAABB.compareTo(fullHouseAABBB));
        assertEquals(0, fullHouseAAABB.compareTo(getCloneHand(fullHouseAAABB.getCards())));
        assertEquals(-1, fullHouseAAABB.compareTo(careAAAAB));
        assertEquals(-1, fullHouseAAABB.compareTo(straightFlush));
        assertEquals(-1, fullHouseAAABB.compareTo(royalFlushTwo));
        assertEquals(true, fullHouseAAABB.equals(getCloneHand(fullHouseAAABB.getCards())));
        assertEquals(false, fullHouseAAABB.equals(pairABCDD));
        assertEquals(false, fullHouseAAABB.equals(null));
    }
    
    @Test
    public void testIComboFullHouseAACCC() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        assertEquals(FullHouse.class, fullHouseAABBB.getHandICombination().getClass());
        assertEquals(checkKickersList, fullHouseAABBB.getHandICombination().getKickersList());
        assertEquals(7, fullHouseAABBB.getHandICombination().getHighness());
        assertEquals("Full House", fullHouseAABBB.getHandICombination().getName());
        assertEquals(1, fullHouseAABBB.compareTo(doesntQualifyOne));
        assertEquals(1, fullHouseAABBB.compareTo(aceKingLower));
        assertEquals(1, fullHouseAABBB.compareTo(pairABCDD));
        assertEquals(1, fullHouseAABBB.compareTo(twoPairsABBCC));
        assertEquals(1, fullHouseAABBB.compareTo(setAAABC));
        assertEquals(1, fullHouseAABBB.compareTo(straight));
        assertEquals(1, fullHouseAABBB.compareTo(flushLower));
        assertEquals(0, fullHouseAABBB.compareTo(getCloneHand(fullHouseAABBB.getCards())));
        assertEquals(-1, fullHouseAABBB.compareTo(fullHouseAAABB));
        assertEquals(-1, fullHouseAABBB.compareTo(careAAAAB));
        assertEquals(-1, fullHouseAABBB.compareTo(straightFlush));
        assertEquals(-1, fullHouseAABBB.compareTo(royalFlushTwo));
        assertEquals(true, fullHouseAABBB.equals(getCloneHand(fullHouseAABBB.getCards())));
        assertEquals(false, fullHouseAABBB.equals(pairABCDD));
        assertEquals(false, fullHouseAABBB.equals(null));
    }
    
    @Test
    public void testIComboFourOfaKindAAAAB() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, 'A'));
        checkKickersList.add(new Card(CardSuit.SPADES, '2'));
        assertEquals(FourOfKind.class, careAAAAB.getHandICombination().getClass());
        assertEquals(checkKickersList, careAAAAB.getHandICombination().getKickersList());
        assertEquals(8, careAAAAB.getHandICombination().getHighness());
        assertEquals("Four Of a Kind", careAAAAB.getHandICombination().getName());
        assertEquals(1, careAAAAB.compareTo(doesntQualifyOne));
        assertEquals(1, careAAAAB.compareTo(aceKingLower));
        assertEquals(1, careAAAAB.compareTo(pairABCDD));
        assertEquals(1, careAAAAB.compareTo(twoPairsABBCC));
        assertEquals(1, careAAAAB.compareTo(setAAABC));
        assertEquals(1, careAAAAB.compareTo(straight));
        assertEquals(1, careAAAAB.compareTo(flushLower));
        assertEquals(1, careAAAAB.compareTo(fullHouseAAABB));
        assertEquals(1, careAAAAB.compareTo(careABBBB));
        assertEquals(0, careAAAAB.compareTo(getCloneHand(careAAAAB.getCards())));
        assertEquals(-1, careAAAAB.compareTo(straightFlush));
        assertEquals(-1, careAAAAB.compareTo(royalFlushTwo));
        assertEquals(true, careAAAAB.equals(getCloneHand(careAAAAB.getCards())));
        assertEquals(false, careAAAAB.equals(pairABCDD));
        assertEquals(false, careAAAAB.equals(null));
    }
    
    @Test
    public void testIComboFourOfaKindABBBB() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.DIAMOND, '2'));
        checkKickersList.add(new Card(CardSuit.SPADES, 'A'));
        assertEquals(FourOfKind.class, careABBBB.getHandICombination().getClass());
        assertEquals(checkKickersList, careABBBB.getHandICombination().getKickersList());
        assertEquals(8, careABBBB.getHandICombination().getHighness());
        assertEquals("Four Of a Kind", careABBBB.getHandICombination().getName());
        assertEquals(1, careABBBB.compareTo(doesntQualifyOne));
        assertEquals(1, careABBBB.compareTo(aceKingLower));
        assertEquals(1, careABBBB.compareTo(pairABCDD));
        assertEquals(1, careABBBB.compareTo(twoPairsABBCC));
        assertEquals(1, careABBBB.compareTo(setAAABC));
        assertEquals(1, careABBBB.compareTo(straight));
        assertEquals(1, careABBBB.compareTo(flushLower));
        assertEquals(1, careABBBB.compareTo(fullHouseAAABB));
        assertEquals(0, careABBBB.compareTo(getCloneHand(careABBBB.getCards())));
        assertEquals(-1, careABBBB.compareTo(careAAAAB));
        assertEquals(-1, careABBBB.compareTo(straightFlush));
        assertEquals(-1, careABBBB.compareTo(royalFlushTwo));
        assertEquals(true, careABBBB.equals(getCloneHand(careABBBB.getCards())));
        assertEquals(false, careABBBB.equals(pairABCDD));
        assertEquals(false, careABBBB.equals(null));
    }

    @Test
    public void testIComboStraightFlush() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.CLUBS, 'K'));
        assertEquals(StraightFlush.class, straightFlush.getHandICombination().getClass());
        assertEquals(checkKickersList, straightFlush.getHandICombination().getKickersList());
        assertEquals(9, straightFlush.getHandICombination().getHighness());
        assertEquals("Straight Flush", straightFlush.getHandICombination().getName());
        assertEquals(1, straightFlush.compareTo(doesntQualifyOne));
        assertEquals(1, straightFlush.compareTo(aceKingLower));
        assertEquals(1, straightFlush.compareTo(pairABCDD));
        assertEquals(1, straightFlush.compareTo(twoPairsABBCC));
        assertEquals(1, straightFlush.compareTo(setAAABC));
        assertEquals(1, straightFlush.compareTo(straight));
        assertEquals(1, straightFlush.compareTo(flushLower));
        assertEquals(1, straightFlush.compareTo(fullHouseAAABB));
        assertEquals(1, straightFlush.compareTo(careAAAAB));
        assertEquals(1, straightFlush.compareTo(straightFlushWheel));
        assertEquals(0, straightFlush.compareTo(getCloneHand(straightFlush.getCards())));
        assertEquals(-1, straightFlush.compareTo(royalFlushTwo));
        assertEquals(true, straightFlush.equals(getCloneHand(straightFlush.getCards())));
        assertEquals(false, straightFlush.equals(pairABCDD));
        assertEquals(false, straightFlush.equals(null));
    }
    
    @Test
    public void testIComboWheelStraightFlush() {
        List<Card> checkKickersList = new ArrayList<Card>();
        checkKickersList.add(new Card(CardSuit.CLUBS, '5'));
        assertEquals(StraightFlush.class, straightFlushWheel.getHandICombination().getClass());
        assertEquals(checkKickersList, straightFlushWheel.getHandICombination().getKickersList());
        assertEquals(9, straightFlushWheel.getHandICombination().getHighness());
        assertEquals("Straight Flush", straightFlushWheel.getHandICombination().getName());
        assertEquals(1, straightFlushWheel.compareTo(doesntQualifyOne));
        assertEquals(1, straightFlushWheel.compareTo(aceKingLower));
        assertEquals(1, straightFlushWheel.compareTo(pairABCDD));
        assertEquals(1, straightFlushWheel.compareTo(twoPairsABBCC));
        assertEquals(1, straightFlushWheel.compareTo(setAAABC));
        assertEquals(1, straightFlushWheel.compareTo(straight));
        assertEquals(1, straightFlushWheel.compareTo(flushLower));
        assertEquals(1, straightFlushWheel.compareTo(fullHouseAAABB));
        assertEquals(1, straightFlushWheel.compareTo(careAAAAB));
        assertEquals(0, straightFlushWheel.compareTo(getCloneHand(straightFlushWheel.getCards())));
        assertEquals(-1, straightFlushWheel.compareTo(straightFlush));
        assertEquals(-1, straightFlushWheel.compareTo(royalFlushTwo));
        assertEquals(true, straightFlushWheel.equals(getCloneHand(straightFlushWheel.getCards())));
        assertEquals(false, straightFlushWheel.equals(pairABCDD));
        assertEquals(false, straightFlushWheel.equals(null));
    }
    
    @Test
    public void testIComboRoyalFlush() {
        assertEquals(RoyalFlush.class, royalFlushOne.getHandICombination().getClass());
        assertEquals(10, royalFlushOne.getHandICombination().getHighness());
        assertEquals("Royal Flush", royalFlushOne.getHandICombination().getName());
        assertEquals(1, royalFlushOne.compareTo(doesntQualifyOne));
        assertEquals(1, royalFlushOne.compareTo(aceKingLower));
        assertEquals(1, royalFlushOne.compareTo(pairABCDD));
        assertEquals(1, royalFlushOne.compareTo(twoPairsABBCC));
        assertEquals(1, royalFlushOne.compareTo(setAAABC));
        assertEquals(1, royalFlushOne.compareTo(straight));
        assertEquals(1, royalFlushOne.compareTo(flushLower));
        assertEquals(1, royalFlushOne.compareTo(fullHouseAAABB));
        assertEquals(1, royalFlushOne.compareTo(careAAAAB));
        assertEquals(1, royalFlushOne.compareTo(straightFlushWheel));
        assertEquals(1, royalFlushOne.compareTo(straightFlush));
        assertEquals(0, royalFlushOne.compareTo(royalFlushTwo));
        assertEquals(true, royalFlushOne.equals(royalFlushTwo));
        assertEquals(false, royalFlushOne.equals(pairABCDD));
        assertEquals(false, royalFlushOne.equals(null));
        
    }
    
    @Test
    public void testICombo(){
        
    }
}
