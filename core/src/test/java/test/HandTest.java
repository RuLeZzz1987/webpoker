package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.rulezzz.pkr.core.Card;
import com.rulezzz.pkr.core.CardSuit;
import com.rulezzz.pkr.core.GameMath;
import com.rulezzz.pkr.core.GameType;
import com.rulezzz.pkr.core.Hand;
import com.rulezzz.pkr.core.PlayerBox;

public class HandTest {
	
	private Hand hand;
	
	@Before
	public void setUp() {
		hand = new Hand();
	}
	

	
	@Test
	public void testRemoveCardFromHand() {
		hand = new Hand(GameType.FIVECARD, new Card(CardSuit.CLUBS, 'A', 14) , new Card(CardSuit.HEART, 'K', 13),
				new Card(CardSuit.SPADES, '6', 6), new Card(CardSuit.DIAMOND, '3', 3), new Card(CardSuit.DIAMOND, '2', 2) );
		hand.removeCardByMask(1,0,1,0,1);
		assertEquals(true, hand.compareTo(new Hand(GameType.UNKNOWN, new Card(CardSuit.DIAMOND, '3', 3), new Card(CardSuit.HEART, 'K', 13))));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testHandCreate(){
		hand = new Hand(GameType.FIVECARD, new Card(CardSuit.CLUBS, 'A', 14) , new Card(CardSuit.HEART, 'K', 13),
				new Card(CardSuit.SPADES, '6', 6), new Card(CardSuit.DIAMOND, '3', 3) );
		hand = new Hand(GameType.TEXAS, new Card(CardSuit.CLUBS, 'A', 14) , new Card(CardSuit.HEART, 'K', 13),
				new Card(CardSuit.SPADES, '6', 6), new Card(CardSuit.DIAMOND, '3', 3) );
	}
	
	@Test
	public void testPermutation() {
		hand.add(new Card(CardSuit.CLUBS, 'A', 14));
		hand.add(new Card(CardSuit.HEART, 'K', 13));
		hand.add(new Card(CardSuit.SPADES, '6', 6));
		hand.add(new Card(CardSuit.DIAMOND, '3', 3));
		hand.add(new Card(CardSuit.CLUBS, '2', 2));		
		GameMath.generateCombinations(hand.getCards(), 3);
	}
	
	@Test
	public void testIsFlush() {
		hand.add(new Card(CardSuit.CLUBS, 'A', 14));
		hand.add(new Card(CardSuit.CLUBS, 'K', 13));
		hand.add(new Card(CardSuit.CLUBS, '6', 6));
		hand.add(new Card(CardSuit.CLUBS, '3', 3));
		hand.add(new Card(CardSuit.CLUBS, '2', 2));
		assertEquals("6 14 13 6 3 2 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testKickersList() {
		hand.add(new Card(CardSuit.CLUBS, 'A', 14));
		hand.add(new Card(CardSuit.DIAMOND, 'A', 14));
		hand.add(new Card(CardSuit.CLUBS, '6', 6));
		hand.add(new Card(CardSuit.CLUBS, '3', 3));
		hand.add(new Card(CardSuit.CLUBS, '2', 2));
		int[] check = { 2, 14, 14, 6, 3, 2 };
		for ( int i=0; i < hand.getCombinationOnFiveCards().getKickers().length; i++) {
			assertEquals(check[i], hand.getCombinationOnFiveCards().getKickers()[i]);
		}
	}
	

	@Test
	public void testCombinationDNQ() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'J'));
		hand.add(new Card(CardSuit.CLUBS, '6'));
		hand.add(new Card(CardSuit.CLUBS, '3'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("0 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationAK() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'K'));
		hand.add(new Card(CardSuit.CLUBS, '6'));
		hand.add(new Card(CardSuit.CLUBS, '3'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("1 6 3 2 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationPairAABCD() {
		hand.add(new Card(CardSuit.CLUBS, 'K'));
		hand.add(new Card(CardSuit.DIAMOND, 'K'));
		hand.add(new Card(CardSuit.CLUBS, '7'));
		hand.add(new Card(CardSuit.CLUBS, '5'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("2 13 13 7 5 2 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationPairABBCD() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'J'));
		hand.add(new Card(CardSuit.CLUBS, 'J'));
		hand.add(new Card(CardSuit.CLUBS, '3'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("2 11 11 14 3 2 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationPairABCCD() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'J'));
		hand.add(new Card(CardSuit.CLUBS, '6'));
		hand.add(new Card(CardSuit.HEART, '6'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("2 6 6 14 11 2 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationPairABCDD() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'J'));
		hand.add(new Card(CardSuit.CLUBS, '6'));
		hand.add(new Card(CardSuit.HEART, '2'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("2 2 2 14 11 6 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationTwoPairsAABBC() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'A'));
		hand.add(new Card(CardSuit.CLUBS, '6'));
		hand.add(new Card(CardSuit.HEART, '6'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("3 14 14 6 6 2 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationTwoPairsAABCC() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'A'));
		hand.add(new Card(CardSuit.CLUBS, '7'));
		hand.add(new Card(CardSuit.HEART, '6'));
		hand.add(new Card(CardSuit.CLUBS, '6'));
		assertEquals("3 14 14 6 6 7 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationTwoPairsABBCC() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, '7'));
		hand.add(new Card(CardSuit.CLUBS, '7'));
		hand.add(new Card(CardSuit.HEART, '6'));
		hand.add(new Card(CardSuit.CLUBS, '6'));
		assertEquals("3 7 7 6 6 14 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationSetAAABC() {
		hand.add(new Card(CardSuit.CLUBS, '7'));
		hand.add(new Card(CardSuit.DIAMOND, '7'));
		hand.add(new Card(CardSuit.SPADES, '7'));
		hand.add(new Card(CardSuit.CLUBS, '5'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("4 7 7 7 5 2 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationSetABBBC() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'J'));
		hand.add(new Card(CardSuit.CLUBS, 'J'));
		hand.add(new Card(CardSuit.SPADES, 'J'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("4 11 11 11 14 2 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationSetABCCC() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'J'));
		hand.add(new Card(CardSuit.DIAMOND, '2'));
		hand.add(new Card(CardSuit.SPADES, '2'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("4 2 2 2 14 11 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationFullHouseAAACC() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'A'));
		hand.add(new Card(CardSuit.HEART, 'A'));
		hand.add(new Card(CardSuit.SPADES, '2'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("7 14 2 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationFullHouseAACCC() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'A'));
		hand.add(new Card(CardSuit.SPADES, '2'));
		hand.add(new Card(CardSuit.HEART, '2'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("7 2 14 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationFourOfaKindAAAAB() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, 'A'));
		hand.add(new Card(CardSuit.HEART, 'A'));
		hand.add(new Card(CardSuit.SPADES, 'A'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("8 14 2 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationFourOfaKindABBBB() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, '2'));
		hand.add(new Card(CardSuit.HEART, '2'));
		hand.add(new Card(CardSuit.SPADES, '2'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("8 2 14 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationStraight() {
		hand.add(new Card(CardSuit.CLUBS, 'K'));
		hand.add(new Card(CardSuit.DIAMOND, 'Q'));
		hand.add(new Card(CardSuit.HEART, 'J'));
		hand.add(new Card(CardSuit.SPADES, 'T'));
		hand.add(new Card(CardSuit.CLUBS, '9'));
		assertEquals("5 13 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationWheelStraight() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.DIAMOND, '5'));
		hand.add(new Card(CardSuit.HEART, '4'));
		hand.add(new Card(CardSuit.SPADES, '3'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("5 5 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationStraightFlush() {
		hand.add(new Card(CardSuit.CLUBS, 'K'));
		hand.add(new Card(CardSuit.CLUBS, 'Q'));
		hand.add(new Card(CardSuit.CLUBS, 'J'));
		hand.add(new Card(CardSuit.CLUBS, 'T'));
		hand.add(new Card(CardSuit.CLUBS, '9'));
		assertEquals("9 13 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationWheelStraightFlush() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.CLUBS, '5'));
		hand.add(new Card(CardSuit.CLUBS, '4'));
		hand.add(new Card(CardSuit.CLUBS, '3'));
		hand.add(new Card(CardSuit.CLUBS, '2'));
		assertEquals("9 5 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
	@Test
	public void testCombinationRoyalFlush() {
		hand.add(new Card(CardSuit.CLUBS, 'A'));
		hand.add(new Card(CardSuit.CLUBS, 'K'));
		hand.add(new Card(CardSuit.CLUBS, 'Q'));
		hand.add(new Card(CardSuit.CLUBS, 'J'));
		hand.add(new Card(CardSuit.CLUBS, 'T'));
		assertEquals("10 ", hand.getCombinationOnFiveCards().getCombCode());
	}
	
    @Test
    public void testDrawCards() throws IOException {
        hand.add(new Card(CardSuit.CLUBS, 'A'));
        hand.add(new Card(CardSuit.DIAMOND, 'J'));
        hand.add(new Card(CardSuit.CLUBS, 'J'));
        hand.add(new Card(CardSuit.CLUBS, '3'));
        hand.add(new Card(CardSuit.CLUBS, '2'));
        PlayerBox box = new PlayerBox(hand, 10);
        LinkedList<Boolean> holdList = new LinkedList<Boolean>();
        holdList.add(false);
        holdList.add(true);
        holdList.add(true);
        holdList.add(false);
        holdList.add(false);
        box.drawCards(holdList);
        assertEquals(new Card(CardSuit.DIAMOND, 'J'), box.getHandHigheness(0).getCards().get(0));
        assertEquals(new Card(CardSuit.CLUBS, 'J'), box.getHandHigheness(0).getCards().get(1));
    }
}
