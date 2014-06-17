package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.Card;

public class FullHouse implements ICombination {
  
	private static final int FULL_HOUSE_HIGHNESS = 7;
	private List<Card> kickers;
	
    public FullHouse(List<Card> cardList){
    	this.kickers = cardList;
    }
    
    @Override
    public int getHighness() {
        return FullHouse.FULL_HOUSE_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Full House";
    }

    @Override
    public List<Card> getKickersList() {
        return this.kickers;
    }
}
