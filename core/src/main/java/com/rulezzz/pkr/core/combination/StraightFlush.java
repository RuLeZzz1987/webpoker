package com.rulezzz.pkr.core.combination;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rulezzz.pkr.core.card.Card;

public class StraightFlush extends AbstractCombination {

    private static final int STRAIGHT_FLUSH_HIGHNESS = 9;
    private static final int MULTIPLIER = 50;
    private List<Card> kickers;
    private static Set<AbstractCombination> allowedAdditCombos = new HashSet<AbstractCombination>();
    
    static {
        StraightFlush.allowedAdditCombos.add(new StraightFlush());
        StraightFlush.allowedAdditCombos.add(new Flush());
        StraightFlush.allowedAdditCombos.add(new Straight());
        StraightFlush.allowedAdditCombos.add(new AceKing());
    }

    public StraightFlush(List<Card> cardList) {
        this.kickers = cardList;
    }

    public StraightFlush() {
    }

    @Override
    public int getHighness() {
        return StraightFlush.STRAIGHT_FLUSH_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Straight Flush";
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
        return this.getName() + " Suit " + kickers.get(0).getSuit().getCharSuit() + " high " + kickers.get(0).getRate();
    }

    @Override
    public Set<AbstractCombination> getAllowedAdditionalCombo() {
        return StraightFlush.allowedAdditCombos;
    }

}
