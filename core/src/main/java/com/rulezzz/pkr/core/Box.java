package com.rulezzz.pkr.core;

import java.util.List;

public abstract class Box {

    private Hand hand = new Hand();

    public Hand getHand() {
        return hand;
    }

    public void sort() {
        hand.sort();
    }

    public void setHand(Card card) {
        this.hand.add(card);
    }
    
    public void setHand(List<Card> cards){
        hand.add(cards);
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return hand.toString();
    }

}
