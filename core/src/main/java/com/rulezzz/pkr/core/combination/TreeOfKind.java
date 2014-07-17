package com.rulezzz.pkr.core.combination;

import java.util.ArrayList;
import java.util.List;

import com.rulezzz.pkr.core.card.Card;

public class TreeOfKind extends AbstractCombination {

    private static final int TREE_OF_A_KIND_HIGHNESS = 4;
    private static final int MULTIPLIER = 3;
    private List<Card> kickers;

    public TreeOfKind(List<Card> cardList) {
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
