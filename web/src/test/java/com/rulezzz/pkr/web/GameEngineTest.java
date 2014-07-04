package com.rulezzz.pkr.web;

import static com.rulezzz.pkr.core.combination.ComboSamples.getAceKingHigher;
import static com.rulezzz.pkr.core.combination.ComboSamples.getAceKingLower;
import static com.rulezzz.pkr.core.combination.ComboSamples.getDoesntQualifyOne;
import static com.rulezzz.pkr.core.combination.ComboSamples.getPairAABCD;
import static org.junit.Assert.assertEquals;
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
