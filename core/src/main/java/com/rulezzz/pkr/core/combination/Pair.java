package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.Card;

public class Pair implements ICombination, Comparable<ICombination> {
    
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

    @Override
    public int compareTo(final ICombination o) {
        if ( this.getHighness() > o.getHighness() ) {
            return 1;
        }
        if ( this.getHighness() < o.getHighness() ) {
            return -1;
        }
        if ( this.equals(o) )  {
            return 0;
        } else {
            for (int i = 0; i < this.kickers.size(); i++) {
                switch(this.kickers.get(i).compareTo(o.getKickersList().get(i))) {
                    case -1 : {
                        return -1;
                    }
                    case 1 : {
                        return 1;
                    }
                    default : {
                        break;
                    }
                }
                
            }
        }
        return 0;
    }

}
