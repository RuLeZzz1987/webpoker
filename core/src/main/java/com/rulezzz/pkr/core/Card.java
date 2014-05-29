package com.rulezzz.pkr.core;

public class Card implements Comparable<Card> {
    private CardSuit suit;
    private char rate;
    private int score;
    private static final int DELTAUTFCHAR = 48;
    public static final int ACESCORE = 14;
    public static final int KINGSCORE = 13;
    public static final int QUEENSCORE = 12;
    public static final int JACKSCORE = 11;
    public static final int TENSCORE = 10;
    public static final int MAXNONCHARRANK = 9;
    

    public Card(CardSuit suit, int rate, int score) {
    	this.score = score;
    	this.suit = suit;
    	setRate(rate);
    }
    
    public Card(CardSuit suit, char rate, int score) {
    	this.score = score;
    	this.suit = suit;
    	this.rate = rate;
    }
    
    public Card(CardSuit suit, char rate) {
    	this.suit = suit;
    	this.rate = rate;
    	switch (rate) {
    		case 'A' : { 
    			this.score = ACESCORE;
    			break;
    		}
    		case 'K' : {
    			this.score = KINGSCORE;
    			break;
    		}
    		case 'Q' : { 
    			this.score = QUEENSCORE;
    			break;
    		}
    		case 'J' : { 
    			this.score = JACKSCORE;
    			break;
    		}
    		case 'T' : { 
    			this.score = TENSCORE;
    			break;
    		}
    		default : {
    			this.score = (int) rate - DELTAUTFCHAR;
    		}
    	}
    }
    
    public CardSuit getSuit(){
            return(suit);           
    }
    
    public char getCharSuit(){
    	switch(suit) {
    	case HEART : {
    		return 'h';
    	}
    	case SPADES : {
    		return 's';
    	}
    	case DIAMOND : {
    		return 'd';
    	}
    	case CLUBS : {
    		return 'c';
    	}
    	default :
    		throw new IllegalArgumentException("unknown card suit. It should be h, s, d or c");
    	}
    }
    
    public char getRate(){
            return(rate);
    }
    
    private void setRate(int i){
            if ( ( i >= 2 ) && ( i <= MAXNONCHARRANK ) ) {
                    i+=DELTAUTFCHAR;
                    rate=(char) i;
            } else {
                    switch (i) {
                            case TENSCORE: {
                                    rate='T';
                                    break;
                            }
                            case JACKSCORE: {
                                    rate='J';
                                    break;
                            }
                            case QUEENSCORE: {
                                    rate='Q';
                                    break;
                            }
                            case KINGSCORE: {
                                    rate='K';
                                    break;
                            }
                            case ACESCORE: {
                                    rate='A';
                                    break;
                            }
                            default : {
                            	throw new IllegalArgumentException("Unknow card rate. It should be in range 2-9, T, J, Q, K or A");
                            }
                                    
                    }               
            }
    }

    public String getStringCard() {
    	return getCharSuit()+String.valueOf(rate);
    }
    
    @Override
    public String toString() {
      return String.valueOf(rate)+suit;
    }
    
    public int getScore() {
            return score;
            
    }
       
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + score;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Card other = (Card) obj;
		if (score != other.score) {
			return false;
		}
		if (suit != other.suit) {
			return false;
		}
		return true;
	}

	@Override
    public int compareTo(Card obj) {
    	if (score == obj.score) { 
    		return 0; 
    	}
    	if (score > obj.getScore()) { 
    		return -1; 
    	}
		return 1;
    }
}
