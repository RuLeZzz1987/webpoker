package com.rulezzz.pkr.core;

import com.google.common.base.Objects;

public class Card implements Comparable<Card> {

    public static final int ACESCORE = 14;
    public static final int KINGSCORE = 13;
    public static final int QUEENSCORE = 12;
    public static final int JACKSCORE = 11;
    public static final int TENSCORE = 10;
    public static final int MAXNONCHARRANK = 9;
    private static final int DELTAUTFCHAR = 48;
    private CardSuit suit;
    private char rate;
    private int score;

    public Card(final CardSuit cardSuit, final int cardRate,
            final int cardScore) {
        this.score = cardScore;
        this.suit = cardSuit;
        setRate(cardRate);
    }

    public Card(final CardSuit cardSuit, final char cardRate,
            final int cardScore) {
        this.score = cardScore;
        this.suit = cardSuit;
        this.rate = cardRate;
    }

    public Card(final CardSuit cardSuit, final char cardRate) {
        this.suit = cardSuit;
        this.rate = cardRate;
        switch (cardRate) {
            case 'A': {
                this.score = ACESCORE;
                break;
            }
            case 'K': {
                this.score = KINGSCORE;
                break;
            }
            case 'Q': {
                this.score = QUEENSCORE;
                break;
            }
            case 'J': {
                this.score = JACKSCORE;
                break;
            }
            case 'T': {
                this.score = TENSCORE;
                break;
            }
            default: {
                this.score = (int) cardRate - DELTAUTFCHAR;
            }
        }
    }

    public CardSuit getSuit() {
        return this.suit;
    }

    public char getRate() {
        return this.rate;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.suit, this.rate);
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
        return Objects.equal(this.suit, other.suit) 
                && Objects.equal(this.rate, other.rate);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.suit.getCharSuit());
        result.append(this.rate);
        return result.toString();
    }

    public int getScore() {
        return this.score;

    }

    @Override
    public int compareTo(final Card obj) {
        return Integer.compare(this.score, obj.score);
    }

    private void setRate(final int intRate) {
        int i = intRate;
        if ((i >= 2) && (i <= MAXNONCHARRANK)) {
            i += DELTAUTFCHAR;
            this.rate = (char) i;
        } else {
            switch (i) {
                case TENSCORE: {
                    this.rate = 'T';
                    break;
                }
                case JACKSCORE: {
                    this.rate = 'J';
                    break;
                }
                case QUEENSCORE: {
                    this.rate = 'Q';
                    break;
                }
                case KINGSCORE: {
                    this.rate = 'K';
                    break;
                }
                case ACESCORE: {
                    this.rate = 'A';
                    break;
                }
                default: {
                    throw new IllegalArgumentException(
                            "Unknow card rate.(2-9, T, J, Q, K, A");
                }
            }
        }
    }

}
