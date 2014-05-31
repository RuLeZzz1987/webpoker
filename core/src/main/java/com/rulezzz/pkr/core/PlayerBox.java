package com.rulezzz.pkr.core;

import java.util.ArrayList;
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

    public int getAnte() {
        return ante;
    }

    public void play() {
        boxStatus = BoxStatus.BET;
        this.bet = ante*2;
    }

    public void fold() {
        boxStatus = BoxStatus.FOLD;
    }

    public BoxStatus getStatus() {
        return boxStatus;
    }

}
