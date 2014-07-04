package com.rulezzz.pkr.core.gameengine;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.rulezzz.pkr.core.base.structures.Box;
import com.rulezzz.pkr.core.base.structures.Hand;
import com.rulezzz.pkr.core.base.structures.PlayerBox;
import com.rulezzz.pkr.core.base.structures.Box.BoxStatus;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.combination.AbstractCombination;
import com.rulezzz.pkr.core.datamodels.FiveCardDataModel;
import com.rulezzz.pkr.core.statuses.GameStatus;

public class Table implements Serializable {

    private static final long serialVersionUID = -3327856908669194148L;

    private FiveCardDataModel model = new FiveCardDataModel();
    
    public Table() {
        model.setGameStatus(GameStatus.BETS);
    }
    
    public void updateDeck(){
        model.updateCardList();
    }
    
    public List<Integer> getDefaultBets() {
        return model.getDefaultBets();
    }

    public GameStatus getGameStatus() {
        return model.getGameStatus();
    }
    
    public void deal() {
        if (model.getGameStatus() != GameStatus.DEAL) {
            throw new IllegalStateException("No money, no Cards!");
        }
        int k = Hand.FIVECARD;
        for (int i = 0; i < k; i++) {
            for (PlayerBox b : model.getPlayerBoxes()) {
                b.addCard(model.getCardList().get(0));
                model.getCardList().remove(0);
            }
            model.getDealerBox().addCard(model.getCardList().get(0));
            model.getCardList().remove(0);
        }
        for (PlayerBox b : model.getPlayerBoxes()) {
            b.sort();
            Collections.reverse(b.getHand().getCards());
            model.setDefaultBets(b.getAnte());
        }
        model.setGameStatus(GameStatus.DRAWS);
    }

    public List<PlayerBox> getBoxes() {
        return model.getPlayerBoxes();
    }

    public PlayerBox getBox(int i) {
        return model.getPlayerBoxes().get(i);
    }

    public void calculateDealResult() {
        AbstractCombination dealerCombo = model.getDealerBox().getHand().getHandICombination();
        if (dealerCombo.getHighness() != 0) {
            for (PlayerBox box : model.getPlayerBoxes()) {
                int compareResult = dealerCombo.compareTo(box.getHand().getHandICombination());
                switch ( compareResult ) {
                    case 0 : {
                        model.setBankroll(box.getAnte() + box.getBet());
                        break;
                    }
                    case -1 : {
                        model.setBankroll(box.getAnte() + box.getBet() + box.getPayment());
                        break;
                    }
                    default : {
                        model.setGameStatus(GameStatus.BETS);
                    }
                }
            }
            model.setGameStatus(GameStatus.SHOWDOWN);
        } else {
            
                if (model.getPlayerBoxes().size() != 0) {
                    model.setGameStatus(GameStatus.DEALER_DNQ);
                } else {
                    model.setGameStatus(GameStatus.SHOWDOWN);
                }
        }
    }
    
    public boolean checkAllDeterminated(){
            for (PlayerBox box : model.getPlayerBoxes()) {
                if ( box.getStatus() != BoxStatus.BET) {
                    return false;
                }
            }
            model.getDealerBox().sort();
            Collections.reverse(model.getDealerBox().getHand().getCards());
            return true;
    }

    public void handleDetermination() {
        for (PlayerBox box : model.getPlayerBoxes()) {
            if (box.getStatus() == BoxStatus.DRAW) {
                box.setCardsAfterDraw(generateDrawCardList(box.getCountOfNeededCards()));
                box.sort();
                Collections.reverse(box.getHand().getCards());
                break;
            }
        }
    }

    private List<Card> generateDrawCardList(int count) {
        List<Card> result = new LinkedList<Card>();
        for (int i = 0; i < count; i++) {
            result.add(model.getCardList().get(0));
            model.getCardList().remove(0);
        }
        return result;
    }

    public void makeBets(int... bets) {
        for (int bet: bets) {
            model.getPlayerBoxes().add(new PlayerBox(bet));
            model.setBankroll(-bet);
        }
        model.setGameStatus(GameStatus.DEAL);
    }

    public void checkBoxStatus() {
        Iterator<PlayerBox> iter = model.getPlayerBoxes().iterator();
        while (iter.hasNext()) {
            if (iter.next().getStatus() == BoxStatus.FOLD) {
                iter.remove();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < model.getPlayerBoxes().size(); i++) {
            result.append(" " + model.getPlayerBoxes().get(i).toString());
        }
        return result.toString() + " | " + model.getDealerBox().getHand().toString();
    }

    public Box getDealerBox() {
        return model.getDealerBox();
    }

    public void setBankroll(int bankroll) {
        model.setBankroll(bankroll);
    }
    
    public int getBankroll(){
        return model.getBankroll();
    }

    public void fold(int boxIndex) {
        model.getPlayerBoxes().remove(boxIndex);
        model.setGameStatus(GameStatus.DETERMINATION);
    }
    
    public void bet(int boxIndex) {
        PlayerBox box = model.getPlayerBoxes().get(boxIndex);
        box.play();
        model.setBankroll(-box.getBet());
        model.setGameStatus(GameStatus.DETERMINATION);
    }
    
    public void draw(int boxIndex, Card... foldCards) {
        PlayerBox box = model.getPlayerBoxes().get(boxIndex);
        model.setBankroll(-box.getAnte());
        box.drawCards(foldCards);
        model.setGameStatus(GameStatus.DETERMINATION);
    }

    public void draw(int boxIndex, List<Card> foldCards) {
        PlayerBox box = model.getPlayerBoxes().get(boxIndex);
        model.setBankroll(-box.getAnte());
        box.drawCards(foldCards);
        model.setGameStatus(GameStatus.DETERMINATION);
    }
    
    public void takeAnte(int boxIndex) {
        PlayerBox box = model.getPlayerBoxes().get(boxIndex);
        model.setBankroll(box.getAnte() + box.getBet() + box.getAnte());
        model.getPlayerBoxes().remove(boxIndex);
    }

    public void choiceBuyGameForDealer(int boxIndex) {
        PlayerBox box = model.getPlayerBoxes().get(boxIndex);
        model.setBankroll(-box.getAnte());
    }
    
    public void buyGame() {
        model.getDealerBox().getHand().getCards().remove(0);
        model.getDealerBox().addCard(model.getCardList().get(0));
        model.getCardList().remove(0);
        model.getDealerBox().sort();
        Collections.reverse(model.getDealerBox().getHand().getCards());
    }




}
