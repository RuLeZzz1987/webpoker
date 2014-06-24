package com.rulezzz.pkr.core.combination;

import java.util.List;

public class FourOfKind extends ICombination {

    private static final int FOUR_OF_A_KIND_HIGHNESS = 8;
    private List<Card> kickers;

    public FourOfKind(List<Card> cardList) {
        this.kickers = cardList;
    }

    @Override
    public int getHighness() {
        return FourOfKind.FOUR_OF_A_KIND_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Four Of a Kind";
    }

    @Override
    public List<Card> getKickersList() {
        return this.kickers;
    }

}
