package com.rulezzz.pkr.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.rulezzz.pkr.core.gameengine.Table;

public class DealPreferencesTest {

    
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private HttpSession session;
    private RequestDispatcher reqDispatcher;
    private Table table;
    private HttpServletRequest startReq;
    
    @Before
    public void setUp(){
        req = mock(HttpServletRequest.class);
        startReq = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        session = MockSession.createMockSession();
        reqDispatcher = mock(RequestDispatcher.class);
        table = new Table();
    }
    
    @Test
    public void testCheckPreferences() throws ServletException, IOException {
        table.setBankroll(1000);
        table.makeBets(20);
        table.deal();
        table.fold(0);
        table.calculateDealResult();
        
        assertEquals(1, table.getDefaultBets().size());
        assertEquals(20, table.getDefaultBets().get(0).intValue());
        
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("boxCount")).thenReturn("2");
        when(req.getParameter("bet0")).thenReturn("25");
        when(req.getParameter("bet1")).thenReturn("50");
        when(startReq.getRequestDispatcher("/WEB-INF/jsp/nextDealParameters.jsp")).thenReturn(reqDispatcher);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp")).thenReturn(reqDispatcher);
        session.setAttribute("table", table);
        
        DealPreferences preferences = new DealPreferences();
        preferences.doGet(startReq, resp);
        preferences.doPost(req, resp);
        
        verify(req, times(1)).getSession();
        assertEquals(2, table.getDefaultBets().size());
        assertEquals(25, table.getDefaultBets().get(0).intValue());
        assertEquals(50, table.getDefaultBets().get(1).intValue());
        assertEquals(2, table.getBoxes().size());
    }
}
