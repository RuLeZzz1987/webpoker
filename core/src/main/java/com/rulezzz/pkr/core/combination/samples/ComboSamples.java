package com.rulezzz.pkr.core.combination.samples;

import com.rulezzz.pkr.core.base.structures.Hand;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.card.CardSuit;

public final class ComboSamples {
    

    private ComboSamples() {}
    
    public static Hand getDoesntQualifyOne() {
        Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
                new Card(CardSuit.DIAMOND, 'J'),
                new Card(CardSuit.SPADES, '6'),
                new Card(CardSuit.HEART, '3'),
                new Card(CardSuit.CLUBS, '2'));
        return hand;
    }
    
    public static Hand getDoesntQualifyTwo() {
        Hand hand = new Hand(new Card(CardSuit.CLUBS, 'K'),
                new Card(CardSuit.DIAMOND, 'T'),
                new Card(CardSuit.SPADES, '9'),
                new Card(CardSuit.HEART, '5'),
                new Card(CardSuit.CLUBS, '4'));
        return hand;
    }
    
    public static Hand getAceKingHigher() {
        Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
                new Card(CardSuit.DIAMOND, 'K'),
                new Card(CardSuit.HEART, 'Q'),
                new Card(CardSuit.CLUBS, '3'),
                new Card(CardSuit.CLUBS, '2'));
        return hand;
    }
    
    public static Hand getAceKingLower() {
    Hand hand = new Hand(new Card(CardSuit.SPADES, 'A'),
            new Card(CardSuit.HEART, 'K'),
            new Card(CardSuit.CLUBS, 'T'),
            new Card(CardSuit.DIAMOND, '7'),
            new Card(CardSuit.SPADES, '6'));
    return hand;
    }

    public static Hand getPairAABCD() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
        new Card(CardSuit.DIAMOND, 'A'),
        new Card(CardSuit.SPADES, 'K'),
        new Card(CardSuit.CLUBS, '5'),
        new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getPairABBCD() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),    
        new Card(CardSuit.DIAMOND, 'K'),
        new Card(CardSuit.CLUBS, 'K'),
        new Card(CardSuit.SPADES, '3'),
        new Card(CardSuit.SPADES, '2'));
    return hand;
    }
    
    public static Hand getPairABCCD() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),    
        new Card(CardSuit.DIAMOND, 'K'),
        new Card(CardSuit.CLUBS, '6'),
        new Card(CardSuit.HEART, '6'),
        new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getPairABCDD() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
        new Card(CardSuit.DIAMOND, 'J'),
        new Card(CardSuit.CLUBS, '6'),
        new Card(CardSuit.HEART, '2'),
        new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getTwoPairsAABBC() {
    Hand hand= new Hand(new Card(CardSuit.CLUBS, 'A'),
        new Card(CardSuit.DIAMOND, 'A'),
        new Card(CardSuit.CLUBS, 'K'),
        new Card(CardSuit.HEART, 'K'),
        new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getTwoPairsAABCC() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
        new Card(CardSuit.DIAMOND, 'A'),
        new Card(CardSuit.CLUBS, 'K'),
        new Card(CardSuit.HEART, '6'),
        new Card(CardSuit.CLUBS, '6'));
    return hand;
    }
    
    public static Hand getTwoPairsABBCC() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
        new Card(CardSuit.DIAMOND, 'K'),
        new Card(CardSuit.CLUBS, 'K'),
        new Card(CardSuit.HEART, '6'),
        new Card(CardSuit.CLUBS, '6'));
    return hand;
    }
    
    public static Hand getSetAAABC() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, '7'),
        new Card(CardSuit.DIAMOND, '7'),
        new Card(CardSuit.SPADES, '7'),
        new Card(CardSuit.CLUBS, '5'),
        new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getSetKKKBC() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'K'),
        new Card(CardSuit.DIAMOND, 'K'),
        new Card(CardSuit.SPADES, 'K'),
        new Card(CardSuit.CLUBS, '5'),
        new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getSetAAACD() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
        new Card(CardSuit.DIAMOND, 'A'),
        new Card(CardSuit.SPADES, 'A'),
        new Card(CardSuit.CLUBS, '5'),
        new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getSetABBBC() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
        new Card(CardSuit.DIAMOND, 'J'),
        new Card(CardSuit.CLUBS, 'J'),
        new Card(CardSuit.SPADES, 'J'),
        new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getSetABCCC() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
        new Card(CardSuit.DIAMOND, 'J'),
        new Card(CardSuit.DIAMOND, '2'),
        new Card(CardSuit.SPADES, '2'),
        new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getFullHouseAAABB() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
            new Card(CardSuit.DIAMOND, 'A'),
            new Card(CardSuit.HEART, 'A'),
            new Card(CardSuit.SPADES, '2'),
            new Card(CardSuit.CLUBS, '2'));
    return hand;
    }

    public static Hand getFullHouseAABBB() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'K'),
            new Card(CardSuit.DIAMOND, 'K'),
            new Card(CardSuit.SPADES, '2'),
            new Card(CardSuit.HEART, '2'),
            new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getCareAAAAB() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
            new Card(CardSuit.DIAMOND, 'A'),
            new Card(CardSuit.HEART, 'A'),
            new Card(CardSuit.SPADES, 'A'),
            new Card(CardSuit.CLUBS, '2'));
    return hand;
    }

    public static Hand getCareABBBB() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
            new Card(CardSuit.DIAMOND, '2'),
            new Card(CardSuit.HEART, '2'),
            new Card(CardSuit.SPADES, '2'),
            new Card(CardSuit.CLUBS, '2'));
    return hand;
    }

    public static Hand getStraight() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'K'),
        new Card(CardSuit.DIAMOND, 'Q'),
        new Card(CardSuit.HEART, 'J'),
        new Card(CardSuit.SPADES, 'T'),
        new Card(CardSuit.CLUBS, '9'));
    return hand;
    }
    
    public static Hand getStraightWheel() {
    Hand hand= new Hand(new Card(CardSuit.CLUBS, 'A'),
            new Card(CardSuit.DIAMOND, '5'),
            new Card(CardSuit.HEART, '4'),
            new Card(CardSuit.SPADES, '3'),
            new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getStraightFlush() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'K'),
            new Card(CardSuit.CLUBS, 'Q'),
            new Card(CardSuit.CLUBS, 'J'),
            new Card(CardSuit.CLUBS, 'T'),
            new Card(CardSuit.CLUBS, '9'));
    return hand;
    }
    
    public static Hand getStraightFlushWheel() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
                new Card(CardSuit.CLUBS, '5'),
                new Card(CardSuit.CLUBS, '4'),
                new Card(CardSuit.CLUBS, '3'),
                new Card(CardSuit.CLUBS, '2'));
    return hand;
    }
    
    public static Hand getRoyalFlushOne() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
            new Card(CardSuit.CLUBS, 'K'),
            new Card(CardSuit.CLUBS, 'Q'),
            new Card(CardSuit.CLUBS, 'J'),
            new Card(CardSuit.CLUBS, 'T'));
    return hand;
    }
    
    public static Hand getRoyalFlushTwo() {
    Hand hand= new Hand(new Card(CardSuit.DIAMOND, 'A'),
            new Card(CardSuit.DIAMOND, 'K'),
            new Card(CardSuit.DIAMOND, 'Q'),
            new Card(CardSuit.DIAMOND, 'J'),
            new Card(CardSuit.DIAMOND, 'T'));
    return hand;
    }
    
    public static Hand getFlushHigher() {
    Hand hand = new Hand(new Card(CardSuit.SPADES, 'A'),
            new Card(CardSuit.SPADES, 'J'),
            new Card(CardSuit.SPADES, '6'),
            new Card(CardSuit.SPADES, '3'),
            new Card(CardSuit.SPADES, '2'));
    return hand;
    }
    
    public static Hand getFlushLower() {
    Hand hand = new Hand(new Card(CardSuit.HEART, 'Q'),
            new Card(CardSuit.HEART, '9'),
            new Card(CardSuit.HEART, '8'),
            new Card(CardSuit.HEART, '7'),
            new Card(CardSuit.HEART, '6'));
    return hand;
    }
    
    public static Hand getLongStraight() {
    Hand hand = new Hand(new Card(CardSuit.CLUBS, 'K'),
        new Card(CardSuit.DIAMOND, 'Q'),
        new Card(CardSuit.HEART, 'J'),
        new Card(CardSuit.SPADES, 'T'),
        new Card(CardSuit.CLUBS, '9'),
        new Card(CardSuit.HEART, 'A'));
    return hand;
    }
}
