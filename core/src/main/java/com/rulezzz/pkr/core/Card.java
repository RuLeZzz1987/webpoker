package com.rulezzz.pkr.core;

public class Card implements Comparable<Card> {
    private CardSuit suit;
    private char rate;
    private int score;
    private final static int deltaUtfChar = 48; /** needed characters starts from 48+ position in UTF-8 **/
    private final static int AceScore = 14;
    private final static int KingScore = 13;
    private final static int QueenScore = 12;
    private final static int JackScore = 11;
    private final static int TenScore = 10;
    

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
    			this.score = AceScore;
    			break;
    		}
    		case 'K' : {
    			this.score = KingScore;
    			break;
    		}
    		case 'Q' : { 
    			this.score = QueenScore;
    			break;
    		}
    		case 'J' : { 
    			this.score = JackScore;
    			break;
    		}
    		case 'T' : { 
    			this.score = TenScore;
    			break;
    		}
    		default : {
    			this.score = (int) rate - deltaUtfChar;
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
    
    private final void setRate(int i){
            if ((i>=2)&&(i<=9)) {
                    i+=deltaUtfChar;
                    rate=(char) i;
            } else {
                    switch (i) {
                            case TenScore: {
                                    rate='T';
                                    break;
                            }
                            case JackScore: {
                                    rate='J';
                                    break;
                            }
                            case QueenScore: {
                                    rate='Q';
                                    break;
                            }
                            case KingScore: {
                                    rate='K';
                                    break;
                            }
                            case AceScore: {
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
