package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.engine.Card;

public class FourOfKind extends ICombination {

	private static final int FOUR_OF_A_KIND_HIGHNESS = 8;
    private List<Card> kickers;
	
    public FourOfKind(List<Card> cardList){
    	this.kickers = cardList;
    }
    
    @Override
    public int getHighness() {
        return FourOfKind.FOUR_OF_A_KIND_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Four Of a Kind";
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
        final FourOfKind other = (FourOfKind) obj;
        return Objects.equal(this.kickers, other.kickers);
	}
	
}
