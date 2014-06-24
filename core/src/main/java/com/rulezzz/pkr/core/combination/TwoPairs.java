package com.rulezzz.pkr.core.combination;

import java.util.List;

public class TwoPairs extends ICombination {

    private static final int TWO_PAIRS_HIGHNESS = 3;
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

}
