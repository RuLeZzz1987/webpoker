package com.rulezzz.pkr.core.card;

import java.util.HashMap;

import com.google.common.base.Objects;

public class Card implements Comparable<Card> {

    public static final int ACE_SCORE = 14;
    public static final int KING_SCORE = 13;
    public static final int QUEEN_SCORE = 12;
    public static final int JACK_SCORE = 11;
    public static final int TEN_SCORE = 10;
    private static HashMap<Character, Integer> parseCharRateToInt;

    private static final int MAX_NONCHAR_RANK = 9;

    static final int DELTAUT_FCHAR = 48;

    private CardSuit suit;
    private char rate;
    private int score;

    static {
        parseCharRateToInt = new HashMap<Character, Integer>();
        parseCharRateToInt.put('A', 14);
        parseCharRateToInt.put('K', 13);
        parseCharRateToInt.put('Q', 12);
        parseCharRateToInt.put('J', 11);
        parseCharRateToInt.put('T', 10);
    }

    public Card(final CardSuit cardSuit, final int cardRate, final int cardScore) {
        this.score = cardScore;
        this.suit = cardSuit;
        setRate(cardRate);
    }

    public Card(final CardSuit cardSuit, final char cardRate, final int cardScore) {
        this.score = cardScore;
        this.suit = cardSuit;
        this.rate = cardRate;
    }

    public Card(final CardSuit cardSuit, final char cardRate) {
        this.suit = cardSuit;
        this.rate = cardRate;
        try {
            this.score = parseCharRateToInt.get(cardRate);
        } catch (NullPointerException e) {
            this.score = cardRate - DELTAUT_FCHAR;
        }
    }

    public CardSuit getSuit() {
        return this.suit;
    }

    public char getRate() {
        return this.rate;
    }

    public boolean equalsByRate(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        return Objects.equal(this.rate, other.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.rate, this.suit);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        return Objects.equal(this.rate, other.rate) && Objects.equal(this.suit, other.suit);
    }

    @Override
    public String toString() {
        return "" + suit.getCharSuit() + rate;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(final Card obj) {
        return Integer.compare(this.score, obj.score);
    }

    private void setRate(final int intRate) {
        if (intRate >= 2 && intRate <= MAX_NONCHAR_RANK) {
            this.rate = (char) (intRate + DELTAUT_FCHAR);
        } else {
            switch ( intRate ) {
                case TEN_SCORE : {
                    this.rate = 'T';
                    break;
                }
                case JACK_SCORE : {
                    this.rate = 'J';
                    break;
                }
                case QUEEN_SCORE : {
                    this.rate = 'Q';
                    break;
                }
                case KING_SCORE : {
                    this.rate = 'K';
                    break;
                }
                case ACE_SCORE : {
                    this.rate = 'A';
                    break;
                }
                default : {
                    throw new IllegalArgumentException("Unknow card rate.(2-9, T, J, Q, K, A");
                }
            }
        }
    }

}
