package com.rulezzz.pkr.core;

public class Card implements Comparable<Card> {

	private CardSuit suit;
    private char rate;
    private int score;

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
    			this.score = 14;
    			break;
    		}
    		case 'K' : {
    			this.score = 13;
    			break;
    		}
    		case 'Q' : { 
    			this.score = 12;
    			break;
    		}
    		case 'J' : { 
    			this.score = 11;
    			break;
    		}
    		case 'T' : { 
    			this.score = 10;
    			break;
    		}
    		default : {
    			this.score = (int) rate - 48;
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
    		throw new IllegalArgumentException();
    	}
    }
    
    public char getRate(){
            return(rate);
    }
    
    public void setRate(int i){
            if ((i>=2)&&(i<=9)) {
                    i+=48;
                    rate=(char) i;
            } else {
                    switch (i) {
                            case 10: {
                                    rate='T';
                                    break;
                            }
                            case 11: {
                                    rate='J';
                                    break;
                            }
                            case 12: {
                                    rate='Q';
                                    break;
                            }
                            case 13: {
                                    rate='K';
                                    break;
                            }
                            case 14: {
                                    rate='A';
                                    break;
                            }
                                    
                    }               
            }
    }
        
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rate;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rate != other.rate)
			return false;
		if (suit != other.suit)
			return false;
		return true;
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
    public int compareTo(Card obj) {
    	if (this.score == obj.score) { 
    		return 0;
    	}
    	if (score > obj.getScore()) {
    		return -1; 
    	}
    			else {
    				return 1;
    			}    				
    }
}
