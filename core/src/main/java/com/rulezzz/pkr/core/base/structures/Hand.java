package com.rulezzz.pkr.core.base.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Objects;
import com.rulezzz.pkr.core.base.engines.ConsilienceCount;
import com.rulezzz.pkr.core.base.engines.ConsilienceCounter;
import com.rulezzz.pkr.core.base.engines.GameMath;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.combination.AbstractCombination;
import com.rulezzz.pkr.core.combination.AceKing;
import com.rulezzz.pkr.core.combination.DoesntQualify;
import com.rulezzz.pkr.core.combination.Flush;
import com.rulezzz.pkr.core.combination.FourOfKind;
import com.rulezzz.pkr.core.combination.FullHouse;
import com.rulezzz.pkr.core.combination.Pair;
import com.rulezzz.pkr.core.combination.RoyalFlush;
import com.rulezzz.pkr.core.combination.Straight;
import com.rulezzz.pkr.core.combination.StraightFlush;
import com.rulezzz.pkr.core.combination.StraightTypes;
import com.rulezzz.pkr.core.combination.TreeOfKind;
import com.rulezzz.pkr.core.combination.TwoPairs;

public class Hand implements Comparable<Hand> {

    public static final int FIVECARD = 5;
    private static final int DELTAFLC = 4;
    private static final int DELTAFLCWH = 9;
    private List<Card> wholeCards = new ArrayList<Card>();
    private Boolean drawStatus = false;
    private AbstractCombination main;
    private AbstractCombination additional;

    public void setDrawStatus(Boolean status) {
        this.drawStatus = status;
    }

    public Boolean getDrawStatus() {
        return this.drawStatus;
    }

    public Hand(Card... card) {
        for (int i = 0; i < card.length; i++) {
            this.wholeCards.add(card[i]);
        }
    }

    public Hand() {
    }

    public void add(Card card) {
        this.wholeCards.add(card);
    }

    public void add(List<Card> cards) {
        this.wholeCards.addAll(cards);
    }

    public void removeCardByMask(int... mask) {
        for (int i = mask.length - 1; i >= 0; i--) {
            if (mask[i] == 1) {
                this.wholeCards.remove(i);
            }
        }
    }

    public List<Card> getCards() {
        return this.wholeCards;
    }

    public void sort() {
        Collections.sort(this.wholeCards);
    }

    protected boolean isFlush(List<Card> fiveCardCombo) {
        for (int i = 1; i < fiveCardCombo.size(); i++) {
            if (fiveCardCombo.get(0).getSuit() != fiveCardCombo.get(i)
                    .getSuit()) {
                return false;
            }
        }
        return true;
    }

    public AbstractCombination getHandAbstractCombination() {
        if (this.wholeCards.size() == Hand.FIVECARD) {
            this.main = this.getHandAbstractCombination(this.wholeCards);
        } else {
            this.generateMainComboFromWholeCards();
        }
        return this.main;
    }

    private void generateMainComboFromWholeCards() {
        this.main = null;
        List<ArrayList<Card>> fiveCardsLists = new GameMath()
                .generateCombinations(this.wholeCards, Hand.FIVECARD);
        List<AbstractCombination> wholeComboList = this.sortGeneratedCardLists(fiveCardsLists);
        this.main = wholeComboList.get(0);
        wholeComboList.remove(0);
        boolean mainEqAK = this.main.getClass().equals(AceKing.class);
        boolean mainEqDnq = this.main.getClass().equals(DoesntQualify.class);
        
        if (!mainEqAK && !mainEqDnq) {
            for (int i = wholeComboList.size() - 1; i >= 0; i--) {
                if (this.rulesForCalcAdditCombo(this.main, wholeComboList.get(i))) {
                    wholeComboList.remove(i);
                }
            }
            if ( wholeComboList.size() != 0 ) {
                this.additional = wholeComboList.get(0);
            }
            boolean mainEq2P = this.main.getClass().equals(TwoPairs.class);
            boolean mainEqSet = this.main.getClass().equals(TreeOfKind.class);
            
            if ((mainEq2P || mainEqSet) && this.additional.getClass().equals(Pair.class)) {
                char rate1p = this.main.getKickersList().get(0).getRate();
                char rate2p = this.main.getKickersList().get(1).getRate();
                this.additional = searchAdditionalAceKingOnFiveCards(this.additional);
                if (rate1p == 'A' && rate2p == 'K') {
                    this.additional = null;
                }
            }
        } 
        boolean mainEqFull = this.main.getClass().equals(FullHouse.class);
        
        if (mainEqFull && !this.additional.getClass().equals(FullHouse.class)) {
            this.additional = searchAdditionalAceKingOnFiveCards(this.additional);
        }
    }

    private boolean rulesForCalcAdditCombo(AbstractCombination highest, AbstractCombination current) {
        boolean highEqCur = highest.equals(current);
        boolean highIsStr = highest.getClass().equals(Straight.class);
        boolean curIsDnq = current.getClass().equals(DoesntQualify.class);
        
        if ( ( highEqCur && !highIsStr ) || curIsDnq) {
                return true;
            }
        
        boolean highEq1P = highest.getClass().equals(Pair.class);
        boolean highEqCurClass = highest.getClass().equals(current.getClass());
        boolean highEqFull = highest.getClass().equals(FullHouse.class);
        boolean highEqSet = highest.getClass().equals(TreeOfKind.class);
        
        if ( highEq1P || ( highEqCurClass && ( highEqFull || highEqSet ) ) ) {
                char highestRate = highest.getKickersList().get(0).getRate();
                char currentRate = current.getKickersList().get(0).getRate();
                if (highestRate == currentRate) {
                    return true;
                }
        }
        
        boolean highEq2P = highest.getClass().equals(TwoPairs.class);
        boolean curEq2P = current.getClass().equals(TwoPairs.class);
        
        if ( highEq2P && curEq2P) {
            char highestRate1 = highest.getKickersList().get(0).getRate();
            char currentRate1 = current.getKickersList().get(0).getRate();
            char highestRate2 = highest.getKickersList().get(1).getRate();
            char currentRate2 = current.getKickersList().get(1).getRate();
            if (highestRate1 == currentRate1 && highestRate2 == currentRate2) {
                return true;
            }
        }

        return false;
    }

    public AbstractCombination getHandAdditionalAbstractCombination() {
        if ( this.wholeCards.size() == Hand.FIVECARD) {
            this.additional = this.searchAdditionalAceKingOnFiveCards(this.main);
        }
        return this.additional;
    }
    
    private List<AbstractCombination> sortGeneratedCardLists(List<ArrayList<Card>> fiveCardLists) {
        List<AbstractCombination> result = new ArrayList<AbstractCombination>();
        for (ArrayList<Card> cards : fiveCardLists) {
            result.add(this.getHandAbstractCombination(cards));
        }
        Collections.sort(result);
        Collections.reverse(result);
        return result;
    }

    private AbstractCombination searchAdditionalAceKingOnFiveCards(AbstractCombination wholeCards) {
        if (wholeCards.getClass().equals(AceKing.class)) {
            return null;
        }
        AbstractCombination result = null;
        Boolean aces = false;
        Boolean kings = false;
        for (Card card : wholeCards.getKickersList()) {
            if (card.getRate() == 'K') {
                kings = true;
            }
            if (card.getRate() == 'A') {
                aces = true;
            }
        }
        if (aces
                && kings
                && (wholeCards.getClass().equals(Pair.class) || wholeCards.getClass().equals(TreeOfKind.class))) {
            result = new AceKing(wholeCards.getKickersList());
        }
        if (aces && kings && wholeCards.getClass() == TwoPairs.class
                && wholeCards.getKickersList().get(1).getRate() != 'K') {
            result = new AceKing(wholeCards.getKickersList());
        }
        return result;
    }

    private AbstractCombination getHandAbstractCombination(List<Card> fiveCardCombo) {
        Collections.sort(fiveCardCombo);
        Collections.reverse(fiveCardCombo);
        if (drawStatus) {
            return null;
        }
        ConsilienceCounter counter = new ConsilienceCounter(fiveCardCombo);
        switch (counter.getConsilience()) {
            case NOCONSILIENCE: {
                return createNonPairICombination(fiveCardCombo);
            }
            case PAIR: {
                return createPairTypeICombination(fiveCardCombo, counter);
            }
            case SET: {
                return createSetTypeICombination(fiveCardCombo, counter);
            }
            case SQUARE: {
                return createFourOfaKindICombination(fiveCardCombo, counter);
            }
            default: {
                throw new IllegalStateException("Unknown combination");
            }
        }
    }

    protected StraightTypes straightCheck(List<Card> fiveCardCombo) {
        if (fiveCardCombo.get(0).getScore()
                - fiveCardCombo.get(fiveCardCombo.size() - 1).getScore() == DELTAFLC) {
            return StraightTypes.STRAIGHT;
        } else {
            if (fiveCardCombo.get(0).getScore()
                    - fiveCardCombo.get(1).getScore() == DELTAFLCWH) {
                return StraightTypes.WHEEL_STRAIGHT;
            } else {
                return StraightTypes.NOT_A_STRAIGHT;
            }
        }
    }

    private AbstractCombination createPairTypeICombination(
            List<Card> fiveCardCombo, ConsilienceCounter counter) {
        List<Card> cardsCompare = new ArrayList<Card>();
        AbstractCombination result;
        switch (counter.getConsilience1()) {
            case NOCONSILIENCE: {
                cardsCompare.add(fiveCardCombo.get(counter.getPairPosition()));
                for (Card card : fiveCardCombo) {
                    if (!card.equalsByRate(cardsCompare.get(0))) {
                        cardsCompare.add(card);
                    }
                }
                result = new Pair(cardsCompare);
                break;
            }
            case PAIR: {
                cardsCompare.add(fiveCardCombo.get(counter
                        .getFirstPairPosition()));
                cardsCompare.add(fiveCardCombo.get(counter.getPairPosition()));
                for (Card card : fiveCardCombo) {
                    if (!card.equalsByRate(cardsCompare.get(0))
                            && !card.equalsByRate(cardsCompare.get(1))) {
                        cardsCompare.add(card);
                    }
                }
                result = new TwoPairs(cardsCompare);
                break;
            }
            case SET: {
                cardsCompare.add(fiveCardCombo.get(counter
                        .getFirstPairPosition()));
                cardsCompare.add(fiveCardCombo.get(counter.getPairPosition()));
                result = new FullHouse(cardsCompare);
                break;
            }
            default: {
                throw new IllegalStateException("Unknown combination");
            }
        }
        return result;
    }

    private AbstractCombination createSetTypeICombination(
            List<Card> fiveCardCombo, ConsilienceCounter counter) {
        List<Card> cardsCompare = new ArrayList<Card>();
        if (counter.getConsilience1().equals(ConsilienceCount.NOCONSILIENCE)) {
            cardsCompare.add(fiveCardCombo.get(counter.getPairPosition()));
            for (Card card : fiveCardCombo) {
                if (!card.equalsByRate(cardsCompare.get(0))) {
                    cardsCompare.add(card);
                }
            }
            return new TreeOfKind(cardsCompare);
        } else {
            cardsCompare.add(fiveCardCombo.get(counter.getPairPosition()));
            cardsCompare.add(fiveCardCombo.get(counter.getFirstPairPosition()));
            return new FullHouse(cardsCompare);
        }

    }

    private AbstractCombination createFourOfaKindICombination(
            List<Card> fiveCardCombo, ConsilienceCounter counter) {
        List<Card> cardsCompare = new ArrayList<Card>();
        cardsCompare.add(fiveCardCombo.get(counter.getPairPosition()));
        for (Card card : fiveCardCombo) {
            if (!card.equalsByRate(cardsCompare.get(0))) {
                cardsCompare.add(card);
            }
        }
        return new FourOfKind(cardsCompare);
    }

    private AbstractCombination createNonPairICombination(
            List<Card> fiveCardCombo) {
        List<Card> cardsCompare = new ArrayList<Card>();
        if (!isFlush(fiveCardCombo)) {
            switch (this.straightCheck(fiveCardCombo)) {
                case NOT_A_STRAIGHT: {
                    if (fiveCardCombo.get(1).getScore() == Card.KING_SCORE) {
                        for (int i = 2; i < fiveCardCombo.size(); i++) {
                            cardsCompare.add(fiveCardCombo.get(i));
                        }
                        return new AceKing(cardsCompare);
                    } else {
                        return new DoesntQualify(cardsCompare);
                    }
                }
                case WHEEL_STRAIGHT: {
                    cardsCompare.add(fiveCardCombo.get(1));
                    return new Straight(cardsCompare);
                }
                case STRAIGHT: {
                    cardsCompare.add(fiveCardCombo.get(0));
                    return new Straight(cardsCompare);
                }
                default: {
                    throw new IllegalStateException(
                            "unknown non-pair combination");
                }
            }
        } else {
            switch (this.straightCheck(fiveCardCombo)) {
                case NOT_A_STRAIGHT: {
                    for (Card card : fiveCardCombo) {
                        cardsCompare.add(card);
                    }
                    return new Flush(cardsCompare);
                }
                case WHEEL_STRAIGHT: {
                    cardsCompare.add(fiveCardCombo.get(1));
                    return new StraightFlush(cardsCompare);
                }
                case STRAIGHT: {
                    if (fiveCardCombo.get(1).getScore() == Card.KING_SCORE) {
                        return new RoyalFlush(cardsCompare);
                    } else {
                        cardsCompare.add(fiveCardCombo.get(0));
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
        return Objects.hashCode(this.wholeCards);
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
        return Objects.equal(this.getHandAbstractCombination(),
                other.getHandAbstractCombination());
    }

    @Override
    public String toString() {
        this.getHandAbstractCombination();
        return this.main.toString();
    }

    @Override
    public int compareTo(Hand other) {
        return this.getHandAbstractCombination().compareTo(
                other.getHandAbstractCombination());
    }
}