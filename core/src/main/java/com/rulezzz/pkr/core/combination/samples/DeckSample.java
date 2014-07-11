package com.rulezzz.pkr.core.combination.samples;

import static com.rulezzz.pkr.core.combination.samples.ComboSamples.getSetAAABC;
import static com.rulezzz.pkr.core.combination.samples.ComboSamples.getAceKingHigher;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.rulezzz.pkr.core.base.structures.Deck;
import com.rulezzz.pkr.core.base.structures.Hand;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.card.CardSuit;


public class DeckSample {

    public static Deck getSetAAABCvsAceKingHigh() {
        List<Card> result = new LinkedList<Card>();
        for (int i = getSetAAABC().getCards().size() - 1; i >= 0; i--) {
            result.add(getSetAAABC().getCards().get(i));
            result.add(getAceKingHigher().getCards().get(i));
        }
        result.add(new Card(CardSuit.HEART, '7'));
        return new Deck(result);
    }
    
    public static Deck getHandAvsHandB(Hand handA, Hand handB, Card... preparedCards) {
        List<Card> result = new LinkedList<Card>();
        for (int i = handA.getCards().size() - 1; i >= 0; i--) {
            result.add(handA.getCards().get(i));
            result.add(handB.getCards().get(i));
        }
        result.addAll(Arrays.asList(preparedCards));
        return new Deck(result);
    }
}
