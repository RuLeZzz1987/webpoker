package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.Card;

public class Pair implements ICombination {
    
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
