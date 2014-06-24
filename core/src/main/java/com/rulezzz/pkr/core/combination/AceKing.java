package com.rulezzz.pkr.core.combination;

import java.util.List;

public class AceKing extends ICombination {

	private static final int ACE_KING_HIGHNESS = 1;
    private List<Card> kickers;
	
    public AceKing(List<Card> cardList){
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

}
