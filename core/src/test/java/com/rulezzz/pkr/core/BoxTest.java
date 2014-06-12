package com.rulezzz.pkr.core;

import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
public class BoxTest {

	@Test
	public void testSetHandByListCards() {
		PlayerBox box = new PlayerBox();
		List<Card> cardList = new LinkedList<Card>();
		cardList.add(new Card(CardSuit.CLUBS, 'A'));
		cardList.add(new Card(CardSuit.HEART, '2'));
		cardList.add(new Card(CardSuit.DIAMOND, 'T'));
		cardList.add(new Card(CardSuit.SPADES, '9'));
		box.setHand(cardList);
		assertEquals(cardList, box.getHand().getCards());
	}
	
}
