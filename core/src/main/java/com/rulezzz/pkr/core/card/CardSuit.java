package com.rulezzz.pkr.core.card;

public enum CardSuit {

    HEART('\u2665', 'h'), CLUBS('\u2663', 'c'), DIAMOND('\u2666', 'd'), SPADES('\u2660', 's');

    private char str;
    private char cSuit;

    private CardSuit(char str, char charSuit) {
        this.str = str;
        this.cSuit = charSuit;
    }

    @Override
    public String toString() {
        return String.valueOf(this.str);
    }
    
    public char getCharSuit() {
        return this.cSuit;
    }
}
