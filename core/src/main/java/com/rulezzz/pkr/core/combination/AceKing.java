package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.Card;

public class AceKing implements ICombination{

    private List<Card> kickers;
	
    public AceKing(List<Card> cardList){
    	this.kickers = cardList;
    }
    
    @Override
    public int getHighness() {
        return 1;
    }

    @Override
    public String getName() {
        return "Ace & King";
    }

    @Override
    public List<Card> getKickersList() {
        return this.kickers;
    }

}
