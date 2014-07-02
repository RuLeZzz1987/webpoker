package com.rulezzz.pkr.core.card;

public enum CardSuit {

    HEART('\u2665', 'h'), CLUBS('\u2663', 'c'), DIAMOND('\u2666', 'd'), SPADES('\u2660', 's');

    private char icon;
    private char cSuit;

    private CardSuit(char icon, char charSuit) {
        this.icon = icon;
        this.cSuit = charSuit;
    }

    @Override
    public String toString() {
        return String.valueOf(this.icon);
    }
    
    public char getCharSuit() {
        return this.cSuit;
    }
}
