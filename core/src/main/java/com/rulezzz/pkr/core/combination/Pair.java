package com.rulezzz.pkr.core.combination;

import java.util.ArrayList;
import java.util.List;

import com.rulezzz.pkr.core.card.Card;

public class Pair extends AbstractCombination {

    private static final int PAIR_HIGHNESS = 2;
    private List<Card> kickers;
    private static final int MULTIPLIER = 1;

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

    @Override
    public int getMultiplier() {
        return MULTIPLIER;
    }
    
    @Override
    public String toString() {
        List<Card> tempToStr = new ArrayList<Card>();
        tempToStr.addAll(kickers);
        tempToStr.remove(0);
        return this.getName() + " of " + kickers.get(0).getRate() + " | " + tempToStr.toString();
    }

}
