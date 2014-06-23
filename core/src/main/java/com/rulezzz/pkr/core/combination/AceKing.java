package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.engine.Card;

public class AceKing extends ICombination {

	private static final int ACE_KING_HIGHNESS = 1;
    private List<Card> kickers;
	
    public AceKing(List<Card> cardList){
        this.kickers = cardList;
    }
    
    @Override
    public int getHighness() {
        return AceKing.ACE_KING_HIGHNESS;
    }

    @Override
    public String getName() {
        return "Ace & King";
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
        final AceKing other = (AceKing) obj;
        return Objects.equal(this.kickers, other.kickers);
	}

	@Override
    public List<Card> getKickersList() {
        return this.kickers;
    }

}
