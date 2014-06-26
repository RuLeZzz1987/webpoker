package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.basestructures.Card;

public class Straight extends ICombination {

    private static final int STRAIGHT_HIGHNESS = 5;
    private static final int MULTIPLIER = 4;
    private List<Card> kickers;

    public Straight(List<Card> cardList) {
        this.kickers = cardList;
    }

    @Override
    public int getHighness() {
        return Straight.STRAIGHT_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Straight";
    }

    @Override
    public List<Card> getKickersList() {
        return this.kickers;
    }

    @Override
    public int getMultiplier() {
        return MULTIPLIER;
    }

}
