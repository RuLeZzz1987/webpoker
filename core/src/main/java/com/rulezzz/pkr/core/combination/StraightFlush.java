package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.engine.Card;

public class StraightFlush extends ICombination {

    private static final int STRAIGHT_FLUSH_HIGHNESS = 9;
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

}
