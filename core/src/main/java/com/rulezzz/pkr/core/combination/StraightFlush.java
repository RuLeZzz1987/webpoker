package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.card.Card;

public class StraightFlush extends AbstractCombination {

    private static final int STRAIGHT_FLUSH_HIGHNESS = 9;
    private static final int MULTIPLIER = 50;
    private List<Card> kickers;

    public StraightFlush(List<Card> cardList) {
        this.kickers = cardList;
    }

    @Override
    public int getHighness() {
        return StraightFlush.STRAIGHT_FLUSH_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Straight Flush";
    }

    @Override
    public List<Card> getKickersList() {
        return this.kickers;
    }

    @Override
    public int getMultiplier() {
        return MULTIPLIER;
    }
    
    @Override
    public String toString() {
        return this.getName() + " Suit " + kickers.get(0).getSuit().getCharSuit() + " high " + kickers.get(0).getRate();
    }

}
