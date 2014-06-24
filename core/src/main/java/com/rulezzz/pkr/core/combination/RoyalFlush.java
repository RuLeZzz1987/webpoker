package com.rulezzz.pkr.core.combination;

import java.util.List;

public class RoyalFlush extends ICombination {

    private static final int ROYAL_FLUSH_HIGHNESS = 10;
    private List<Card> kickers;

    public RoyalFlush(List<Card> cardList) {
        this.kickers = cardList;
    }

    @Override
    public int getHighness() {
        return RoyalFlush.ROYAL_FLUSH_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Royal Flush";
    }

    @Override
    public List<Card> getKickersList() {
        return this.kickers;
    }

}
