package com.rulezzz.pkr.core.basestructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rulezzz.pkr.core.card.Card;

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
    
    public void drawCards(Card... foldCards) {
        if (foldCards.length > getHand().getCards().size()) {
            throw new ArithmeticException("count of holding cards don't match count of cards on box");
        }
        List<Card> cards = getHand().getCards();
        cards.removeAll(Arrays.asList(foldCards));
        
        countOfNeededCards = foldCards.length;
        this.boxStatus = BoxStatus.DRAW;
        getHand().setDrawStatus(true);
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
        addCards(cards);
    }

    public void buyCard() {
        this.boxStatus = BoxStatus.BUY_CARD;
    }

    public int getPayment() {
        return this.handList.get(0).getHandICombination().getMultiplier() * this.bet;
    }
}


