package com.rulezzz.pkr.core;

public class Combination implements Comparable<Combination> {
	
	private String code;
	private String name;
	private int highness;
	private int[] kickers = new int[6]; 
	
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
	
	private final void setKickersList(String code) {
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
	
	private final void setName(String code) {
		switch (code.charAt(0)) {
			case '0' : {
				this.name = "Don't qualify";
				this.highness = 0;
				break;
			}
			case '1' : {
				if ( code.charAt(1) != '0' ) {
					this.name = "Ace & King";
					this.highness = 1;
				} else {
					this.name = "Royal flush";
					this.highness = 10;
				}
				break;
			}
			case '2' : {
				this.name = "Pair";
				this.highness = 2;
				break;
			}
			case '3' : {
				this.name = "Two pairs";
				this.highness = 3;
				break;
			}
			case '4' : {
				this.name = "Three of a kind";
				this.highness = 4;
				break;
			}
			case '5' : {
				this.name = "Straight";
				this.highness = 5;
				break;
			}
			case '6' : {
				this.name = "Flush";
				this.highness = 6;
				break;
			}
			case '7' : {
				this.name = "Full house";
				this.highness = 7;
				break;
			}
			case '8' : {
				this.name = "Four of a kind";
				this.highness = 8;
				break;
			}
			case '9' : {
				this.name = "Straight flush";
				this.highness = 9;
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
			if (other.code != null)
				return false;
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
