package com.rulezzz.pkr.core.combination;

import java.util.List;
import java.util.Set;

import com.rulezzz.pkr.core.card.Card;

public class DoesntQualify extends AbstractCombination {

    private static final int DNQ_HIGHNESS = 0;
    private static final int MULTIPLIER = 0;
    private List<Card> kickers;

    public DoesntQualify(List<Card> cardHand) {
        this.kickers = cardHand;
    }

    @Override
    public int getHighness() {
        return DoesntQualify.DNQ_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Doesn't qualifier";
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
        return "Hand " + this.getName();
    }

    @Override
    public Set<AbstractCombination> getAllowedAdditionalCombo() {
        return null;
    }
}
