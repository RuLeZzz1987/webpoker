package com.rulezzz.pkr.core.card;

import com.google.common.base.Objects;

public class Card implements Comparable<Card> {

    public static final int ACE_SCORE = 14;
    public static final int KING_SCORE = 13;
    public static final int QUEEN_SCORE = 12;
    public static final int JACK_SCORE = 11;
    public static final int TEN_SCORE = 10;
    public static final int MAX_NONCHAR_RANK = 9;
    private static final int DELTAUT_FCHAR = 48;
    private CardSuit suit;
    private char rate;
    private int score;

    public Card(final CardSuit cardSuit, final int cardRate, final int cardScore) {
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
        switch ( cardRate ) {
            case 'A' : {
                this.score = ACE_SCORE;
                break;
            }
            case 'K' : {
                this.score = KING_SCORE;
                break;
            }
            case 'Q' : {
                this.score = QUEEN_SCORE;
                break;
            }
            case 'J' : {
                this.score = JACK_SCORE;
                break;
            }
            case 'T' : {
                this.score = TEN_SCORE;
                break;
            }
            default : {
                this.score = (int) cardRate - DELTAUT_FCHAR;
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
        return Objects.equal(this.rate, other.rate);
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
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
        if ((i >= 2) && (i <= MAX_NONCHAR_RANK)) {
            i += DELTAUT_FCHAR;
            this.rate = (char) i;
        } else {
            switch ( i ) {
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
                    throw new IllegalArgumentException(
                            "Unknow card rate.(2-9, T, J, Q, K, A");
                }
            }
        }
    }

}
