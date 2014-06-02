package com.rulezzz.pkr.core;

public abstract class Box {

	private Hand hand = new Hand();

	public Hand getHand() {
		return hand;
	}

	public void sort() {
		hand.sort();
	}

	public void setHand(Card card) {
		this.hand.add(card);
	}

	public int getPower() {
		return Integer.parseInt(hand.getCombinationOnFiveCards().getCombCode());
	}

	@Override
	public String toString() {
		return hand.toString();
	}

}
