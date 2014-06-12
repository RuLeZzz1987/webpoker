package com.rulezzz.pkr.core;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class TableTest {

    private Table table;
    
    @Test (expected = IOException.class)
    public void testDealWithIncorrectStatus() throws IOException {
        table = new Table(GameType.OMAHA);
        assertEquals(GameStatus.BETS, table.getGameStatus());
        table.deal();
    }
    
    @Test
    public void testDealDifferentGameTypes() throws IOException {
        table = new Table(GameType.OMAHA);
        table.makeBets(20, 25);
        table.deal();
        assertEquals(GameStatus.DRAWS, table.getGameStatus());
        assertEquals(2, table.getBoxes().size());
        assertEquals(4, table.getBoxes().get(0).getHand().getCards().size());
        table = new Table(GameType.TEXAS);
        table.makeBets(20, 25);
        table.deal();
        assertEquals(2, table.getBoxes().get(0).getHand().getCards().size());
        table = new Table(GameType.FIVECARD);
        table.makeBets(20, 25);
        table.deal();
        assertEquals(5, table.getBoxes().get(0).getHand().getCards().size());       
    }
    
    @Test (expected = IllegalStateException.class)
    public void testDealUnknownGameType() throws IOException {
        table = new Table(GameType.UNKNOWN);
        table.makeBets(20, 25);
        table.deal();
    }
    


    @Test
    public void testDeal() throws IOException {
        table = new Table(GameType.FIVECARD);
        table.makeBets(10, 15);
        table.deal();
        if (table.getBoxes().get(0).getHand().getCombinationOnFiveCards()
                .getCombCode().startsWith("0")) {
            table.getBoxes().get(0).fold();
        } else
            table.getBoxes().get(0).play();
        if (table.getBoxes().get(1).getHand().getCombinationOnFiveCards()
                .getCombCode().startsWith("0")) {
            table.getBoxes().get(1).fold();
        } else
            table.getBoxes().get(1).play();
        table.calculateDealResult();
        System.out.println(table.toString());
    }

    @Test
    public void testCheckingBoxStatus() throws IOException {
        table = new Table(GameType.FIVECARD);
        table.makeBets(10);
        table.deal();
        table.getBox(0).fold();
        table.checkBoxStatus();
        assertEquals(0, table.getBoxes().size());
    }

    @Test
    public void testHandleDrawsChooseFoldBetBet() throws IOException {
        table = new Table(GameType.FIVECARD);
        table.makeBets(10, 15, 25);
        table.deal();
        LinkedList<String> boxChoises = new LinkedList<String>();
        boxChoises.add("fold");
        boxChoises.add("bet");
        boxChoises.add("bet");
        table.handleDraws(boxChoises);
        assertEquals(2, table.getBoxes().size());
        assertEquals(30, table.getBox(0).getBet());
        assertEquals(50, table.getBox(1).getBet());
        assertEquals(BoxStatus.BET, table.getBox(0).getStatus());
        assertEquals(BoxStatus.BET, table.getBox(1).getStatus());

    }

    @Test
    public void testHandleDrawsChooseBetFoldBet() throws IOException {
        table = new Table(GameType.FIVECARD);
        table.makeBets(10, 15, 25);
        table.deal();
        LinkedList<String> boxChoises = new LinkedList<String>();
        boxChoises.add("bet");
        boxChoises.add("fold");
        boxChoises.add("bet");
        table.handleDraws(boxChoises);
        assertEquals(2, table.getBoxes().size());
        assertEquals(20, table.getBox(0).getBet());
        assertEquals(50, table.getBox(1).getBet());
        assertEquals(BoxStatus.BET, table.getBox(0).getStatus());
        assertEquals(BoxStatus.BET, table.getBox(1).getStatus());

    }
    
    @Test
    public void testHandleDrawsChooseBetBetFold() throws IOException {
        table = new Table(GameType.FIVECARD);
        table.makeBets(10, 15, 25);
        table.deal();
        LinkedList<String> boxChoises = new LinkedList<String>();
        boxChoises.add("bet");
        boxChoises.add("buy");
        boxChoises.add("fold");
        table.handleDraws(boxChoises);
        assertEquals(2, table.getBoxes().size());
        assertEquals(20, table.getBox(0).getBet());
        assertEquals(BoxStatus.BET, table.getBox(0).getStatus());
        assertEquals(BoxStatus.BUY_CARD, table.getBox(1).getStatus());

    }
    
    @Test
    public void testHandleDrawsChooseBetDrawFold() throws IOException {
        table = new Table(GameType.FIVECARD);
        table.makeBets(10, 15, 25);
        table.deal();
        LinkedList<String> boxChoises = new LinkedList<String>();
        boxChoises.add("bet");
        boxChoises.add("draw 11001");
        boxChoises.add("fold");
        table.handleDraws(boxChoises);
        assertEquals(2, table.getBoxes().size());
        assertEquals(20, table.getBox(0).getBet());
        assertEquals(BoxStatus.BET, table.getBox(0).getStatus());
        assertEquals(BoxStatus.DRAW, table.getBox(1).getStatus());
        assertEquals(3, table.getBox(1).getHand().getCards().size());
    }
    
    @Test
    public void testParseChoise() {
        table = new Table(GameType.FIVECARD);
        LinkedList<Boolean> result = new LinkedList<Boolean>();
        result.add(true);
        result.add(false);
        result.add(true);
        result.add(false);
        result.add(true);
        assertEquals(result, table.parseChoise("draw 10101"));
    }
    
    @Test
    public void testHandleDrawsChooseDraw() throws IOException {
        table = new Table(GameType.FIVECARD);
        table.makeBets(10);
        table.deal();
        LinkedList<String> boxChoises = new LinkedList<String>();
        boxChoises.add("draw 11001");
        table.handleDraws(boxChoises);
        assertEquals(BoxStatus.DRAW, table.getBox(0).getStatus());
        assertEquals(3, table.getBox(0).getHand().getCards().size());
    }
}
