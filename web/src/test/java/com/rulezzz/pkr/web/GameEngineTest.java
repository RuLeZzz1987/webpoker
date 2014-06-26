package com.rulezzz.pkr.web;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.rulezzz.pkr.core.basestructures.Card;
import com.rulezzz.pkr.core.engine.Table;

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
    public void testGameEngineServlet() throws ServletException, IOException {        
        table.makeBets(20, 25, 15);
        table.deal();
        List<Card> handThatDraw = table.getBox(0).getHand().getCards();

        when(req.getParameter(handThatDraw.get(0).toString())).thenReturn(null);
        when(req.getParameter(handThatDraw.get(2).toString())).thenReturn(null);
        when(req.getParameter(handThatDraw.get(4).toString())).thenReturn(null);
        when(req.getParameter("choise0")).thenReturn("draw");
        when(req.getParameter("choise1")).thenReturn("fold");
        when(req.getParameter("choise2")).thenReturn("bet");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp"))
        .thenReturn(reqDispatcher);
        
        session.setAttribute("table", table);
        GameEngineServlet engine = new GameEngineServlet();
        engine.doPost(req, resp);
        
        verify(req, atLeast(1)).getSession(); 
        
    }
    
    @Test
    public void testGameEngineNewDeal() throws ServletException, IOException {
        table.makeBets(10);
        table.deal();
        
        when(req.getParameter("choise0")).thenReturn("fold");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp"))
        .thenReturn(reqDispatcher);
        
        session.setAttribute("table", table);
        GameEngineServlet engine = new GameEngineServlet();
        engine.doPost(req, resp);
        engine.doPost(req, resp);
    }
}
