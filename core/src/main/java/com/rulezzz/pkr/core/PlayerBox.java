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
    }

    public PlayerBox() {
        // TODO Auto-generated constructor stub
    }

    public PlayerBox(int bet) {
        this.ante = bet;
    }
    
    public Hand getHandHigheness(int i) {
        return handList.get(i);
    }
    
    public void drawCards(LinkedList<Boolean> holdList) {
        this.boxStatus = BoxStatus.DRAW;
        Iterator<Card> cardsIter = handList.get(0).getCards().iterator();
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

}
