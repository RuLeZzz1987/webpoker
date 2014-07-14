package com.rulezzz.pkr.web;

import static com.rulezzz.pkr.core.combination.samples.ComboSamples.getAceKingHigher;
import static com.rulezzz.pkr.core.combination.samples.ComboSamples.getAceKingLower;
import static com.rulezzz.pkr.core.combination.samples.ComboSamples.getDoesntQualifyOne;
import static com.rulezzz.pkr.core.combination.samples.ComboSamples.getPairAABCD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.rulezzz.pkr.core.base.structures.Box.BoxStatus;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.card.CardSuit;
import com.rulezzz.pkr.core.combination.AbstractCombination;
import com.rulezzz.pkr.core.combination.DoesntQualify;
import com.rulezzz.pkr.core.combination.FullHouse;
import com.rulezzz.pkr.core.combination.samples.ComboSamples;
import com.rulezzz.pkr.core.combination.samples.DeckSample;
import com.rulezzz.pkr.core.gameengine.Table;
import com.rulezzz.pkr.core.statuses.GameStatus;

public class GameEngineTest {
    
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private HttpSession session;
    private RequestDispatcher reqDispatcher;
    private Table table;
    
    @Before
    public void setUp(){
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        session = MockSession.createMockSession();
        reqDispatcher = mock(RequestDispatcher.class);
        table = new Table();
    }
    
 /*   @Test
    public void testIfOneBuyCardAndBet() throws ServletException, IOException {
        when(req.getParameter("bet")).thenReturn("25");
        when(req.getParameter("bankroll")).thenReturn("1000");
        when(req.getParameter("boxCount")).thenReturn("1");
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp")).thenReturn(reqDispatcher);
        when(req.getSession()).thenReturn(session);
        
        StartPageServlet startPage = new StartPageServlet();
        startPage.doPost(req, resp);
        
        verify(req, atLeast(1)).getSession();
        Table objTable = (Table) req.getSession().getAttribute("table");
        assertNotNull(objTable);
        assertEquals(getAceKingHigher(), objTable.getDealerBox().getHand());
        assertEquals(getDoesntQualifyOne(), objTable.getBox(0).getHand());
        
        when(req.getParameter("choice0")).thenReturn("buy");
    
        GameEngineServlet engine = new GameEngineServlet();
        engine.doPost(req, resp);
        
        objTable = (Table) req.getSession().getAttribute("table");
        verify(req, atLeast(1)).getSession();
        String dnqName = getDoesntQualifyOne().getHandAbstractCombination().getName();
        String currName = objTable.getBox(0).getHand().getHandAbstractCombination().getName();
        assertEquals(dnqName, currName);
        assertEquals(6, objTable.getBox(0).getHand().getCards().size());
        
        when(req.getParameter("choice0")).thenReturn("bet");
        
        engine.doPost(req, resp);
        
        objTable = (Table) req.getSession().getAttribute("table");
        currName = objTable.getBox(0).getHand().getHandAbstractCombination().getName();
        assertEquals(dnqName, currName);
        assertEquals(900, objTable.getBankroll());
        
        when(req.getParameter("boxCount")).thenReturn("1");
        when(req.getParameter("bet0")).thenReturn("25");
        
        DealPreferences preferences = new DealPreferences();
        
        preferences.doPost(req, resp);
        
        when(req.getParameter("choice0")).thenReturn("buy");
        
        engine.doPost(req, resp);
        
        assertEquals(FullHouse.class, objTable.getBox(0).getHand().getHandAbstractCombination().getClass());
        
        when(req.getParameter("choice0")).thenReturn("bet");
        
        engine.doPost(req, resp);
    } */
    
    @Test
    public void testIfOneBoxBetAndTakeAnte() throws ServletException, IOException {
        table.setBankroll(1000);
        table.makeBets(10);
        table.deal();
        table.getDealerBox().getHand().getCards().clear();
        table.getDealerBox().setHand(getDoesntQualifyOne());
        table.getBox(0).getHand().getCards().clear();
        table.getBox(0).setHand(getPairAABCD());
        
        when(req.getParameter("choice0")).thenReturn("bet");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp")).thenReturn(reqDispatcher);
        
        session.setAttribute("table", table);
        GameEngineServlet engine = new GameEngineServlet();
        
        engine.doGet(req, resp);
        engine.doPost(req, resp);
        
        when(req.getParameter("choice0")).thenReturn("ante");
        
        engine.doPost(req, resp);
    }
    

    
    @Test
    public void testIfOneBoxBetAndBuyGame() throws ServletException, IOException {
        table.setBankroll(1000);
        table.makeBets(10);
        table.deal();
        table.getDealerBox().getHand().getCards().clear();
        table.getDealerBox().setHand(getDoesntQualifyOne());
        table.getBox(0).getHand().getCards().clear();
        table.getBox(0).setHand(getPairAABCD());
        
        when(req.getParameter("choice0")).thenReturn("bet");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp")).thenReturn(reqDispatcher);
        
        session.setAttribute("table", table);
        GameEngineServlet engine = new GameEngineServlet();
        
        engine.doGet(req, resp);
        engine.doPost(req, resp);
        
        when(req.getParameter("choice0")).thenReturn("buy_game");
        
        engine.doPost(req, resp);
    }
    
    @Test
    public void testIfOneBoxDrawThenBetAndWin() throws ServletException, IOException {
        table.setBankroll(1000);
        table.makeBets(10);
        table.deal();
        table.getDealerBox().getHand().getCards().clear();
        table.getDealerBox().setHand(getAceKingLower());
        table.getBox(0).getHand().getCards().clear();
        table.getBox(0).setHand(getPairAABCD());
        List<Card> handThatDraw = new ArrayList<Card>();
        handThatDraw.addAll(table.getBox(0).getHand().getCards());
        
        when(req.getParameter("choice0")).thenReturn("draw");
        when(req.getSession()).thenReturn(session);
        when(req.getParameter(handThatDraw.get(0).toString())).thenReturn(handThatDraw.get(0).toString());
        when(req.getParameter(handThatDraw.get(1).toString())).thenReturn(handThatDraw.get(1).toString());
        when(req.getParameter(handThatDraw.get(2).toString())).thenReturn(null);
        when(req.getParameter(handThatDraw.get(3).toString())).thenReturn(null);
        when(req.getParameter(handThatDraw.get(4).toString())).thenReturn(null);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp")).thenReturn(reqDispatcher);
        
        session.setAttribute("table", table);
        GameEngineServlet engine = new GameEngineServlet();
        engine.doPost(req, resp);
        
        when(req.getParameter("choice0")).thenReturn("bet");
        
        engine.doPost(req, resp);
        
    }
    
    @Test
    public void testIfOneBoxDrawThenFold() throws ServletException, IOException{
        table.makeBets(10);
        table.deal();
        
        when(req.getParameter("choice0")).thenReturn("draw");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp")).thenReturn(reqDispatcher);
        
        session.setAttribute("table", table);
        GameEngineServlet engine = new GameEngineServlet();
        engine.doPost(req, resp);
        
        when(req.getParameter("choice0")).thenReturn("fold");
        
        engine.doPost(req, resp);
    }
    
    @Test
    public void testIfOneBoxBetAndWin() throws ServletException, IOException {
        table.setBankroll(1000);
        table.makeBets(10);
        table.deal();
        table.getDealerBox().getHand().getCards().clear();
        table.getDealerBox().setHand(getAceKingHigher());
        table.getBox(0).getHand().getCards().clear();
        table.getBox(0).setHand(getPairAABCD());
        
        when(req.getParameter("choice0")).thenReturn("bet");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp")).thenReturn(reqDispatcher);
        
        session.setAttribute("table", table);
        GameEngineServlet engine = new GameEngineServlet();
        engine.doPost(req, resp);
        
        assertEquals(1020, table.getBankroll());
    }
    
    @Test
    public void testComplexGameEngineServlet() throws ServletException, IOException {        
        table.makeBets(20, 25, 15);
        table.deal();
        List<Card> handThatDraw = new ArrayList<Card>();
        handThatDraw.addAll(table.getBox(0).getHand().getCards());

        when(req.getParameter(handThatDraw.get(0).toString())).thenReturn(handThatDraw.get(0).toString());
        when(req.getParameter(handThatDraw.get(1).toString())).thenReturn(handThatDraw.get(1).toString());
        when(req.getParameter(handThatDraw.get(2).toString())).thenReturn(null);
        when(req.getParameter(handThatDraw.get(3).toString())).thenReturn(null);
        when(req.getParameter(handThatDraw.get(4).toString())).thenReturn(null);
        when(req.getParameter("choice0")).thenReturn("draw");
        when(req.getParameter("choice1")).thenReturn("fold");
        when(req.getParameter("choice2")).thenReturn("bet");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp")).thenReturn(reqDispatcher);
        
        session.setAttribute("table", table);
        GameEngineServlet engine = new GameEngineServlet();
        engine.doPost(req, resp);
        
        
        verify(req, times(1)).getSession();
        assertEquals(2, table.getBoxes().size());
        assertEquals(5, table.getBox(0).getHand().getCards().size());
        int newCardsCount = 0;
        for (Card card : table.getBox(0).getHand().getCards()) {
            if ( !handThatDraw.contains(card) ) {
                newCardsCount++;
            }
        }
        assertEquals(3, newCardsCount);
        assertEquals(BoxStatus.BET, table.getBox(1).getStatus());
        assertEquals(BoxStatus.DETERMINATION, table.getBox(0).getStatus());
        assertEquals(GameStatus.DETERMINATION, table.getGameStatus());
        
        when(req.getParameter("choice0")).thenReturn("fold");
        when(req.getParameter("choice1")).thenReturn(null);
        
        engine.doPost(req, resp);
        
    }
    
    @Test
    public void testGameEngineNewDeal() throws ServletException, IOException {
        table.makeBets(10);
        table.deal();
        
        when(req.getParameter("choice0")).thenReturn("fold");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp"))
        .thenReturn(reqDispatcher);
        when(req.getRequestDispatcher("/WEB-INF/jsp/nextDealParameters.jsp"))
        .thenReturn(reqDispatcher);
        
        session.setAttribute("table", table);
        GameEngineServlet engine = new GameEngineServlet();
        engine.doPost(req, resp);
        engine.doPost(req, resp);
    }
    
    @Test
    public void testPrepareDrawPhase() {
        table.makeBets(10, 15, 30);
        table.deal();
        List<Card> drawCards = table.getBox(0).getHand().getCards();
        
        when(req.getParameter("choice2")).thenReturn("fold");
        when(req.getParameter("choice1")).thenReturn("bet");
        when(req.getParameter("choice0")).thenReturn("draw");
        
        when(req.getParameter(drawCards.get(0).toString())).thenReturn(drawCards.get(0).toString());
        when(req.getParameter(drawCards.get(1).toString())).thenReturn(drawCards.get(1).toString());
        when(req.getParameter(drawCards.get(2).toString())).thenReturn(null);
        when(req.getParameter(drawCards.get(3).toString())).thenReturn(null);
        when(req.getParameter(drawCards.get(4).toString())).thenReturn(null);
        
        GameEngineServlet engine = new GameEngineServlet();
        engine.prepareChoices(table, req);
        assertEquals(2, table.getBoxes().size());
        
    }
}
