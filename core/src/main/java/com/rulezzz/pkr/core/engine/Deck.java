package com.rulezzz.pkr.core.engine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.rulezzz.pkr.core.combination.Card;
import com.rulezzz.pkr.core.combination.CardSuit;

public class Deck {

    private List<Card> deck = new LinkedList<Card>();
    private List<Card> used = new LinkedList<Card>();
    private static final int CARDRANKS = 15;

    public List<Card> getDeck() {
        return this.deck;

    }

    public Deck() {

        for (CardSuit suit : CardSuit.values()) {
            for (int j = 2; j < CARDRANKS; j++) {
                Card card = new Card(suit, j, j);
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
