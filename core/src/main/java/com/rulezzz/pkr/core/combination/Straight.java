package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.engine.Card;

public class Straight extends ICombination {

    private static final int STRAIGHT_HIGHNESS = 5;
    private List<Card> kickers;

    public Straight(List<Card> cardList) {
        this.kickers = cardList;
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
        final Straight other = (Straight) obj;
        return Objects.equal(this.kickers, other.kickers);
    }

}
