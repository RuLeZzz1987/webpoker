package com.rulezzz.pkr.core.combination;

import com.rulezzz.pkr.core.basestructures.Card;
import com.rulezzz.pkr.core.basestructures.CardSuit;
import com.rulezzz.pkr.core.engine.Hand;

public class ComboSamples {
    

    
    public static Hand getDoesntQualifyOneHand() {
        Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
                new Card(CardSuit.DIAMOND, 'J'),
                new Card(CardSuit.SPADES, '6'),
                new Card(CardSuit.HEART, '3'),
                new Card(CardSuit.CLUBS, '2'));
        return hand;
    }
    
    public static Hand getDoesntQualifyTwoHand() {
        Hand hand = new Hand(new Card(CardSuit.CLUBS, 'K'),
                new Card(CardSuit.DIAMOND, 'T'),
                new Card(CardSuit.SPADES, '9'),
                new Card(CardSuit.HEART, '5'),
                new Card(CardSuit.CLUBS, '4'));
        return hand;
    }
    
    public static Hand getAceKingHigherHand() {
        Hand hand = new Hand(new Card(CardSuit.CLUBS, 'A'),
                new Card(CardSuit.DIAMOND, 'K'),
                new Card(CardSuit.HEART, 'Q'),
                new Card(CardSuit.CLUBS, '3'),
                new Card(CardSuit.CLUBS, '2'));
        return hand;
    }
}
