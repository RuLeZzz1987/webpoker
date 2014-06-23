package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.engine.Card;

public abstract class ICombination implements Comparable<ICombination> {

    @Override
    public int compareTo(final ICombination combo) {
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

    public abstract int getHighness();

    public abstract String getName();

    public abstract List<Card> getKickersList();
}
