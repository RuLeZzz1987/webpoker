package com.rulezzz.pkr.web;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeast;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.rulezzz.pkr.core.Table;

public class StartPageTest {

    @Test
    public void testStartPage() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher reqDispatcher = mock(RequestDispatcher.class);
        List<String> testBets = new LinkedList<String>();
        List<String> testGameTypes = new LinkedList<String>();
        testBets.add("a");
        testBets.add("-5");
        testBets.add("105");
        testBets.add("10");
        testGameTypes.add(null);
        testGameTypes.add("FIVECARD");
        for (String type : testGameTypes) {
            for (String bets : testBets) {

                when(req.getParameter("gametype")).thenReturn(type);
                when(req.getParameter("boxCount")).thenReturn("2");
                when(req.getParameter("bankroll")).thenReturn("1000");
                when(req.getParameter("bet")).thenReturn(bets);
                when(req.getSession()).thenReturn(session);
                when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp"))
                        .thenReturn(reqDispatcher);

                final Map<String, Object> sessionAttributes = new HashMap<>();
                doAnswer(new Answer<Object>() {
                    @Override
                    public Object answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        sessionAttributes.put((String) args[0], args[1]);
                        return null;
                    }
                }).when(session).setAttribute(anyString(), anyObject());
                when(session.getAttribute(anyString())).then(
                        new Answer<Object>() {

                            @Override
                            public Object answer(InvocationOnMock invocation)
                                    throws Throwable {
                                return sessionAttributes.get(invocation
                                        .getArguments()[0]);
                            }
                        });

                StartPageServlet startPage = new StartPageServlet();
                startPage.doPost(req, resp);

                verify(req, atLeast(1)).getSession();

                Table objTable = (Table) req.getSession().getAttribute("table");
                assertNotNull(objTable);

                assertEquals(5, objTable.getHand().getCards().size());
            }
        }
    }
}
