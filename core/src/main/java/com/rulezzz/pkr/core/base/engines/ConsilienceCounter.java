package com.rulezzz.pkr.core.base.engines;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rulezzz.pkr.core.base.structures.Hand;
import com.rulezzz.pkr.core.card.Card;

public class ConsilienceCounter {
    
    private int pairPosition = 0;
    private int consilience = 0;
    private int firstPairPosition = 0;
    private int consilience1 = 0;
    private int flagFirstPairFound = 0;
    private List<Card> hand;
    private static Map<Integer, ConsilienceCount> consilCount;
    
    static {
        consilCount = new HashMap<Integer, ConsilienceCount>();
        consilCount.put(0, ConsilienceCount.NOCONSILIENCE);
        consilCount.put(1, ConsilienceCount.PAIR);
        consilCount.put(2, ConsilienceCount.SET);
        consilCount.put(3, ConsilienceCount.SQUARE);
    }

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
        return consilCount.get(count);
    }

}
