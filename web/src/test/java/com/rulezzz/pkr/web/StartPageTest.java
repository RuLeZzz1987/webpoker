package com.rulezzz.pkr.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.rulezzz.pkr.core.Table;

public class StartPageTest {
    
    @Test
    public void testStartPage() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher reqDispatcher = mock(RequestDispatcher.class);
        
        when(req.getParameter("gametype")).thenReturn("FIVECARD");
        when(req.getParameter("boxCount")).thenReturn("2");
        when(req.getParameter("bankroll")).thenReturn("1000");
        when(req.getParameter("bet")).thenReturn("25");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp")).thenReturn(reqDispatcher);
        
        StartPageServlet startPage = new StartPageServlet();
        startPage.doPost(req, resp);
        
        verify(req, times(1)).getSession();
        //verify(req.getSession().getAttribute("talbe"), Table);
    }
}
