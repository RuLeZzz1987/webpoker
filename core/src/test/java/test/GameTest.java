package test;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

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
		if (table.getBoxes().get(0).getHand().getCombinationOnFiveCards().getCombCode().startsWith("0")) {
			table.getBoxes().get(0).fold();
		} 
		else	table.getBoxes().get(0).play();
		if (table.getBoxes().get(1).getHand().getCombinationOnFiveCards().getCombCode().startsWith("0")) {
			table.getBoxes().get(1).fold();
		} 
		else	table.getBoxes().get(1).play();
		table.calculateDealResult();
		System.out.println(table.toString());
	}

}
