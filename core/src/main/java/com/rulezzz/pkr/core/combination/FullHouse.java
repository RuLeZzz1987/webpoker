package com.rulezzz.pkr.core.combination;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.rulezzz.pkr.core.card.Card;

public class FullHouse extends AbstractCombination {

    private static final int FULL_HOUSE_HIGHNESS = 7;
    private static final int MULTIPLIER = 7;
    private List<Card> kickers;
    private static Set<AbstractCombination> allowedAdditCombos = new HashSet<AbstractCombination>();
    
    static {
        FullHouse.allowedAdditCombos.add(new FullHouse());
        FullHouse.allowedAdditCombos.add(new AceKing());
    }

    public FullHouse(List<Card> cardList) {
        this.kickers = cardList;
    }

    public FullHouse() {
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

    @Override
    public int getMultiplier() {
        return MULTIPLIER;
    }
    
    @Override
    public String toString() {
        return this.getName() + " of " + kickers.get(0).getRate() + " over " + kickers.get(1).getRate();
    }

    @Override
    public Set<AbstractCombination> getAllowedAdditionalCombo() {
        return FullHouse.allowedAdditCombos;
    }

}
