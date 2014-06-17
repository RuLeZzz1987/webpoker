package com.rulezzz.pkr.core;

import java.util.ArrayList;
import java.util.List;

public class ConsilienceCounter {
    
    private int pairPosition = 0;
    private int consilience = 0;
    private int firstPairPosition = 0;
    private int consilience1 = 0;
    private int flagFirstPairFound = 0;
    private List<Card> hand = new ArrayList<Card>();

    public ConsilienceCounter(List<Card> incomeHand) {
        if (incomeHand.size() != Hand.FIVECARD) {
            throw new IllegalStateException("Cards count in hand != 5");
        }
        this.hand = incomeHand;
        count();
    }

    private void count() {
        for (int i = 1; i < this.hand.size(); i++) {
            if (this.hand.get(pairPosition).getScore() == this.hand.get(i).getScore()) {
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

    public int getConsilience() {
        return this.consilience;
    }

    public int getFirstPairPosition() {
        return this.firstPairPosition;
    }

    public int getConsilience1() {
        return this.consilience1;
    }

    public int getFlagFirstPairFound() {
        return this.flagFirstPairFound;
    }

}
