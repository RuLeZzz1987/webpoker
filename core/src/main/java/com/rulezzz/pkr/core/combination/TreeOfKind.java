package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.Card;

public class TreeOfKind implements ICombination{

	private static final int TREE_OF_A_KIND_HIGHNESS = 9;
    private List<Card> kickers;
	
    public TreeOfKind(List<Card> cardList){
    	this.kickers = cardList;
    }
    
    @Override
    public int getHighness() {
        return TreeOfKind.TREE_OF_A_KIND_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Tree of a Kind";
    }

    @Override
    public List<Card> getKickersList() {
        return this.kickers;
    }
}
