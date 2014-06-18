package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.Card;

public class StraightFlush extends ICombination {

    private static final int STRAIGHT_FLUSH_HIGHNESS = 9;
	private List<Card> kickers;
	
    public StraightFlush(List<Card> cardList){
    	this.kickers = cardList;
    }
    
    @Override
    public int getHighness() {
        return StraightFlush.STRAIGHT_FLUSH_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Straight Flush";
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
        final StraightFlush other = (StraightFlush) obj;
        return Objects.equal(this.kickers, other.kickers);
	}
	
}
