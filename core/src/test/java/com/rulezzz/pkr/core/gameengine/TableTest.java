package com.rulezzz.pkr.core.gameengine;

import static org.junit.Assert.assertEquals;
import static com.rulezzz.pkr.core.combination.samples.ComboSamples.*;

import com.rulezzz.pkr.core.base.structures.Box.BoxStatus;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.combination.Straight;
import com.rulezzz.pkr.core.gameengine.Table;
import com.rulezzz.pkr.core.statuses.GameStatus;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TableTest {

    private Table table;
    
    @Before
    public void setUp() {
        table = new Table();
    }
    
    @Test
    public void testBuy6Card() {
        table.setBankroll(1000);
        table.makeBets(10);
        table.deal();
        table.getBox(0).setHand(getStraight());
        table.getDealerBox().setHand(getPairAABCD());
        
        assertEquals(Straight.class, table.getBox(0).getHand().getHandAbstractCombination().getClass());
        
        table.buyCard(0);
        table.handleDetermination();
        
        assertEquals(6, table.getBox(0).getHand().getCards().size());
        
        table.getBox(0).setHand(getLongStraight());
        
        assertEquals(Straight.class, table.getBox(0).getHand().getHandAbstractCombination().getClass());
    }
    
    @Test
    public void testDefaultBets(){
        table.makeBets(10, 15);
        table.deal();
        
        assertEquals(2, table.getDefaultBets().size());
        assertEquals(10, table.getDefaultBets().get(0).intValue());
        assertEquals(15, table.getDefaultBets().get(1).intValue());
    }
    
    @Test
    public void testBoxDraw(){
        table.updateDeck();
        table.setBankroll(1000);
        table.makeBets(10);
        table.deal();
        
        List<Card> foldCards = new ArrayList<Card>();
        foldCards.add(table.getBox(0).getHand().getCards().get(2));
        foldCards.add(table.getBox(0).getHand().getCards().get(3));
        foldCards.add(table.getBox(0).getHand().getCards().get(4));
        
        table.draw(0, foldCards);
        
        assertEquals(2, table.getBox(0).getHand().getCards().size());
        assertEquals(980, table.getBankroll());
        assertEquals(BoxStatus.DRAW, table.getBox(0).getStatus());
        
        table.handleDetermination();
        
        assertEquals(5, table.getBox(0).getHand().getCards().size());
        
        table.bet(0);
        table.handleDetermination();
        
        assertEquals(BoxStatus.BET, table.getBox(0).getStatus());
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
    
    @Test
    public void testCalculateDealResultDealerQualify() {
        table.setBankroll(1000);
        table.makeBets(10, 15, 20);
        table.deal();
        table.getBox(0).getHand().getCards().clear();
        table.getBox(1).getHand().getCards().clear();
        table.getBox(2).getHand().getCards().clear();
        table.getDealerBox().getHand().getCards().clear();
        table.getBox(0).setHand(getPairAABCD());
        table.getBox(1).setHand(getFullHouseAAABB());
        table.getBox(2).setHand(getStraight());
        table.getDealerBox().setHand(getStraight());
        table.bet(0);
        table.bet(1);
        table.bet(2);
        table.calculateDealResult();
        
        assertEquals(1180, table.getBankroll());
        assertEquals(GameStatus.SHOWDOWN, table.getGameStatus());
    }
    
    @Test
    public void testCalculateDealResultDealerDNQandTakeAnte() {
        table.setBankroll(1000);
        table.makeBets(10);
        table.deal();
        table.getBox(0).getHand().getCards().clear();
        table.getDealerBox().getHand().getCards().clear();
        table.getBox(0).setHand(getPairAABCD());
        table.getDealerBox().setHand(getDoesntQualifyOne());
        table.bet(0);
        table.calculateDealResult();
        
        assertEquals(GameStatus.DEALER_DNQ, table.getGameStatus());
        
        table.takeAnte(0);
        table.calculateDealResult();
        
        assertEquals(GameStatus.SHOWDOWN, table.getGameStatus());
        assertEquals(1010, table.getBankroll());
    }
    
    @Test
    public void testCalculateDealResultDealerDNQandBuyGameDealerDNQagain() {
        table.setBankroll(1000);
        table.makeBets(10);
        table.deal();
        table.getBox(0).getHand().getCards().clear();
        table.getDealerBox().getHand().getCards().clear();
        table.getBox(0).setHand(getStraight());
        table.getDealerBox().setHand(getDoesntQualifyOne());
        table.bet(0);
        table.calculateDealResult();
        table.choiceBuyGameForDealer(0);
        
        assertEquals(960, table.getBankroll());
        
        table.buyGame();
        
        assertEquals(GameStatus.GAME_BOUGHT, table.getGameStatus());
        assertEquals(5, table.getDealerBox().getHand().getCards().size());
        
        table.getDealerBox().getHand().getCards().clear();
        table.getDealerBox().setHand(getDoesntQualifyOne());
        table.calculateDealResult();
        
        assertEquals(990, table.getBankroll());
        assertEquals(GameStatus.SHOWDOWN, table.getGameStatus());
        
    }
    
    @Test
    public void testCalculateDealResultDealerDNQandBuyGameThenDealerQualify() {
        table.setBankroll(1000);
        table.makeBets(10, 20);
        table.deal();
        table.getBox(0).getHand().getCards().clear();
        table.getDealerBox().getHand().getCards().clear();
        table.getBox(0).setHand(getStraight());
        table.getDealerBox().setHand(getDoesntQualifyOne());
        
        assertEquals(false, table.checkAllDeterminated());
        assertEquals(2, table.getBoxes().size());
        
        table.bet(0);
        table.fold(1);
        
        assertEquals(1, table.getBoxes().size());
        assertEquals(true, table.checkAllDeterminated());
        
        table.calculateDealResult();
        table.choiceBuyGameForDealer(0);
        
        assertEquals(940, table.getBankroll());
        
        table.buyGame();
        
        assertEquals(GameStatus.GAME_BOUGHT, table.getGameStatus());
        assertEquals(5, table.getDealerBox().getHand().getCards().size());
        
        table.getDealerBox().getHand().getCards().clear();
        table.getDealerBox().setHand(getPairAABCD());
        table.calculateDealResult();
        
        assertEquals(1050, table.getBankroll());
        assertEquals(GameStatus.SHOWDOWN, table.getGameStatus());
        
    }
}
