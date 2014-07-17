package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.card.Card;

public class Flush extends AbstractCombination {

    private static final int FLUSH_HIGHNESS = 6;
    private static final int MULTIPLIER = 5;
    private List<Card> kickers;

    public Flush(List<Card> cardList) {
        this.kickers = cardList;
    }

    @Override
    public int getHighness() {
        return Flush.FLUSH_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Flush";
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
        return this.getName() + " Suit " + kickers.get(0).getSuit().getCharSuit() + " | " + kickers;
    }

}
