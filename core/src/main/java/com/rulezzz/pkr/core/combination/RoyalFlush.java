package com.rulezzz.pkr.core.combination;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rulezzz.pkr.core.card.Card;

public class RoyalFlush extends AbstractCombination {

    private static final int ROYAL_FLUSH_HIGHNESS = 10;
    private static final int MULTIPLIER = 100;
    private List<Card> kickers;
    private static Set<AbstractCombination> allowedAdditCombos = new HashSet<AbstractCombination>();
    
    static {
        RoyalFlush.allowedAdditCombos.add(new StraightFlush());
        RoyalFlush.allowedAdditCombos.add(new Flush());
        RoyalFlush.allowedAdditCombos.add(new Straight());
    }

    public RoyalFlush(List<Card> cardList) {
        this.kickers = cardList;
    }

    @Override
    public int getHighness() {
        return RoyalFlush.ROYAL_FLUSH_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Royal Flush";
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
        return this.getName();
    }

    @Override
    public Set<AbstractCombination> getAllowedAdditionalCombo() {
        return RoyalFlush.allowedAdditCombos;
    }

}
