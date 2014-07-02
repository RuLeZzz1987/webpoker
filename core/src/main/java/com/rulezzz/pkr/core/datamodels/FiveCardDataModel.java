package com.rulezzz.pkr.core.datamodels;

import java.util.ArrayList;
import java.util.List;

import com.rulezzz.pkr.core.basestructures.Box;
import com.rulezzz.pkr.core.basestructures.Card;
import com.rulezzz.pkr.core.basestructures.Deck;
import com.rulezzz.pkr.core.basestructures.PlayerBox;

public class FiveCardDataModel {

    private int bankroll;
    private Box dealerBox = new Box();
    private List<PlayerBox> playerBoxes = new ArrayList<PlayerBox>();
    private List<Card> cardList = new Deck().getDeck();
    private List<Integer> defaultBets = new ArrayList<Integer>();
    
    public int getBankroll() {
        return bankroll;
    }
    public void setBankroll(int bankroll) {
        this.bankroll += bankroll;
    }
    public Box getDealerBox() {
        return dealerBox;
    }
    public void setDealerBox(Box dealerBox) {
        this.dealerBox = dealerBox;
    }
    public List<PlayerBox> getPlayerBoxes() {
        return playerBoxes;
    }
    public void setPlayerBoxes(List<PlayerBox> playerBoxes) {
        this.playerBoxes = playerBoxes;
    }
    public List<Card> getCardList() {
        return cardList;
    }
    public void updateCardList() {
        this.cardList = new Deck().getDeck();
    }
    public List<Integer> getDefaultBets() {
        return defaultBets;
    }
    
    public void setDefaultBets(int bet) {
        this.defaultBets.add(bet);
    }

}
