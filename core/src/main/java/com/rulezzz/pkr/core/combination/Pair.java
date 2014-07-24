package com.rulezzz.pkr.core.combination;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.rulezzz.pkr.core.card.Card;

public class Pair extends AbstractCombination {

    private static final int PAIR_HIGHNESS = 2;
    private List<Card> kickers = new ArrayList<Card>();
    private static final int MULTIPLIER = 1;
    private static Set<AbstractCombination> allowedAdditCombos = new TreeSet<AbstractCombination>();
    
    static {
        Pair.allowedAdditCombos.add(new AceKing());
    }

    public Pair(List<Card> cardList) {
        this.kickers = cardList;
    }

    public Pair() {
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

    @Override
    public Set<AbstractCombination> getAllowedAdditionalCombo() {
        return Pair.allowedAdditCombos;
    }

}
