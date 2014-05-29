package com.rulezzz.pkr.core;

public enum CardSuit {

    HEART('\u2665'), CLUBS('\u2663'), DIAMOND('\u2666'), SPADES('\u2660'), ;

    private char str;

    private CardSuit(char str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return String.valueOf(str);
    }
}
