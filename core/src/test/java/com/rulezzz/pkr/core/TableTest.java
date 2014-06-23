package com.rulezzz.pkr.core;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class TableTest {

    private Table table;
    
    @Before
    public void setUp() {
        table = new Table(GameType.FIVECARD);
    }
    
    @Test
    public void testBankRoll() {
        table.setBankroll(1000);
        assertEquals(1000, table.getBankroll());
    }
    
    @Test (expected = IOException.class)
    public void testDealWithIncorrectStatus() throws IOException {
        table = new Table(GameType.OMAHA);
        assertEquals(GameStatus.BETS, table.getGameStatus());
        table.deal();
    }
    
    @Test (expected = IllegalStateException.class)
    public void testHandleDrawsWithIncorrectStatus() throws IOException{
        table = new Table(GameType.OMAHA);
        table.makeBets(20, 25);
        LinkedList<String> boxChoises = new LinkedList<String>();
        boxChoises.add("fold");
        boxChoises.add("bet");
        boxChoises.add("bet");
        table.handleDraws(boxChoises);
    }


/*    @Test
    public void testDeal() throws IOException {
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
    }*/

    @Test
    public void testCheckingBoxStatus() throws IOException {
        table.makeBets(10, 25);
        table.deal();
        table.getBox(0).fold();
        table.getBox(1).play();
        table.checkBoxStatus();
        assertEquals(1, table.getBoxes().size());
        assertEquals(BoxStatus.BET, table.getBoxes().get(0).getStatus());
    }

    @Test
    public void testHandleDrawsChooseFoldBetBet() throws IOException {
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
    public void testHandleDrawsAndDetermination() throws IOException {
        table.makeBets(10, 20, 25);
        table.deal();
        LinkedList<String> boxChoises = new LinkedList<String>();
        boxChoises.add("bet");
        boxChoises.add("draw 11000");
        boxChoises.add("draw 11001");
        table.handleDraws(boxChoises);
        assertEquals(BoxStatus.BET, table.getBox(0).getStatus());
        assertEquals(BoxStatus.DRAW, table.getBox(1).getStatus());
        assertEquals(BoxStatus.DRAW, table.getBox(2).getStatus());
        assertEquals(2, table.getBox(1).getHand().getCards().size());
        assertEquals(3, table.getBox(2).getHand().getCards().size());
        table.handleDetermination();
        assertEquals(5, table.getBox(1).getHand().getCards().size());
        assertEquals(3, table.getBox(2).getHand().getCards().size());
        
    }
    
    @Test (expected = IllegalStateException.class)
    public void testIncorrectDetermination() {
        table.makeBets(10, 20, 25);
        table.handleDetermination();
    }
    
    @Test (expected = IllegalStateException.class)
    public void testHandleDrawsIncorrectChoise() throws IOException {
        table.makeBets(10);
        table.deal();
        LinkedList<String> boxChoises = new LinkedList<String>();
        boxChoises.add("choise");
        table.handleDraws(boxChoises);
    }
}
