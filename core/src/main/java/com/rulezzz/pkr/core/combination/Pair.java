package com.rulezzz.pkr.core.combination;

import java.util.List;

public class Pair extends ICombination {

    private static final int PAIR_HIGHNESS = 2;
    private List<Card> kickers;

    public Pair(List<Card> cardList) {
        this.kickers = cardList;
    }

    @Override
    public int getHighness() {
        return Pair.PAIR_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Pair";
    }

    @Override
    public List<Card> getKickersList() {
        return this.kickers;
    }

}
