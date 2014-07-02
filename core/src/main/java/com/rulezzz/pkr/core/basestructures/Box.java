package com.rulezzz.pkr.core.basestructures;

import java.util.List;

import com.rulezzz.pkr.core.card.Card;

public class Box {

    private Hand hand = new Hand();

    public Hand getHand() {
        return this.hand;
    }

    public void sort() {
        this.hand.sort();
    }

    public void addCard(final Card card) {
        this.hand.add(card);
    }
    
    public void addCards(final List<Card> cards) {
        this.hand.add(cards);
    }

    public void setHand(final Hand preparedHand) {
        this.hand = preparedHand;
    }

    @Override
    public String toString() {
        return this.hand.toString();
    }

    public enum BoxStatus {
        DEALED, FOLD, BET, DRAW, DETERMINATION, WAIT_FOR_DRAW, BUY_CARD, BUY_GAME, TAKE_ANTE;
    }
}
