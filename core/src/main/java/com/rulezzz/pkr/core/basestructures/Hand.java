package com.rulezzz.pkr.core.basestructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Objects;


import com.rulezzz.pkr.core.basestructuresengine.ConsilienceCount;
import com.rulezzz.pkr.core.basestructuresengine.ConsilienceCounter;
import com.rulezzz.pkr.core.combination.*;


public class Hand implements Comparable<Hand>{

    public static final int FIVECARD = 5;
    private static final int DELTAFLC = 4;
    private static final int DELTAFLCWH = 9;
    private List<Card> cards = new ArrayList<Card>();
    private Boolean drawStatus = false;

    public void setDrawStatus(Boolean status) {
        this.drawStatus = status;
    }

    public Hand(Card... card) {
        for (int i = 0; i < card.length; i++) {
            this.cards.add(card[i]);
        }
    }

    public Hand() {
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public void add(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void removeCardByMask(int... mask) {
        for (int i = mask.length - 1; i >= 0; i--) {
            if (mask[i] == 1) {
                this.cards.remove(i);
            }
        }
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void sort() {
        Collections.sort(this.cards);
    }

    protected boolean isFlush() {
        for (int i = 1; i < this.cards.size(); i++) {
            if (this.cards.get(0).getSuit() != this.cards.get(i).getSuit()) {
                return false;
            }
        }
        return true;
    }

    public ICombination getHandICombination() {
        Collections.sort(this.cards);
        Collections.reverse(this.cards);
        if (drawStatus) {
            return null;
        }
        ConsilienceCounter counter = new ConsilienceCounter(this.cards);
        switch (counter.getConsilience()) {
            case NOCONSILIENCE: {
                return createNonPairICombination();
            }
            case PAIR: {
                return createPairTypeICombination(counter);
            }
            case SET: {
                return createSetTypeICombination(counter);
            }
            case SQUARE: {
                return createFourOfaKindICombination(counter);
            }
            default: {
                throw new IllegalStateException("Unknown combination");
            }
        }
    }

    protected StraightTypes straightCheck() {
        if (this.cards.get(0).getScore()
                - this.cards.get(this.cards.size() - 1).getScore() == DELTAFLC) {
            return StraightTypes.STRAIGHT;
        } else {
            if (this.cards.get(0).getScore() - this.cards.get(1).getScore() == DELTAFLCWH) {
                return StraightTypes.WHEEL_STRAIGHT;
            } else {
                return StraightTypes.NOT_A_STRAIGHT;
            }
        }
    }

    private ICombination createPairTypeICombination(ConsilienceCounter counter) {
        List<Card> cardsCompare = new ArrayList<Card>();
        ICombination result;
        switch (counter.getConsilience1()) {
            case NOCONSILIENCE: {
                cardsCompare.add(this.cards.get(counter.getPairPosition()));
                for (Card card : this.cards) {
                    if (!card.equals(cardsCompare.get(0))) {
                        cardsCompare.add(card);
                    }
                }
                result = new Pair(cardsCompare);
                break;
            }
            case PAIR: {
                cardsCompare.add(this.cards.get(counter.getFirstPairPosition()));
                cardsCompare.add(this.cards.get(counter.getPairPosition()));
                for (Card card : this.cards) {
                    if (!card.equals(cardsCompare.get(0))
                            && !card.equals(cardsCompare.get(1))) {
                        cardsCompare.add(card);
                    }
                }
                result = new TwoPairs(cardsCompare);
                break;
            }
            case SET: {
                cardsCompare.add(this.cards.get(counter.getFirstPairPosition()));
                cardsCompare.add(this.cards.get(counter.getPairPosition()));
                result = new FullHouse(cardsCompare);
                break;
            }
            default : {
                throw new IllegalStateException("Unknown combination");
            }
        }
        return result;
    }

    private ICombination createSetTypeICombination(ConsilienceCounter counter) {
        List<Card> cardsCompare = new ArrayList<Card>();
        if (counter.getConsilience1().equals(ConsilienceCount.NOCONSILIENCE)) {
            cardsCompare.add(this.cards.get(counter.getPairPosition()));
            for (Card card : this.cards) {
                if (!card.equals(cardsCompare.get(0))) {
                    cardsCompare.add(card);
                }
            }
            return new TreeOfKind(cardsCompare);
        } else {
            cardsCompare.add(this.cards.get(counter.getPairPosition()));
            cardsCompare.add(this.cards.get(counter.getFirstPairPosition()));
            return new FullHouse(cardsCompare);
        }

    }

    private ICombination createFourOfaKindICombination(
            ConsilienceCounter counter) {
        List<Card> cardsCompare = new ArrayList<Card>();
        cardsCompare.add(this.cards.get(counter.getPairPosition()));
        for (Card card : this.cards) {
            if (!card.equals(cardsCompare.get(0))) {
                cardsCompare.add(card);
            }
        }
        return new FourOfKind(cardsCompare);
    }

    private ICombination createNonPairICombination() {
        List<Card> cardsCompare = new ArrayList<Card>();
        if (!isFlush()) {
            switch (this.straightCheck()) {
                case NOT_A_STRAIGHT: {
                    if (this.cards.get(1).getScore() == Card.KING_SCORE) {
                        for (int i = 2; i < this.cards.size(); i++) {
                            cardsCompare.add(this.cards.get(i));
                        }
                        return new AceKing(cardsCompare);
                    } else {
                        return new DoesntQualify(cardsCompare);
                    }
                }
                case WHEEL_STRAIGHT: {
                    cardsCompare.add(this.cards.get(1));
                    return new Straight(cardsCompare);
                }
                case STRAIGHT: {
                    cardsCompare.add(this.cards.get(0));
                    return new Straight(cardsCompare);
                }
                default: {
                    throw new IllegalStateException(
                            "unknown non-pair combination");
                }
            }
        } else {
            switch (this.straightCheck()) {
                case NOT_A_STRAIGHT: {
                    for (Card card : this.cards) {
                        cardsCompare.add(card);
                    }
                    return new Flush(cardsCompare);
                }
                case WHEEL_STRAIGHT: {
                    cardsCompare.add(this.cards.get(1));
                    return new StraightFlush(cardsCompare);
                }
                case STRAIGHT: {
                    if (this.cards.get(1).getScore() == Card.KING_SCORE) {
                        return new RoyalFlush(cardsCompare);
                    } else {
                        cardsCompare.add(this.cards.get(0));
                        return new StraightFlush(cardsCompare);
                    }
                }
                default: {
                    throw new IllegalStateException(
                            "unknown non-pair combination");
                }
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hand other = (Hand) obj;
        return Objects.equal(this.getHandICombination(), other.getHandICombination());
    }

    @Override
    public String toString() {
        return this.cards.toString();
    }

    @Override
    public int compareTo(Hand other) {
        return this.getHandICombination().compareTo(other.getHandICombination());
    }
}