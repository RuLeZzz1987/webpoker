package com.rulezzz.pkr.core.combination;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.rulezzz.pkr.core.card.Card;

public class Flush extends AbstractCombination {

    private static final int FLUSH_HIGHNESS = 6;
    private static final int MULTIPLIER = 5;
    private List<Card> kickers = new ArrayList<Card>();
    private static Set<AbstractCombination> allowedAdditCombos = new TreeSet<AbstractCombination>();

    static { 
        Flush.allowedAdditCombos.add(new AceKing());
        Flush.allowedAdditCombos.add(new Pair());
        Flush.allowedAdditCombos.add(new Straight());
        Flush.allowedAdditCombos.add(new Flush());
    }
    
    
    public Flush(List<Card> cardList) {
        this.kickers = cardList;
    }
    
    public Flush() {
    }

    @Override
    public int getHighness() {
        return Flush.FLUSH_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Flush";
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
        return this.getName() + " Suit " + kickers.get(0).getSuit().getCharSuit() + " | " + kickers;
    }

    @Override
    public Set<AbstractCombination> getAllowedAdditionalCombo() {
        return Flush.allowedAdditCombos;
    }

}
