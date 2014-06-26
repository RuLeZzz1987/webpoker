package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.basestructures.Card;

public class AceKing extends ICombination {

    private static final int ACE_KING_HIGHNESS = 1;
    private static final int MULTIPLIER = 1;
    private List<Card> kickers;


    public AceKing(List<Card> cardList) {
        this.kickers = cardList;
    }

    @Override
    public int getHighness() {
        return AceKing.ACE_KING_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Ace & King";
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
