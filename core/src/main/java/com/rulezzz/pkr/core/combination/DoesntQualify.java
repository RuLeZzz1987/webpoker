package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.engine.Card;

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

    @Override
    public int hashCode() {
        return Objects.hashCode(this.kickers, this.getHighness());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }

}
