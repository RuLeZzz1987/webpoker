package com.rulezzz.pkr.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PlayerBox extends Box {

    private int ante;
    private int bet = 0;
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
    
    public void drawCards(LinkedList<Boolean> holdList) {
        this.boxStatus = BoxStatus.DRAW;
        if ( holdList.size() != getHand().getCards().size()) {
            throw new ArithmeticException("count of holding cards don't match count of cards on box");
        }
        getHand().setDrawStatus(true);
        Iterator<Card> cardsIter = getHand().getCards().iterator();
        Iterator<Boolean> holdListIter = holdList.iterator();
        while (holdListIter.hasNext()) {
            if ( holdListIter.next() ) {
                cardsIter.next();
            } else {
                cardsIter.next();
                cardsIter.remove();
            }
        }
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

    public void getCardsAfterDraw(List<Card> cards) {
        this.boxStatus = BoxStatus.DETERMINATION;
        setHand(cards);
    }
}


