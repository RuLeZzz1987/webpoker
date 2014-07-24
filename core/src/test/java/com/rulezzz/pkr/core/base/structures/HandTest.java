package com.rulezzz.pkr.core.base.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.rulezzz.pkr.core.base.engines.GameMath;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.card.CardSuit;
import com.rulezzz.pkr.core.combination.AceKing;
import com.rulezzz.pkr.core.combination.DoesntQualify;
import com.rulezzz.pkr.core.combination.Flush;
import com.rulezzz.pkr.core.combination.FourOfKind;
import com.rulezzz.pkr.core.combination.FullHouse;
import com.rulezzz.pkr.core.combination.Pair;
import com.rulezzz.pkr.core.combination.Straight;
import com.rulezzz.pkr.core.combination.ThreeOfKind;
import com.rulezzz.pkr.core.combination.TwoPairs;
import com.rulezzz.pkr.core.combination.samples.ComboSamples;

public class HandTest {

    private Hand hand;

    @Before
    public void setUp() {
        hand = new Hand();
    }
    
    @Test
    public void testMainAndAdditionalCombosFrom6Cards(){
        hand = ComboSamples.getDoesntQualifyOne();
        hand.add(new Card(CardSuit.HEART, '8'));
        
        assertEquals(DoesntQualify.class, hand.getHandAbstractCombination().getClass());
        assertNull(hand.getHandAdditionalAbstractCombination());
        
        hand = ComboSamples.getAceKingHigher();
        hand.add(new Card(CardSuit.HEART, '8'));
        
        assertEquals(AceKing.class, hand.getHandAbstractCombination().getClass());
        assertNull(hand.getHandAdditionalAbstractCombination());

        hand = ComboSamples.getPairAABCD();
        hand.add(new Card(CardSuit.HEART, '8'));
        
        assertEquals(Pair.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getPairABBCD();
        hand.add(new Card(CardSuit.HEART, '8'));
        
        assertEquals(Pair.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getPairABCCD();
        hand.add(new Card(CardSuit.HEART, '8'));
        
        assertEquals(Pair.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getPairABCDD();
        hand.add(new Card(CardSuit.HEART, '8'));
        
        assertEquals(Pair.class, hand.getHandAbstractCombination().getClass());
        assertNull(hand.getHandAdditionalAbstractCombination());
        
        hand = ComboSamples.getTwoPairsAABBC();
        hand.add(new Card(CardSuit.HEART, '8'));
        
        assertEquals(TwoPairs.class, hand.getHandAbstractCombination().getClass());
        //assertNull(hand.getHandAdditionalAbstractCombination());
        
        hand = ComboSamples.getTwoPairsAABBC();
        hand.add(new Card(CardSuit.HEART, '2'));
        
        assertEquals(TwoPairs.class, hand.getHandAbstractCombination().getClass());
        assertEquals(TwoPairs.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getTwoPairsAABCC();
        hand.add(new Card(CardSuit.HEART, '2'));
        
        assertEquals(TwoPairs.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getTwoPairsABBCC();
        hand.add(new Card(CardSuit.HEART, '2'));
        
        assertEquals(TwoPairs.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getPairABCCD();
        hand.add(new Card(CardSuit.HEART, '2'));
        
        assertEquals(TwoPairs.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getPairABCDD();
        hand.add(new Card(CardSuit.HEART, '6'));
        
        assertEquals(TwoPairs.class, hand.getHandAbstractCombination().getClass());
        assertNull(hand.getHandAdditionalAbstractCombination());
        
        hand = ComboSamples.getSetAAABC();
        hand.add(new Card(CardSuit.HEART, '6'));
        
        assertEquals(ThreeOfKind.class, hand.getHandAbstractCombination().getClass());
        assertNull(hand.getHandAdditionalAbstractCombination());
        
        hand = ComboSamples.getSetABBBC();
        hand.add(new Card(CardSuit.HEART, 'K'));
        
        assertEquals(ThreeOfKind.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getSetKKKBC();
        hand.add(new Card(CardSuit.HEART, 'A'));
        
        assertEquals(ThreeOfKind.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getSetAAACD();
        hand.add(new Card(CardSuit.HEART, 'K'));
        
        assertEquals(ThreeOfKind.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getStraightWheel();
        hand.add(new Card(CardSuit.HEART, '5'));
        
        assertEquals(Straight.class, hand.getHandAbstractCombination().getClass());
        assertEquals(Straight.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getStraightWheel();
        hand.add(new Card(CardSuit.HEART, '6'));
        
        assertEquals(Straight.class, hand.getHandAbstractCombination().getClass());
        assertEquals(Straight.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getStraight();
        hand.add(new Card(CardSuit.HEART, 'A'));
        
        assertEquals(Straight.class, hand.getHandAbstractCombination().getClass());
        assertEquals(Straight.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getStraightWheel();
        hand.add(new Card(CardSuit.HEART, 'K'));
        
        assertEquals(Straight.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getFlushHigher();
        hand.add(new Card(CardSuit.SPADES, 'K'));
        
        assertEquals(Flush.class, hand.getHandAbstractCombination().getClass());
        assertEquals(Flush.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getFlushHigher();
        hand.add(new Card(CardSuit.HEART, 'K'));
        
        assertEquals(Flush.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getFlushHigher();
        hand.add(new Card(CardSuit.HEART, 'A'));
        
        assertEquals(Flush.class, hand.getHandAbstractCombination().getClass());
        assertEquals(Pair.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getFlushLower();
        hand.add(new Card(CardSuit.DIAMOND, 'T'));
        
        assertEquals(Flush.class, hand.getHandAbstractCombination().getClass());
        assertEquals(Straight.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getFullHouseAAABB();
        hand.add(new Card(CardSuit.HEART, '3'));
        
        assertEquals(FullHouse.class, hand.getHandAbstractCombination().getClass());
        assertNull(hand.getHandAdditionalAbstractCombination());
        
        hand = ComboSamples.getFullHouseAAABB();
        hand.add(new Card(CardSuit.HEART, '2'));
        
        assertEquals(FullHouse.class, hand.getHandAbstractCombination().getClass());
        assertEquals(FullHouse.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getFullHouseAAABB();
        hand.add(new Card(CardSuit.HEART, 'K'));
        
        assertEquals(FullHouse.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getFullHouseAABBB();
        hand.add(new Card(CardSuit.HEART, 'K'));
        
        assertEquals(FullHouse.class, hand.getHandAbstractCombination().getClass());
        assertEquals(FullHouse.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getFullHouseAABBB();
        hand.add(new Card(CardSuit.HEART, 'A'));
        
        assertEquals(FullHouse.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getFullHouseAABBB();
        hand.add(new Card(CardSuit.HEART, 'J'));
        
        assertEquals(FullHouse.class, hand.getHandAbstractCombination().getClass());
        assertNull(hand.getHandAdditionalAbstractCombination());
        
        hand = ComboSamples.getCareAAAAB();
        hand.add(new Card(CardSuit.HEART, '2'));
        
        assertEquals(FourOfKind.class, hand.getHandAbstractCombination().getClass());
        assertEquals(FullHouse.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getCareABBBB();
        hand.add(new Card(CardSuit.HEART, 'A'));
        
        assertEquals(FourOfKind.class, hand.getHandAbstractCombination().getClass());
        assertEquals(FullHouse.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        hand = ComboSamples.getCareABBBB();
        hand.add(new Card(CardSuit.HEART, 'J'));
        
        assertEquals(FourOfKind.class, hand.getHandAbstractCombination().getClass());
        assertNull(hand.getHandAdditionalAbstractCombination());
        
        hand = ComboSamples.getCareAAAAB();
        hand.add(new Card(CardSuit.HEART, 'K'));
        
        assertEquals(FourOfKind.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        
        
        
    }
    
    @Test
    public void testAddAceKingFromFiveCard() {
        hand = ComboSamples.getPairABBCD();
        assertEquals(Pair.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        hand = ComboSamples.getPairAABCD();
        assertEquals(Pair.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        hand = ComboSamples.getPairABCCD();
        assertEquals(Pair.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        hand = ComboSamples.getTwoPairsAABBC();
        assertEquals(TwoPairs.class, hand.getHandAbstractCombination().getClass());
        assertNull(null, hand.getHandAdditionalAbstractCombination());
        hand = ComboSamples.getTwoPairsABBCC();
        assertEquals(TwoPairs.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass());
        hand = ComboSamples.getTwoPairsAABCC();
        assertEquals(TwoPairs.class, hand.getHandAbstractCombination().getClass());
        assertEquals(AceKing.class, hand.getHandAdditionalAbstractCombination().getClass()); 
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
