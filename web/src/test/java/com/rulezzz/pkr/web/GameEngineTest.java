package com.rulezzz.pkr.web;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.rulezzz.pkr.core.GameType;
import com.rulezzz.pkr.core.Table;

public class GameEngineTest {
    
    @Test
    public void testGameEngineServlet() throws ServletException, IOException {
    /*    HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher reqDispatcher = mock(RequestDispatcher.class);
        Table table = new Table(GameType.FIVECARD);
        table.makeBets(20, 25, 15);
        table.deal();
        session.setAttribute("table", table);
        
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
        
        GameEngineServlet engine = new GameEngineServlet();
        engine.doPost(req, resp);
        
        verify(req, atLeast(1)).getSession(); */
        
    }
}
