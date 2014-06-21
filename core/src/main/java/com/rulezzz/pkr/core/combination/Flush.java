package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.Card;

public class Flush extends ICombination {

	private static final int FLUSH_HIGHNESS = 6;
    private List<Card> kickers;
	
    public Flush(List<Card> cardList){
    	this.kickers = cardList;
    }
    
    @Override
    public int getHighness() {
        return Flush.FLUSH_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Flush";
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
        final Flush other = (Flush) obj;
        return Objects.equal(this.kickers, other.kickers);
	}

}
