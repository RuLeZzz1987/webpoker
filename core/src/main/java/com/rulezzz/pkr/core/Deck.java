package com.rulezzz.pkr.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private List<Card> deck = new LinkedList<Card>();
    private List<Card> used = new LinkedList<Card>();
    private static final int CARDRANKS = 15;

    public List<Card> getDeck() {
        return deck;

    }

    public Deck() {

        for (CardSuit suit : CardSuit.values()) {
            for (int j = 2; j < CARDRANKS; j++) {
                Card card = new Card(suit, j, j);
                deck.add(card);
            }

        }
        Collections.shuffle(deck);

    }

    public List<Card> getUsed() {
        return used;
    }

    public void setUsed(Card usedCard) {
        this.used.add(usedCard);
    }

}
