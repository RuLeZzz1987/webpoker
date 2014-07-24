package com.rulezzz.pkr.core.combination;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.rulezzz.pkr.core.card.Card;

public class Straight extends AbstractCombination {

    private static final int STRAIGHT_HIGHNESS = 5;
    private static final int MULTIPLIER = 4;
    private List<Card> kickers = new ArrayList<Card>();
    private static Set<AbstractCombination> allowedAdditCombos = new TreeSet<AbstractCombination>();
    
    static {
        Straight.allowedAdditCombos.add(new Straight());
        Straight.allowedAdditCombos.add(new AceKing());
    }

    public Straight(List<Card> cardList) {
        this.kickers = cardList;
    }

    public Straight() {
    }

    @Override
    public int getHighness() {
        return Straight.STRAIGHT_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Straight";
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
        return this.getName() + " high " + kickers.get(0).getRate();
    }

    @Override
    public Set<AbstractCombination> getAllowedAdditionalCombo() {
        return Straight.allowedAdditCombos;
    }

}
