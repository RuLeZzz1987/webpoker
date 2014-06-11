package com.rulezzz.pkr.core;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.rulezzz.pkr.core.Card;
import com.rulezzz.pkr.core.CardSuit;
import com.rulezzz.pkr.core.Deck;
import com.rulezzz.pkr.core.GameType;
import com.rulezzz.pkr.core.PlayerBox;
import com.rulezzz.pkr.core.Table;

public class CardTest {

	@Test
	public void testShowDeck() {
		List<Card> deck = new Deck().getDeck();
		for(Card i : deck)
		{
			System.out.print(i.toString()+" ");
		}
	}
	
	@Test
	public void testIsSameCard() {
		Card card1 = new Card(CardSuit.CLUBS, 'A');
		Card card2 = new Card(CardSuit.CLUBS, 'A');
		assertEquals(true, card1.equals(card2));
		card2 = new Card(CardSuit.DIAMOND, 'A');
		assertEquals(false, card1.equals(card2));
		card2 = new Card(CardSuit.CLUBS, 'K');
		assertEquals(false, card1.equals(card2));
		
	}
	
	@Test
	public void testCreateCard() {
		Card card1 = new Card(CardSuit.CLUBS, 'A');
		assertEquals(14, card1.getScore());
		card1 = new Card(CardSuit.CLUBS, 'K');
		assertEquals(13, card1.getScore());
		card1 = new Card(CardSuit.CLUBS, 'Q'); 
		assertEquals(12, card1.getScore());
		card1 = new Card(CardSuit.CLUBS, 'J');
		assertEquals(11, card1.getScore());
		card1 = new Card(CardSuit.CLUBS, 'T');
		assertEquals(10, card1.getScore());
		for (int i=2; i<=9; i++) {
			card1 = new Card(CardSuit.CLUBS, (char)(i+48));
			assertEquals(i, card1.getScore()); 
		}
	}
	@Test
	public void testShowDeal() {
		Table table = new Table(GameType.FIVECARD);
		table.deal(2);
		List<PlayerBox> boxList = table.getBoxes();
		for (PlayerBox b: boxList) {
			b.sort();
			b.getHand().getCombinationOnFiveCards();
			System.out.println(b.toString() +" "+b.getHand().getCombinationOnFiveCards());
		}
		table.sort();
		System.out.println(table.getHand().toString() + " " + table.getHand().getCombinationOnFiveCards());
	}
	


}
