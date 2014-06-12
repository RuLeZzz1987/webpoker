package com.rulezzz.pkr.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
        List<String> testData = new LinkedList<String>();
        testData.add("a");
        testData.add("-5");
        testData.add("200");
        List<String> testGameType = new LinkedList<String>();
        testGameType.add(null);
        testGameType.add("FIVECARD");
        for (String type : testGameType) {
            for (String param : testData) {
                when(req.getParameter("gametype")).thenReturn(type);
                when(req.getParameter("boxCount")).thenReturn("2");
                when(req.getParameter("bankroll")).thenReturn("1000");
                when(req.getParameter("bet")).thenReturn(param);
                when(req.getSession()).thenReturn(session);
                when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp"))
                        .thenReturn(reqDispatcher);

                StartPageServlet startPage = new StartPageServlet();
                startPage.doPost(req, resp);

                verify(req, atLeast(1)).getSession();
            }
        }
        // verify(req.getSession().getAttribute("talbe"), Table);
    }
}
