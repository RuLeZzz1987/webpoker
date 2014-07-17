package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.card.Card;

public class TwoPairs extends AbstractCombination {

    private static final int TWO_PAIRS_HIGHNESS = 3;
    private static final int MULTIPLIER = 2;
    private List<Card> kickers;

    public TwoPairs(List<Card> cardList) {
        this.kickers = cardList;
    }

    @Override
    public int getHighness() {
        return TwoPairs.TWO_PAIRS_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Two Pairs";
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
        return this.getName() + " of " + kickers.get(0).getRate() + " over " + kickers.get(1).getRate() + " | "
                + kickers.get(2);
    }
}
