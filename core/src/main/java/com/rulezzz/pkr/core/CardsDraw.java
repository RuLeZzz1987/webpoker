package com.rulezzz.pkr.core;

import java.util.Collections;
import java.util.List;

public class CardsDraw{
	
	private List<Card> knownCards;
	private Hand hand;
	private List<Card> cardsForChange;
	
	public CardsDraw(Hand hand, List<Card> knownCards, List<Card> cardsForChange) {
		this.knownCards = knownCards;
		this.hand = hand;
		this.cardsForChange = cardsForChange;		
	}
	
	public void addCardsForChange(Card card) {
		this.cardsForChange.add(card);
	}
	
	public List<Card> getDrawList(){
		return cardsForChange;
	}
	
	public void sort() {
		Collections.sort(this.cardsForChange);
	}

	public Boolean isSameSet(CardsDraw draw){
		draw.sort();
		sort();
		for (int i=0; i<cardsForChange.size(); i++) {
			if ( draw.getDrawList().get(i) != this.cardsForChange.get(i)) {
				return false;
			}
		}
		return true;
	}
}
