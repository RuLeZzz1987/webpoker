package com.rulezzz.pkr.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerBox extends Box {

    private int ante;
    private int bet;
    private int countOfNeededCards;
    private BoxStatus boxStatus = BoxStatus.DEALED;
    private List<Hand> handList = new ArrayList<Hand>();

    public PlayerBox(Hand hand, int bet) {
        this.handList.add(hand);
        this.ante = bet;
        setHand(hand);
    }

    public PlayerBox() {
    }

    public PlayerBox(int bet) {
        this.ante = bet;
    }
    
    public Hand getHandHigheness(int i) {
        return handList.get(i);
    }
    
    public void drawCards(List<Boolean> list) {
        this.boxStatus = BoxStatus.DRAW;
        if ( list.size() != getHand().getCards().size()) {
            throw new ArithmeticException("count of holding cards don't match count of cards on box");
        }
        getHand().setDrawStatus(true);
        Iterator<Card> cardsIter = getHand().getCards().iterator();
        Iterator<Boolean> holdListIter = list.iterator();
        while (holdListIter.hasNext()) {
            if ( holdListIter.next() ) {
                cardsIter.next();
            } else {
                cardsIter.next();
                cardsIter.remove();
                countOfNeededCards++;
            }
        }
    }
    
    public int getCountOfNeededCards() {
        return countOfNeededCards;
    }
    
    public int getAnte() {
        return ante;
    }

    public void play() {
        boxStatus = BoxStatus.BET;
        this.bet = ante*2;
    }
    
    public int getBet() {
        return this.bet;
    }

    public void fold() {
        boxStatus = BoxStatus.FOLD;
    }

    public BoxStatus getStatus() {
        return boxStatus;
    }

    public void setCardsAfterDraw(List<Card> cards) {
        this.boxStatus = BoxStatus.DETERMINATION;
        setHand(cards);
    }

    public void buyCard() {
        this.boxStatus = BoxStatus.BUY_CARD;
    }
}


