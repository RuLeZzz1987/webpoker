package com.rulezzz.pkr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerBox extends Box implements Serializable {

    private int bet;
    private BoxStatus boxStatus = BoxStatus.DEALED;
    private List<Hand> handList = new ArrayList<Hand>();

    public PlayerBox(Hand hand, int bet) {
        this.handList.add(hand);
        this.bet = bet;
    }

    public PlayerBox() {
        // TODO Auto-generated constructor stub
    }

    public PlayerBox(int bet) {
        this.bet = bet;
    }

    public int getBet() {
        return bet;
    }

    public void play() {
        boxStatus = BoxStatus.BET;
    }

    public void fold() {
        boxStatus = BoxStatus.FOLD;
    }

    public BoxStatus getStatus() {
        return boxStatus;
    }

}
