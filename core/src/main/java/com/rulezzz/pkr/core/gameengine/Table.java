package com.rulezzz.pkr.core.gameengine;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.rulezzz.pkr.core.base.structures.Box;
import com.rulezzz.pkr.core.base.structures.Box.BoxStatus;
import com.rulezzz.pkr.core.base.structures.Hand;
import com.rulezzz.pkr.core.base.structures.PlayerBox;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.combination.AbstractCombination;
import com.rulezzz.pkr.core.datamodels.FiveCardDataModel;
import com.rulezzz.pkr.core.statuses.GameStatus;

public class Table implements Serializable {

    private static final long serialVersionUID = -3327856908669194148L;

    private FiveCardDataModel model = new FiveCardDataModel();
    
    public Table() {
        this.model.setGameStatus(GameStatus.BETS);
    }
    
    public void updateDeck(){
        this.model.updateCardList();
    }
    
    public List<Integer> getDefaultBets() {
        return this.model.getDefaultBets();
    }

    public GameStatus getGameStatus() {
        return this.model.getGameStatus();
    }
    
    public void deal() {
        for (int i = 0; i < Hand.FIVECARD; i++) {
            for (PlayerBox b : this.model.getPlayerBoxes()) {
                b.addCard(this.model.getCardList().get(0));
                this.model.getCardList().remove(0);
            }
            this.model.getDealerBox().addCard(this.model.getCardList().get(0));
            this.model.getCardList().remove(0);
        }
        for (PlayerBox b : this.model.getPlayerBoxes()) {
            b.sort();
            Collections.reverse(b.getHand().getCards());
            this.model.setDefaultBets(b.getAnte());
        }
        this.model.setGameStatus(GameStatus.DRAWS);
    }

    public List<PlayerBox> getBoxes() {
        return this.model.getPlayerBoxes();
    }

    public PlayerBox getBox(final int i) {
        return this.model.getPlayerBoxes().get(i);
    }

    public void calculateDealResult() {
        final AbstractCombination dealerCombo = this.model.getDealerBox().getHand().getHandAbstractCombination();
        if (dealerCombo.getHighness() != 0) {
            for (PlayerBox box : this.model.getPlayerBoxes()) {
                final int compareResult = dealerCombo.compareTo(box.getHand().getHandAbstractCombination());
                switch ( compareResult ) {
                    case 0 : {
                        this.model.setBankroll(box.getAnte() + box.getBet());
                        break;
                    }
                    case -1 : {
                        this.model.setBankroll(box.getAnte() + box.getBet() + box.getPayment());
                        break;
                    }
                    default : {
                        this.model.setGameStatus(GameStatus.BETS);
                    }
                }
            }
            this.model.setGameStatus(GameStatus.SHOWDOWN);
        } else {
            if (this.model.getGameStatus() != GameStatus.GAME_BOUGHT) {
                if (this.model.getPlayerBoxes().size() != 0) {
                    this.model.setGameStatus(GameStatus.DEALER_DNQ);
                } else {
                    this.model.setGameStatus(GameStatus.SHOWDOWN);
                }
            } else {
                for (PlayerBox box : this.model.getPlayerBoxes()) {
                    this.model.setBankroll(box.getAnte() + box.getBet());
                }
                this.model.setGameStatus(GameStatus.SHOWDOWN);
            }
        }
    }
    
    public boolean checkAllDeterminated(){
            for (PlayerBox box : this.model.getPlayerBoxes()) {
                if ( box.getStatus() != BoxStatus.BET) {
                    return false;
                }
            }
            this.model.getDealerBox().sort();
            Collections.reverse(this.model.getDealerBox().getHand().getCards());
            return true;
    }

    public void handleDetermination() {
        for (PlayerBox box : this.model.getPlayerBoxes()) {
            if (box.getStatus() == BoxStatus.DRAW) {
                box.setCardsAfterDraw(generateDrawCardList(box.getCountOfNeededCards()));
                box.sort();
                Collections.reverse(box.getHand().getCards());
                break;
            }
        }
    }

    private List<Card> generateDrawCardList(final int count) {
        final List<Card> result = new LinkedList<Card>();
        for (int i = 0; i < count; i++) {
            result.add(this.model.getCardList().get(0));
            this.model.getCardList().remove(0);
        }
        return result;
    }

    public void makeBets(int... bets) {
        for (int bet: bets) {
            this.model.getPlayerBoxes().add(new PlayerBox(bet));
            this.model.setBankroll(-bet);
        }
        this.model.setGameStatus(GameStatus.DEAL);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.model.getPlayerBoxes().size(); i++) {
            result.append(" " + this.model.getPlayerBoxes().get(i).toString());
        }
        return result.toString() + " | " + this.model.getDealerBox().getHand().toString();
    }

    public Box getDealerBox() {
        return this.model.getDealerBox();
    }

    public void setBankroll(int bankroll) {
        this.model.setBankroll(bankroll);
    }
    
    public int getBankroll(){
        return this.model.getBankroll();
    }

    public void fold(final int boxIndex) {
        this.model.getPlayerBoxes().remove(boxIndex);
        this.model.setGameStatus(GameStatus.DETERMINATION);
    }
    
    public void bet(final int boxIndex) {
        final PlayerBox box = this.model.getPlayerBoxes().get(boxIndex);
        box.play();
        this.model.setBankroll(-box.getBet());
        this.model.setGameStatus(GameStatus.DETERMINATION);
    }
    
    public void draw(final int boxIndex, Card... foldCards) {
        final PlayerBox box = this.model.getPlayerBoxes().get(boxIndex);
        this.model.setBankroll(-box.getAnte());
        box.drawCards(foldCards);
        this.model.setGameStatus(GameStatus.DETERMINATION);
    }

    public void draw(final int boxIndex, List<Card> foldCards) {
        final PlayerBox box = this.model.getPlayerBoxes().get(boxIndex);
        this.model.setBankroll(-box.getAnte());
        box.drawCards(foldCards);
        this.model.setGameStatus(GameStatus.DETERMINATION);
    }
    
    public void takeAnte(final int boxIndex) {
        PlayerBox box = this.model.getPlayerBoxes().get(boxIndex);
        this.model.setBankroll(box.getAnte() + box.getBet() + box.getAnte());
        this.model.getPlayerBoxes().remove(boxIndex);
    }

    public void choiceBuyGameForDealer(final int boxIndex) {
        PlayerBox box = this.model.getPlayerBoxes().get(boxIndex);
        this.model.setBankroll(-box.getAnte());
    }
    
    public void buyGame() {
        this.model.getDealerBox().getHand().getCards().remove(0);
        this.model.getDealerBox().addCard(this.model.getCardList().get(0));
        this.model.getCardList().remove(0);
        this.model.getDealerBox().sort();
        Collections.reverse(this.model.getDealerBox().getHand().getCards());
        this.model.setGameStatus(GameStatus.GAME_BOUGHT);
    }




}
