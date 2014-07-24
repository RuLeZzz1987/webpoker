package com.rulezzz.pkr.core.combination;

import java.util.List;
import java.util.Set;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.card.Card;

public abstract class AbstractCombination implements Comparable<AbstractCombination> {

    @Override
    public int compareTo(final AbstractCombination combo) {
        if (this.getHighness() > combo.getHighness()) {
            return 1;
        }
        if (this.getHighness() < combo.getHighness()) {
            return -1;
        }
        if (this.equals(combo)) {
            return 0;
        } else {
            for (int i = 0; i < this.getKickersList().size(); i++) {
                Card cardThis = this.getKickersList().get(i);
                Card cardOther = combo.getKickersList().get(i);
                int comparRes = cardThis.compareTo(cardOther);
                if (comparRes != 0) {
                    return comparRes;
                }
            }
        }
        return 0;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.getKickersList(), this.getHighness());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractCombination other = (AbstractCombination) obj;
        if (this.getKickersList() != null) {
            if (other.getKickersList() != null)
            for (int i = 0; i < this.getKickersList().size(); i++) {
                if (!this.getKickersList().get(i).equalsByRate(other.getKickersList().get(i))) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (other.getKickersList() == null) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
    
    public abstract int getHighness();

    public abstract String getName();

    public abstract List<Card> getKickersList();

    public abstract int getMultiplier();
    
    public abstract Set<AbstractCombination> getAllowedAdditionalCombo();
}
