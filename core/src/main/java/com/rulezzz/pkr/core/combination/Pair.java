package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.engine.Card;

public class Pair extends ICombination {
    
	private static final int PAIR_HIGHNESS = 2;
    private List<Card> kickers;

    public Pair(List<Card> cardList) {
    	this.kickers = cardList;
    }
    
    @Override
    public int getHighness() {
        return Pair.PAIR_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Pair";
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
        final Pair other = (Pair) obj;
        return Objects.equal(this.kickers, other.kickers);
    }



}
