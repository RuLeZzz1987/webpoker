package com.rulezzz.pkr.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Hand {

    public static final int OMAHA = 4;
    public static final int FIVECARD = 5;
    public static final int TEXAS = 2;
    private static final int PAIR = 1;
    private static final int SET = 2;
    private static final int NOCONSILIENCE = 0;
    private static final int SQUARE = 3;
    private static final int DELTAFLC = 4;
    private static final int DELTAFLCWH = 9;
    private List<Card> hand = new LinkedList<Card>();
    private GameType gameType;
    private Boolean drawStatus = false;

    public void setDrawStatus(Boolean status) {
        this.drawStatus = status;
    }

    public int getStandardCardsCount() {
        switch ( this.gameType ) {
            case FIVECARD : {
                return FIVECARD;
            }
            case OMAHA : {
                return OMAHA;
            }
            case TEXAS : {
                return TEXAS;
            }
            default : {
                return FIVECARD;
            }
        }
    }

    public Hand(GameType gameType, Card... card) {
        this.gameType = gameType;
        for (int i = 0; i < card.length; i++) {
            this.hand.add(card[i]);
        }
        switch ( this.gameType ) {
            case FIVECARD : {
                if (this.hand.size() != FIVECARD) {
                    throw new IllegalArgumentException(
                            "Illegal cards count for this type of game. Correct number is 5!");
                }
                break;
            }
            case OMAHA : {
                if (this.hand.size() != OMAHA) {
                    throw new IllegalArgumentException(
                            "Illegal cards count for this type of game. Correct number is 4!");
                }
                break;
            }
            case TEXAS : {
                if (this.hand.size() != TEXAS) {
                    throw new IllegalArgumentException(
                            "Illegal cards count for this type of game. Correct number is 2!");
                }
                break;
            }
            default : {
                break;
            }
        }
    }

    public Hand() {
    }

    public void add(Card card) {
        this.hand.add(card);
    }

    public void add(List<Card> cards) {
        this.hand.addAll(cards);
    }

    public void removeCardByMask(int... mask) {
        for (int i = mask.length - 1; i >= 0; i--) {
            if (mask[i] == 1) {
                this.hand.remove(i);
            }
        }
    }

    public List<Card> getCards() {
        return this.hand;
    }

    public void sort() {
        Collections.sort(this.hand);
    }

    protected Boolean isFlushOnFiveCards() {
        for (int i = 1; i < this.hand.size(); i++) {
            if (this.hand.get(0).getSuit() != this.hand.get(i).getSuit()) {
                return false;
            }
        }
        return true;
    }

    public Combination getCombinationOnFiveCards() {
        if (drawStatus) {
            return new Combination("draw");
        }
        ConsilienceCounter counter = new ConsilienceCounter(this.hand);
        switch ( counter.getConsilience() ) {
            case NOCONSILIENCE : {
                return createNonPairCombination();
            }
            case PAIR : {
                return createPairTypeCombination(counter);
            }
            case SET : {
                return createSetTypeCombination(counter);
            }
            case SQUARE : {
                return createFourOfaKindCombination(counter);
            }
            default : {
                throw new IllegalStateException("Unknown combination");
            }
        }
    }

    private Combination createPairTypeCombination(ConsilienceCounter counter) {
        StringBuilder resultBuffer;
        if (counter.getConsilience1() == 0) {
            resultBuffer = new StringBuilder("2 ");
            resultBuffer.append(this.hand.get(counter.getPairPosition()).getScore());
            resultBuffer.append(" ");
            resultBuffer.append(this.hand.get(counter.getPairPosition()).getScore());
            resultBuffer.append(" ");
            for (int i = 0; i < this.hand.size(); i++) {
                if (this.hand.get(counter.getPairPosition()).getScore() != this.hand.get(i).getScore()) {
                    resultBuffer.append(this.hand.get(i).getScore());
                    resultBuffer.append(" ");
                }
            }
            return new Combination(resultBuffer.toString());
        }
        if (counter.getConsilience1() == 1) {
            resultBuffer = new StringBuilder("3 ");
            resultBuffer.append(this.hand.get(counter.getFirstPairPosition()).getScore());
            resultBuffer.append(" ");
            resultBuffer.append(this.hand.get(counter.getFirstPairPosition()).getScore());
            resultBuffer.append(" ");
            resultBuffer.append(this.hand.get(counter.getPairPosition()).getScore());
            resultBuffer.append(" ");
            resultBuffer.append(this.hand.get(counter.getPairPosition()).getScore());
            resultBuffer.append(" ");
            for (int i = 0; i < this.hand.size(); i++) {
                if (this.hand.get(counter.getPairPosition()).getScore() != this.hand.get(i).getScore()
                        && this.hand.get(counter.getFirstPairPosition()).getScore() != this.hand.get(i).getScore()) {
                    resultBuffer.append(this.hand.get(i).getScore());
                    resultBuffer.append(" ");
                }
            }
            return new Combination(resultBuffer.toString());
        }
        if (counter.getConsilience1() == 2) {
            resultBuffer = new StringBuilder("7 ");
            resultBuffer.append(this.hand.get(counter.getFirstPairPosition()).getScore());
            resultBuffer.append(" ");
            resultBuffer.append(this.hand.get(counter.getPairPosition()).getScore());
            resultBuffer.append(" ");
            return new Combination(resultBuffer.toString());
        }
        throw new IllegalStateException("Unknown combination");
    }

    private Combination createSetTypeCombination(ConsilienceCounter counter) {
        StringBuilder resultBuffer;
        if (counter.getConsilience1() == 0) {
            resultBuffer = new StringBuilder("4 ");
            resultBuffer.append(this.hand.get(counter.getPairPosition()).getScore());
            resultBuffer.append(" ");
            resultBuffer.append(this.hand.get(counter.getPairPosition()).getScore());
            resultBuffer.append(" ");
            resultBuffer.append(this.hand.get(counter.getPairPosition()).getScore());
            resultBuffer.append(" ");
            for (int i = 0; i < this.hand.size(); i++) {
                if (this.hand.get(counter.getPairPosition()).getScore() != this.hand.get(i).getScore()) {
                    resultBuffer.append(this.hand.get(i).getScore());
                    resultBuffer.append(" ");
                }
            }
            return new Combination(resultBuffer.toString());
        } else {
            resultBuffer = new StringBuilder("7 ");
            resultBuffer.append(this.hand.get(counter.getPairPosition()).getScore());
            resultBuffer.append(" ");
            resultBuffer.append(this.hand.get(counter.getFirstPairPosition()).getScore());
            resultBuffer.append(" ");
            return new Combination(resultBuffer.toString());
        }
    }

    private Combination createFourOfaKindCombination(ConsilienceCounter counter) {
        StringBuilder resultBuffer;
        resultBuffer = new StringBuilder("8 ");
        resultBuffer.append(this.hand.get(counter.getPairPosition()).getScore());
        resultBuffer.append(" ");
        for (int i = 0; i < this.hand.size(); i++) {
            if (this.hand.get(counter.getPairPosition()).getScore() != this.hand.get(i).getScore()) {
                resultBuffer.append(this.hand.get(i).getScore());
                resultBuffer.append(" ");
            }
        }
        return new Combination(resultBuffer.toString());
    }

    private Combination createNonPairCombination() {
        StringBuilder resultBuffer;
        resultBuffer = new StringBuilder();
        if (!isFlushOnFiveCards()) {
            if (this.hand.get(1).getScore() == Card.KING_SCORE
                    && this.hand.get(0).getScore() - this.hand.get(this.hand.size() - 1).getScore() != DELTAFLC) {
                resultBuffer.append("1 ");
                resultBuffer.append(this.hand.get(2).getScore());
                resultBuffer.append(" ");
                resultBuffer.append(this.hand.get(this.hand.size() - 2).getScore());
                resultBuffer.append(" ");
                resultBuffer.append(this.hand.get(this.hand.size() - 1).getScore());
                resultBuffer.append(" ");
                return new Combination(resultBuffer.toString());
            }
            if (this.hand.get(0).getScore() - this.hand.get(this.hand.size() - 1).getScore() == DELTAFLC) {
                resultBuffer.append("5 ");
                resultBuffer.append(this.hand.get(0).getScore());
                resultBuffer.append(" ");
                return new Combination(resultBuffer.toString());
            }
            if (this.hand.get(0).getScore() - this.hand.get(1).getScore() == DELTAFLCWH) {
                resultBuffer.append("5 ");
                resultBuffer.append(this.hand.get(1).getScore());
                resultBuffer.append(" ");
                return new Combination(resultBuffer.toString());
            }
            return new Combination("0 ");
        } else {
            if (this.hand.get(0).getScore() - this.hand.get(this.hand.size() - 1).getScore() != DELTAFLC
                    && this.hand.get(0).getScore() - this.hand.get(1).getScore() != DELTAFLCWH) {
                resultBuffer.append("6 ");
                for (Card card : this.hand) {
                    resultBuffer.append(card.getScore());
                    resultBuffer.append(" ");
                }
                return new Combination(resultBuffer.toString());
            }
            if (this.hand.get(0).getScore() - this.hand.get(this.hand.size() - 1).getScore() == DELTAFLC
                    && this.hand.get(0).getScore() != Card.ACE_SCORE) {
                resultBuffer.append("9 ");
                resultBuffer.append(this.hand.get(0).getScore());
                resultBuffer.append(" ");
                return new Combination(resultBuffer.toString());
            }
            if (this.hand.get(0).getScore() - this.hand.get(1).getScore() == DELTAFLCWH) {
                resultBuffer.append("9 ");
                resultBuffer.append(this.hand.get(1).getScore());
                resultBuffer.append(" ");
                return new Combination(resultBuffer.toString());
            }
            if (this.hand.get(1).getScore() == Card.KING_SCORE
                    && this.hand.get(0).getScore() - this.hand.get(this.hand.size() - 1).getScore() == DELTAFLC) {
                return new Combination("10 ");
            }
        }
        throw new IllegalStateException("unknown non-pair combination");
    }

    @Override
    public String toString() {
        return this.hand.toString();
    }

    public Boolean compareTo(Hand h) {
        sort();
        h.sort();
        if (this.hand.size() != h.getCards().size()) {
            return false;
        } else {
            for (int i = 0; i < this.hand.size(); i++) {
                if (!this.hand.get(i).equals(h.getCards().get(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
