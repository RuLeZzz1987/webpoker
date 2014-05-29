package com.rulezzz.pkr.core;

public class Combination implements Comparable<Combination> {
	
	private String code;
	private String name;
	private int highness;
	private static final int MAXCARDSCOUNT = 6;
	private int[] kickers = new int[MAXCARDSCOUNT];
	private static final int ACEKINGHIGH = 1;
	private static final int DNQ = 0;
	private static final int ROYALFLUSH = 10;
	private static final int PAIR = 2;
	private static final int TWOPAIRS = 3;
	private static final int SET = 4;
	private static final int STRAIGHT = 5;
	private static final int FLUSH = 6;
	private static final int FULLHOUSE = 7;
	private static final int KARE = 8;
	private static final int STRAIGHTFLUSH = 9;
	
	public Combination(String code) {
		this.code = code;
		setName(code);
		setKickersList(code);
	}
	
	public int getHighness() {
		return this.highness;
	}

	public String getCombCode() {
		return code;
	}
	
	private void setKickersList(String code) {
		int i = 0;
		code = code + " ";
		while ( code.compareTo(" ") != 0 ) {
			String bufstr = code.substring(0, code.indexOf(' '));
			kickers[i] = Integer.parseInt(bufstr);
			code = code.substring(code.indexOf(' ')+1);
			i++;
		}
		
	}
	
	public int[] getKickers() {
		return  kickers;
	}
	
	
    @Override
    public String toString() {
      return name;
    }
	
	private void setName(String code) {
		switch (code.charAt(0)) {
			case '0' : {
				this.name = "Don't qualify";
				this.highness = DNQ;
				break;
			}
			case '1' : {
				if ( code.charAt(1) != '0' ) {
					this.name = "Ace & King";
					this.highness = ACEKINGHIGH;
				} else {
					this.name = "Royal flush";
					this.highness = ROYALFLUSH;
				}
				break;
			}
			case '2' : {
				this.name = "Pair";
				this.highness = PAIR;
				break;
			}
			case '3' : {
				this.name = "Two pairs";
				this.highness = TWOPAIRS;
				break;
			}
			case '4' : {
				this.name = "Three of a kind";
				this.highness = SET;
				break;
			}
			case '5' : {
				this.name = "Straight";
				this.highness = STRAIGHT;
				break;
			}
			case '6' : {
				this.name = "Flush";
				this.highness = FLUSH;
				break;
			}
			case '7' : {
				this.name = "Full house";
				this.highness = FULLHOUSE;
				break;
			}
			case '8' : {
				this.name = "Four of a kind";
				this.highness = KARE;
				break;
			}
			case '9' : {
				this.name = "Straight flush";
				this.highness = STRAIGHTFLUSH;
				break;
			}
			default : {
				throw new IllegalArgumentException("Unknown combination code. It should be in range 0-10");
			}
		}
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Combination other = (Combination) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else { 
			if (!code.equals(other.code)) {
				return false; 
			} 
		}
		return true;
	}

	@Override
	public int compareTo(Combination comb) {
		for (int i = 0; i < kickers.length; i++) {
			if ( comb.getKickers()[i] > kickers[i] ) { 
				return -1;
			}
			if ( comb.getKickers()[i] < kickers[i] ) { 
				return 1;
			}
		}
		return 0;
	}
}
