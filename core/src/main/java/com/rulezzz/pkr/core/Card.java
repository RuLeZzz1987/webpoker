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
    
    public Boolean isSame(Card card) {
    	return (this.suit == card.suit && this.score == card.score) ? true : false; 
    }

    @Override
    public int compareTo(Card obj) {
    	if (score == obj.getScore()) return 0;
    	if (score > obj.getScore()) return -1; 
    			else
    				return 1;
    }
}
