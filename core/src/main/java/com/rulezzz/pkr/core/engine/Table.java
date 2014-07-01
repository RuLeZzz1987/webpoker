package com.rulezzz.pkr.core.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.rulezzz.pkr.core.basestructures.Card;
import com.rulezzz.pkr.core.basestructures.Deck;
import com.rulezzz.pkr.core.combination.ICombination;

public class Table implements Serializable {

    private static final long serialVersionUID = -3327856908669194148L;
    private int bankroll;
    private Box dealerBox = new Box();
    private List<PlayerBox> playerBoxes = new ArrayList<PlayerBox>();
    private Deck deck = new Deck();
    private List<Card> cardList = deck.getDeck();
    private GameStatus gameStatus;
    private List<Integer> defaultBets = new ArrayList<Integer>();
    private static final String DRAW = "draw";

    public Table() {
        gameStatus = GameStatus.BETS;
    }
    
    public List<Integer> getDefaultBets() {
        return this.defaultBets;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Box getDealerBox(){
        return dealerBox;
    }
    
    public void deal() {
        if (this.gameStatus != GameStatus.DEAL) {
            throw new IllegalStateException("No money, no Cards!");
        }
        int k = Hand.FIVECARD;
        for (int i = 0; i < k; i++) {
            for (PlayerBox b : this.playerBoxes) {
                b.setHand(this.cardList.get(0));
                this.deck.setUsed(this.cardList.get(0));
                this.cardList.remove(0);
            }
            this.dealerBox.setHand(this.cardList.get(0));
            this.deck.setUsed(this.cardList.get(0));
            this.cardList.remove(0);
        }
        for (PlayerBox b : this.playerBoxes) {
            b.sort();
            Collections.reverse(b.getHand().getCards());
            this.defaultBets.add(b.getAnte());
        }
        this.gameStatus = GameStatus.DRAWS;
    }

    public List<PlayerBox> getBoxes() {
        return this.playerBoxes;
    }

    public PlayerBox getBox(int i) {
        return this.playerBoxes.get(i);
    }

    public void calculateDealResult() {
        if (this.dealerBox.getHand().getHandICombination().getHighness() != 0) {
            ICombination dealerCombo = this.dealerBox.getHand().getHandICombination();
            for (PlayerBox box : this.playerBoxes) {
                int compareResult = dealerCombo.compareTo(box.getHand().getHandICombination());
                switch ( compareResult ) {
                    case 0 : {
                        this.bankroll += box.getAnte() + box.getBet();
                        break;
                    }
                    case -1 : {
                        this.bankroll += box.getAnte() + box.getPayment();
                        break;
                    }
                    default : {
                    }
                }
            }
            this.gameStatus = GameStatus.SHOWDOWN;
        } else {
            if (this.playerBoxes.size() != 0) {
                this.gameStatus = GameStatus.DEALER_DNQ;
            } else {
                this.gameStatus = GameStatus.SHOWDOWN;
            }
        }
    }

    public void handleDraws(List<String> boxChoise) {
        if (gameStatus != GameStatus.DRAWS) {
            throw new IllegalStateException(
                    "game status don't match this operation. Expected: DRAWS. Actual: "
                            + gameStatus);
        }
        Iterator<PlayerBox> boxIterator = playerBoxes.iterator();
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
                    this.bankroll -= currentBox.getBet();
                    break;
                }
                case "buy": {
                    currentBox.buyCard();
                    this.bankroll -= currentBox.getAnte();
                    break;
                }
                default: {
                    if (choiseDrawCheck(choise)) {
                        this.bankroll -= currentBox.getAnte();
                        currentBox.drawCards(parseChoise(choise));
                    } else {
                        throw new IllegalStateException("unknown box choise");
                    } 
                    break;
                }
            }
        }
        this.gameStatus = GameStatus.DETERMINATION;
    }
    
    public boolean checkAllDeterminated(){
            for (PlayerBox box : this.playerBoxes) {
                if ( box.getStatus() != BoxStatus.BET) {
                    return false;
                }
            }
            this.dealerBox.sort();
            Collections.reverse(this.dealerBox.getHand().getCards());
            return true;
    }

    public void handleDetermination() {
        for (PlayerBox box : playerBoxes) {
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
            result.add(cardList.get(0));
            deck.setUsed(cardList.get(0));
            cardList.remove(0);
        }
        return result;
    }
    
    private Boolean choiseDrawCheck(String choise) {
        if (choise.substring(0, DRAW.length()).equals(DRAW)) { 
            return true;
        } else {
            return false;
        }
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
            playerBoxes.add(new PlayerBox(bets[i]));
        }
        gameStatus = GameStatus.DEAL;
    }

    public void checkBoxStatus() {
        Iterator<PlayerBox> iter = playerBoxes.iterator();
        while (iter.hasNext()) {
            if (iter.next().getStatus() == BoxStatus.FOLD) {
                iter.remove();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < playerBoxes.size(); i++) {
            result.append(" " + playerBoxes.get(i).toString());
        }
        return result.toString() + " | " + dealerBox.getHand().toString();
    }

    public int getBankroll() {
        return bankroll;
    }

    public void setBankroll(int bankroll) {
        this.bankroll = bankroll;
    }
}
