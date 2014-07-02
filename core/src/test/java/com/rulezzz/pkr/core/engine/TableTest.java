package com.rulezzz.pkr.core.engine;

import static org.junit.Assert.assertEquals;

import com.rulezzz.pkr.core.basestructures.Box.BoxStatus;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.gameengine.Table;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TableTest {

    private Table table;
    
    @Before
    public void setUp() {
        table = new Table();
    }
    
    @Test
    public void testFold() { 
        table.makeBets(10, 30);
        table.deal();
        
        assertEquals(2, table.getBoxes().size());
        
        int boxIndex = 1;
        table.fold(boxIndex);
        
        assertEquals(1, table.getBoxes().size());
    }
    
    @Test
    public void testBet() { 
        table.makeBets(10, 30, 50);
        table.deal();
        
        int boxIndex = 2;
        table.bet(boxIndex);
        
        assertEquals(100, table.getBoxes().get(boxIndex).getBet());
        assertEquals(BoxStatus.BET, table.getBoxes().get(boxIndex).getStatus());
    }
    
    @Test
    public void testDraw() { 
        table.makeBets(10);
        table.deal();
        
        int boxIndex = 0;
        List<Card> cards = table.getBoxes().get(boxIndex).getHand().getCards();
        table.draw(boxIndex, cards.get(0), cards.get(3));
        
        assertEquals(BoxStatus.DRAW, table.getBoxes().get(boxIndex).getStatus());
        assertEquals(3, table.getBoxes().get(boxIndex).getHand().getCards().size());
    }
}
