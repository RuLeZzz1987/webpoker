package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.engine.Card;

public class FullHouse extends ICombination {
  
	private static final int FULL_HOUSE_HIGHNESS = 7;
	private List<Card> kickers;
	
    public FullHouse(List<Card> cardList){
    	this.kickers = cardList;
    }
    
    @Override
    public int getHighness() {
        return FullHouse.FULL_HOUSE_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Full House";
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
        final FullHouse other = (FullHouse) obj;
        return Objects.equal(this.kickers, other.kickers);
	}	
	
}
