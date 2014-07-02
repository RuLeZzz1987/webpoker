package com.rulezzz.pkr.core.gameengine;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.rulezzz.pkr.core.basestructures.Box;
import com.rulezzz.pkr.core.basestructures.BoxStatus;
import com.rulezzz.pkr.core.basestructures.Hand;
import com.rulezzz.pkr.core.basestructures.PlayerBox;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.combination.ICombination;
import com.rulezzz.pkr.core.datamodels.FiveCardDataModel;

public class Table implements Serializable {

    private static final long serialVersionUID = -3327856908669194148L;

    private FiveCardDataModel model = new FiveCardDataModel();
    private static final String DRAW = "draw";

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
                b.setHand(model.getCardList().get(0));
                model.getCardList().remove(0);
            }
            model.getDealerBox().setHand(model.getCardList().get(0));
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
        if (model.getDealerBox().getHand().getHandICombination().getHighness() != 0) {
            ICombination dealerCombo = model.getDealerBox().getHand().getHandICombination();
            for (PlayerBox box : model.getPlayerBoxes()) {
                int compareResult = dealerCombo.compareTo(box.getHand().getHandICombination());
                switch ( compareResult ) {
                    case 0 : {
                        model.setBankroll(box.getAnte() + box.getBet());
                        break;
                    }
                    case -1 : {
                        model.setBankroll(box.getAnte() + box.getPayment());
                        break;
                    }
                    default : {
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

    public void handleDraws(List<String> boxChoise) {
        if (model.getGameStatus() != GameStatus.DRAWS) {
            throw new IllegalStateException(
                    "game status don't match this operation. Expected: DRAWS. Actual: "
                            + model.getGameStatus());
        }
        Iterator<PlayerBox> boxIterator = model.getPlayerBoxes().iterator();
        Iterator<String> choiseIterator = boxChoise.iterator();
        while (boxIterator.hasNext()) {
            String choise = choiseIterator.next();
            PlayerBox currentBox = boxIterator.next();
            switch (choise) {
                case "fold": {
                    boxIterator.remove();
                    break;
                }
                case "bet": {
                    
                    currentBox.play();
                    model.setBankroll(-currentBox.getBet());
                    break;
                }
                case "buy": {
                    currentBox.buyCard();
                    model.setBankroll(-currentBox.getAnte());
                    break;
                }
                default: {
                    if (choiseDrawCheck(choise)) {
                        model.setBankroll(-currentBox.getAnte());
                        currentBox.drawCards(parseChoise(choise));
                    } else {
                        throw new IllegalStateException("unknown box choise");
                    } 
                    break;
                }
            }
        }
        model.setGameStatus(GameStatus.DETERMINATION);
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
                box.setCardsAfterDraw(generateDrawCardList(box
                        .getCountOfNeededCards()));
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
    
    private Boolean choiseDrawCheck(String choise) {
        return choise.substring(0, DRAW.length()).equals(DRAW);
    }

    private List<Boolean> parseChoise(String choise) {
        String buf = choise;
        LinkedList<Boolean> result = new LinkedList<Boolean>();
        buf = choise.substring(DRAW.length() + 1);
        for (int i = 0; i < buf.length(); i++) {
            if (buf.charAt(i) == '1') {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

    public void makeBets(int... bets) {
        for (int i = 0; i < bets.length; i++) {
            model.getPlayerBoxes().add(new PlayerBox(bets[i]));
            model.setBankroll(-bets[i]);
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

}
