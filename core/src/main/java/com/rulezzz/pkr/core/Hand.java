package com.rulezzz.pkr.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand{
	
	public static final int OMAHA = 4;
	public static final int FIVECARD = 5;
	public static final int TEXAS = 2;
	private static final int PAIR = 1;
	private static final int SET = 2;
	private static final int NOCONSILIENCE = 0;
	private static final int SQUARE = 3;
	private static final int DELTAFLC = 4; // delta between score of first and last card. if it's equally 4 combination is Straight  
	private static final int DELTAFLCWH = 9; // delta between score of first and second card. if it's equally 9 combination is Wheel Straight 
	private List<Card> hand = new ArrayList<Card>();
	private GameType gameType;
	
	public Hand(GameType gameType, Card... card) {
		this.gameType = gameType;
		for (int i=0; i<card.length; i++) {
			hand.add(card[i]);
		}
		switch(this.gameType) {
		case FIVECARD : {
			if ( hand.size() != FIVECARD ) {
				throw new IllegalArgumentException("Illegal cards count for this type of game. Correct number is 5!");
			}
			break;
		}
		case OMAHA : {
			if ( hand.size() != OMAHA ) {
				throw new IllegalArgumentException("Illegal cards count for this type of game. Correct number is 4!");
			}
			break;
		}
		case TEXAS : {
			if ( hand.size() != TEXAS ) {
				throw new IllegalArgumentException("Illegal cards count for this type of game. Correct number is 2!");
			}
			break;
		}
		default :
			break;
		}		
	}
	
	public Hand() {
		// TODO Auto-generated constructor stub
	}

	public void add(Card card) {
		hand.add(card);
	}
	
	public void removeCardByMask(int... mask) {
		for (int i = mask.length - 1; i >= 0; i--) {
			if ( mask[i] == 1 ) {
				hand.remove(i);
			}
		}
	}
	
	public List<Card> getCards() {
		return hand;
	}
	
	public void sort() {
		Collections.sort(hand);
	}
	
	protected Boolean isFlushOnFiveCards(){
		for (int i = 1; i < hand.size(); i++) {
			if ( hand.get(0).getSuit() != hand.get(i).getSuit() ) {
				return false;
			}
		}
		return true;
	}
	
	public Combination getCombinationOnFiveCards() {
		if ( hand.size() != FIVECARD ) {
			throw new IllegalStateException("Cards count in hand != 5");
		}
		int pairPosition = 0;
		int consilience = 0;
		int firstPairPosition = 0;
		int consilience1 = 0;
		StringBuilder resultBuffer;
		int flagFirstPairFound = 0;
		for (int i = 1; i < hand.size(); i++) {
			if ( hand.get(pairPosition).getScore() == hand.get(i).getScore() ) {
				if ( flagFirstPairFound <= 1) {
					consilience++;
				}
			} else {
				if ( consilience == 0 ) {
					pairPosition = i;
				} else {
					if ( flagFirstPairFound == 0 ) {
						firstPairPosition = pairPosition;
						pairPosition = i;
						consilience1 = consilience;
						consilience = 0;
						flagFirstPairFound = 1;
					}
				}
			}
		}
		if ( consilience == 0 && consilience1 != 0) {
			consilience = consilience1;
			consilience1 = 0;
			pairPosition = firstPairPosition;
			firstPairPosition = 0;
		}
		switch ( consilience ) {
			case NOCONSILIENCE: {
				if ( !isFlushOnFiveCards() ) {
					if ( hand.get(1).getScore() == Card.KINGSCORE && hand.get(0).getScore() - hand.get(hand.size()-1).getScore() != DELTAFLC ) {
						return new Combination("1" + " " + hand.get(hand.size()-3).getScore() + " " + hand.get(hand.size()-2).getScore() + " " + hand.get(hand.size()-1).getScore() + " ");
					}
					if ( hand.get(0).getScore() - hand.get(hand.size()-1).getScore() == DELTAFLC ) {
						return new Combination("5" + " " + hand.get(0).getScore() + " ");
					}
					if ( hand.get(0).getScore() - hand.get(1).getScore() == DELTAFLCWH ) {
						return new Combination("5" + " " + hand.get(1).getScore() + " ");
					}
					return new Combination("0 ");
				} else {
					if ( hand.get(0).getScore() - hand.get(hand.size()-1).getScore() != DELTAFLC && hand.get(0).getScore() - hand.get(1).getScore() != DELTAFLCWH ) {
						return new Combination("6" + " " + hand.get(0).getScore() + " " + hand.get(hand.size()-4).getScore() + " " + hand.get(hand.size()-3).getScore() + " " + hand.get(hand.size()-2).getScore() + " " + hand.get(hand.size()-1).getScore() + " ");
					}
					if ( hand.get(0).getScore() - hand.get(hand.size()-1).getScore() == DELTAFLC && hand.get(0).getScore() != Card.ACESCORE) {
						return new Combination("9" + " " + hand.get(0).getScore() + " ");
					}
					if ( hand.get(0).getScore() - hand.get(1).getScore() == DELTAFLCWH ) {
						return new Combination("9" + " " + hand.get(1).getScore() + " ");
					}
					if ( hand.get(1).getScore() == Card.KINGSCORE && hand.get(0).getScore() - hand.get(hand.size()-1).getScore() == DELTAFLC) {
						return new Combination("10 ");
					}					
				}
			}
			case PAIR: { 
				if ( consilience1 == 0 ) {
					resultBuffer = new StringBuilder("2" + " " + hand.get(pairPosition).getScore() + " " + hand.get(pairPosition).getScore() + " ");
					for (int i = 0; i < hand.size(); i++) {
						if ( hand.get(pairPosition).getScore() != hand.get(i).getScore() ) {
							resultBuffer.append(hand.get(i).getScore() + " ");
						}
					}
					return new Combination(resultBuffer.toString());
				}
				if ( consilience1 == 1 ) {
					resultBuffer = new StringBuilder("3" + " " + hand.get(firstPairPosition).getScore() + " " + hand.get(firstPairPosition).getScore() + " " + hand.get(pairPosition).getScore() + " " + hand.get(pairPosition).getScore() + " ");
					for (int i = 0; i < hand.size(); i++) {
						if ( hand.get(pairPosition).getScore() != hand.get(i).getScore() && hand.get(firstPairPosition).getScore() != hand.get(i).getScore() ) {
							resultBuffer.append(hand.get(i).getScore() + " ");
						}
					}
					return new Combination(resultBuffer.toString());
				}
				if ( consilience1 == 2 ) {
					return new Combination("7" + " " + hand.get(firstPairPosition).getScore() + " " + hand.get(pairPosition).getScore() + " ") ;
				}
			}
			case SET: {
				if ( consilience1 == 0 ) {
					resultBuffer = new StringBuilder("4" + " " + hand.get(pairPosition).getScore() + " " + hand.get(pairPosition).getScore() + " " + hand.get(pairPosition).getScore() + " ");
					for (int i = 0; i < hand.size(); i++) {
						if ( hand.get(pairPosition).getScore() != hand.get(i).getScore() ) {
							resultBuffer.append(hand.get(i).getScore() + " ");
						}
					}
					return new Combination(resultBuffer.toString());
				} else {
					return new Combination("7" + " " + hand.get(pairPosition).getScore() + " " + hand.get(firstPairPosition).getScore() + " ");
				}
			}
			case SQUARE: {
				resultBuffer = new StringBuilder("8" + " " + hand.get(pairPosition).getScore() + " ");
				for (int i = 0; i < hand.size(); i++) {
					if ( hand.get(pairPosition).getScore() != hand.get(i).getScore() ) {
						resultBuffer.append(hand.get(i).getScore() + " ");
					}
				}
				return new Combination(resultBuffer.toString());
			}
			default : {
				 throw new IllegalStateException("Unknown combination");
			}
		}
	}
	
	@Override
	public String toString() {
		return hand.toString();
	}

	
	public Boolean compareTo(Hand h) {
		sort();
		h.sort();
		if ( hand.size() != h.getCards().size() ) { 
			return false; 
		} else {
					for (int i=0; i < hand.size(); i++) {
						if ( !hand.get(i).equals(h.getCards().get(i))) {
							return false;	
						}
					}
				}
		return true;
	}
}
