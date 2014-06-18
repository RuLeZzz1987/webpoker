package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.Card;

public class TwoPairs extends ICombination {

	private static final int TWO_PAIRS_HIGHNESS = 4;
    private List<Card> kickers;
	
    public TwoPairs(List<Card> cardList){
    	this.kickers = cardList;
    }
    
    @Override
    public int getHighness() {
        return TwoPairs.TWO_PAIRS_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Two Pairs";
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
        final TwoPairs other = (TwoPairs) obj;
        return Objects.equal(this.kickers, other.kickers);
	}
	

}
