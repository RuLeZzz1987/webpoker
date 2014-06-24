package com.rulezzz.pkr.core.combination;

import java.util.List;

public class DoesntQualify extends ICombination {

    private static final int DNQ_HIGHNESS = 0;
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

}
