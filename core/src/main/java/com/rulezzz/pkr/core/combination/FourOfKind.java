package com.rulezzz.pkr.core.combination;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rulezzz.pkr.core.card.Card;

public class FourOfKind extends AbstractCombination {

    private static final int FOUR_OF_A_KIND_HIGHNESS = 8;
    private static final int MULTIPLIER = 20;
    private List<Card> kickers;
    private static Set<AbstractCombination> allowedAdditCombos = new HashSet<AbstractCombination>();
    
    static {
        FourOfKind.allowedAdditCombos.add(new AceKing());
        FourOfKind.allowedAdditCombos.add(new FullHouse());
    }

    public FourOfKind(List<Card> cardList) {
        this.kickers = cardList;
    }

    @Override
    public int getHighness() {
        return FourOfKind.FOUR_OF_A_KIND_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Four of a Kind";
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
        return this.getName() + " of " + kickers.get(0).getRate() + " | " + kickers.get(1);
    }

    @Override
    public Set<AbstractCombination> getAllowedAdditionalCombo() {
        return FourOfKind.allowedAdditCombos;
    }

}
