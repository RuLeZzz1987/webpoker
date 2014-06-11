package com.rulezzz.pkr.core;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Table extends Box implements Serializable {

    private static final long serialVersionUID = -3327856908669194148L;
    private int bankroll;
    private List<PlayerBox> playerBoxes = new ArrayList<PlayerBox>();
    private Deck deck = new Deck();
    private List<Card> cardList = deck.getDeck();
    private GameType gameType;
    private GameStatus gameStatus;

    public Table(GameType gameType) {
        this.gameType = gameType;
        gameStatus = GameStatus.BETS;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void deal() throws IOException {
        if (gameStatus != GameStatus.DEAL) {
            throw new IOException("No money, no Cards!");
        } else {
            deal(playerBoxes.size());
        }
    }

    public void deal(int boxCount) {
        int k = Hand.FIVECARD;
        switch (gameType) {
        case FIVECARD: {
            k = Hand.FIVECARD;
            break;
        }
        case OMAHA: {
            k = Hand.OMAHA;
            break;
        }
        case TEXAS: {
            k = Hand.TEXAS;
            break;
        }
        default:
            throw new IllegalStateException("Unknown game type");
        }
        for (int i = 0; i < k; i++) {
            for (PlayerBox b : playerBoxes) {
                b.setHand(cardList.get(0));
                deck.setUsed(cardList.get(0));
                cardList.remove(0);
            }
            setHand(cardList.get(0));
            deck.setUsed(cardList.get(0));
            cardList.remove(0);
        }
        for (PlayerBox b : playerBoxes) {
            b.sort();
        }
        gameStatus = GameStatus.DRAWS;
    }

    public List<PlayerBox> getBoxes() {
        return this.playerBoxes;
    }

    public PlayerBox getBox(int i) {
        return this.playerBoxes.get(i);
    }

    public void calculateDealResult() {
        if (getHand().getCombinationOnFiveCards().getHighness() != 0
                && gameStatus == GameStatus.DETERMINATION) {
            for (int i = 0; i < playerBoxes.size(); i++) {
                if (playerBoxes.get(i).getStatus().equals(BoxStatus.BET)) {
                    switch (playerBoxes.get(i).getHand()
                            .getCombinationOnFiveCards()
                            .compareTo(getHand().getCombinationOnFiveCards())) {
                    case 1: {

                        break;
                    }
                    case 0: {

                        break;
                    }
                    case -1: {

                        break;
                    }
                    }
                }
            }
        } else {
            gameStatus = GameStatus.GIVE_ANTE;
        }
    }

    public void handleDraws(List<String> boxChoise) {
        if ( gameStatus != GameStatus.DRAWS) {
            throw new IllegalStateException("game status don't match this operation. Expected: DRAWS. Actual: " + gameStatus);
        }
        Iterator<PlayerBox> boxIterator = playerBoxes.iterator();
        Iterator<String> choiseIterator = boxChoise.iterator();
        while ( boxIterator.hasNext() ) {
            String choise = choiseIterator.next();
            switch(choise) {
                case "fold" : {
                    boxIterator.next();
                    boxIterator.remove();
                    break;
                }
                case "bet" : {
                    boxIterator.next().play();
                    break;
                }
                case "buy" : {
                    break;
                }
                default : {
                	if ( choiseDrawCheck(choise) ) {
                		boxIterator.next().drawCards(parseChoise(choise));
                	}
                    break;
                    
                }
            }
        }
        this.gameStatus = GameStatus.DETERMINATION;
    }
    
    public void handleDetermination() {
        if (gameStatus != GameStatus.DETERMINATION) {
            throw new IllegalStateException(
                    "game status don't match this operation. Expected: DETERMINATION. Actual: "
                            + gameStatus);
        }
        for (PlayerBox box : playerBoxes) {
            if (box.getStatus() == BoxStatus.DRAW) {
                List<Card> cards = new LinkedList<Card>();
                for ( int i = 0; i < box.getHand().getStandardCardsCount() - box.getHand().getCards().size(); i++) {
                    cards.add(cardList.get(0));
                    deck.setUsed(cardList.get(0));
                    cardList.remove(0);
                }
                box.getCardsAfterDraw(cards);
            }

        }
    }
    
    
    private Boolean choiseDrawCheck(String choise) {
    	return ( choise.substring(0, "draw".length()).equals("draw")) ? true : false;
    }
    
    public List<Boolean> parseChoise(String choise) {
        String buf = choise;
        LinkedList<Boolean> result = new LinkedList<Boolean>();
        if (choise.substring(0, "draw".length()).equals("draw")) {
            buf = choise.substring(5);
            for (int i = 0; i < buf.length(); i++) {
                if ( buf.charAt(i) == '1' ) {
                    result.add(true);
                } else {
                    result.add(false);
                }
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
        return result.toString() + " | " + getHand().toString();
    }

    public int getBankroll() {
        return bankroll;
    }

    public void setBankroll(int bankroll) {
        this.bankroll = bankroll;
    }
}
