package com.rulezzz.pkr.core;

import java.util.ArrayList;
import java.util.List;

public class ConsilienceCounter {
	private int pairPosition = 0;
	private int consilience = 0;
	private int firstPairPosition = 0;
	private int consilience1 = 0;
	private int flagFirstPairFound = 0;
	private List<Card> hand = new ArrayList<Card>();

	public ConsilienceCounter(List<Card> hand) {
		if (hand.size() != Hand.FIVECARD) {
			throw new IllegalStateException("Cards count in hand != 5");
		}
		this.hand = hand;
		count();
	}

	private void count() {
		for (int i = 1; i < hand.size(); i++) {
			if (hand.get(pairPosition).getScore() == hand.get(i).getScore()) {
				if (flagFirstPairFound <= 1) {
					consilience++;
				}
			} else {
				if (consilience == 0) {
					pairPosition = i;
				} else {
					if (flagFirstPairFound == 0) {
						firstPairPosition = pairPosition;
						pairPosition = i;
						consilience1 = consilience;
						consilience = 0;
						flagFirstPairFound = 1;
					}
				}
			}
		}
		if (consilience == 0 && consilience1 != 0) {
			consilience = consilience1;
			consilience1 = 0;
			pairPosition = firstPairPosition;
			firstPairPosition = 0;
		}
	}

	public int getPairPosition() {
		return pairPosition;
	}

	public int getConsilience() {
		return consilience;
	}

	public int getFirstPairPosition() {
		return firstPairPosition;
	}

	public int getConsilience1() {
		return consilience1;
	}

	public int getFlagFirstPairFound() {
		return flagFirstPairFound;
	}

}
