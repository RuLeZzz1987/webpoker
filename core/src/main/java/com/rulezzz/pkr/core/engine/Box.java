package com.rulezzz.pkr.core.engine;

import java.util.List;

public class Box {

    private Hand hand = new Hand();

    public Box() {}
    
    public Hand getHand() {
        return this.hand;
    }

    public void sort() {
        this.hand.sort();
    }

    public void setHand(final Card card) {
        this.hand.add(card);
    }
    
    public void setHand(final List<Card> cards) {
        this.hand.add(cards);
    }

    public void setHand(final Hand preparedHand) {
        this.hand = preparedHand;
    }

    @Override
    public String toString() {
        return this.hand.toString();
    }

}
