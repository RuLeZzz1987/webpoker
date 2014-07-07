package com.rulezzz.pkr.core.base.structures;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rulezzz.pkr.core.base.structures.PlayerBox;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.card.CardSuit;
public class BoxTest {

	@Test
	public void testSetHandByListCards() {
		PlayerBox box = new PlayerBox();
		List<Card> cardList = new LinkedList<Card>();
		cardList.add(new Card(CardSuit.CLUBS, 'A'));
		cardList.add(new Card(CardSuit.HEART, '2'));
		cardList.add(new Card(CardSuit.DIAMOND, 'T'));
		cardList.add(new Card(CardSuit.SPADES, '9'));
		box.addCards(cardList);
		assertEquals(cardList, box.getHand().getCards());
	}
	
}
