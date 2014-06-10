package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.rulezzz.pkr.core.BoxStatus;
import com.rulezzz.pkr.core.GameType;
import com.rulezzz.pkr.core.Table;

public class GameTest {

    private Table table;

    @Before
    public void setUp() {
        table = new Table(GameType.FIVECARD);
    }

    @Test
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
    }

    @Test
    public void testCheckingBoxStatus() throws IOException {
        table.makeBets(10);
        table.deal();
        table.getBox(0).fold();
        table.checkBoxStatus();
        assertEquals(0, table.getBoxes().size());
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
        boxChoises.add("bet");
        boxChoises.add("fold");
        table.handleDraws(boxChoises);
        assertEquals(2, table.getBoxes().size());
        assertEquals(20, table.getBox(0).getBet());
        assertEquals(30, table.getBox(1).getBet());
        assertEquals(BoxStatus.BET, table.getBox(0).getStatus());
        assertEquals(BoxStatus.BET, table.getBox(1).getStatus());

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
    public void testParseChoise() {
        LinkedList<Boolean> result = new LinkedList<Boolean>();
        result.add(true);
        result.add(false);
        result.add(true);
        result.add(false);
        result.add(true);
        assertEquals(result, table.parseChoise("draw 10101"));
    }

}
