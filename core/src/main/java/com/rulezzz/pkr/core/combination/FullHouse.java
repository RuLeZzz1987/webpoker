package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.card.Card;

public class FullHouse extends AbstractCombination {

    private static final int FULL_HOUSE_HIGHNESS = 7;
    private static final int MULTIPLIER = 7;
    private List<Card> kickers;

    public FullHouse(List<Card> cardList) {
        this.kickers = cardList;
    }

    @Override
    public int getHighness() {
        return FullHouse.FULL_HOUSE_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Full House";
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
        return this.getName() + " of " + kickers.get(0).getRate() + " over " + kickers.get(1).getRate();
    }

}
