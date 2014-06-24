package com.rulezzz.pkr.core.combination;

import java.util.List;

public class Flush extends ICombination {

    private static final int FLUSH_HIGHNESS = 6;
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

}
