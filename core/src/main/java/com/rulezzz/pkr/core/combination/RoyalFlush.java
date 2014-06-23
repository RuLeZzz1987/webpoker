package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.engine.Card;

public class RoyalFlush extends ICombination {
	
	private static final int ROYAL_FLUSH_HIGHNESS = 10;
    private List<Card> kickers;
	
    public RoyalFlush(List<Card> cardList){
    	this.kickers = cardList;
    }
    
    @Override
    public int getHighness() {
        return RoyalFlush.ROYAL_FLUSH_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Royal Flush";
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
