package com.rulezzz.pkr.core.base.structures;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.card.CardSuit;

public class Deck {

    private List<Card> deck = new LinkedList<Card>();
    private List<Card> used = new LinkedList<Card>();
    private static final int CARDRANKS = 15;

    public List<Card> getDeck() {
        return this.deck;
    }

    public Deck() {
        for (CardSuit suit : CardSuit.values()) {
            for (int i = 2; i < CARDRANKS; i++) {
                Card card = new Card(suit, i, i);
                this.deck.add(card);
            }
        }
        Collections.shuffle(this.deck);

    }

    public List<Card> getUsed() {
        return this.used;
    }

    public void setUsed(Card usedCard) {
        this.used.add(usedCard);
    }

}
