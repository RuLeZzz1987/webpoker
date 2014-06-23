package com.rulezzz.pkr.core;

import java.util.List;

public class ConsilienceCounter {
    
    private int pairPosition = 0;
    private int consilience = 0;
    private int firstPairPosition = 0;
    private int consilience1 = 0;
    private int flagFirstPairFound = 0;
    private List<Card> hand;

    public ConsilienceCounter(List<Card> incomeHand) {
        if (incomeHand.size() != Hand.FIVECARD) {
            throw new IllegalStateException("Cards count in hand != 5");
        }
        this.hand = incomeHand;
        count();
    }

    private void count() {
        for (int i = 1; i < this.hand.size(); i++) {
            if (this.hand.get(this.pairPosition).getScore() == this.hand.get(i).getScore()) {
                if (this.flagFirstPairFound <= 1) {
                    this.consilience++;
                }
            } else {
                if (this.consilience == 0) {
                    this.pairPosition = i;
                } else {
                    if (this.flagFirstPairFound == 0) {
                        this.firstPairPosition = this.pairPosition;
                        this.pairPosition = i;
                        this.consilience1 = this.consilience;
                        this.consilience = 0;
                        this.flagFirstPairFound = 1;
                    }
                }
            }
        }
        if (this.consilience == 0 && this.consilience1 != 0) {
            this.consilience = this.consilience1;
            this.consilience1 = 0;
            this.pairPosition = this.firstPairPosition;
            this.firstPairPosition = 0;
        }
    }

    public int getPairPosition() {
        return this.pairPosition;
    }

    public ConsilienceCount getConsilience() {
        return getEnumCountFromInt(this.consilience);
    }

    public int getFirstPairPosition() {
        return this.firstPairPosition;
    }

    public ConsilienceCount getConsilience1() {
        return getEnumCountFromInt(this.consilience1);
    }

    public int getFlagFirstPairFound() {
        return this.flagFirstPairFound;
    }
    
    private ConsilienceCount getEnumCountFromInt(int count) {
        switch(count) {
            case 0: {
                return ConsilienceCount.NOCONSILIENCE;
            }
            case 1: {
                return ConsilienceCount.PAIR;
            }
            case 2: {
                return ConsilienceCount.SET;
            }
            case 3: {
                return ConsilienceCount.SQUARE;
            }
            default : {
                throw new ArithmeticException("unknown consilience count");
            }
        }
    }

}
