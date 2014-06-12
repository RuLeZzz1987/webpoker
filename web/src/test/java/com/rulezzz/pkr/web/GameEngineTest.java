package com.rulezzz.pkr.web;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class GameEngineTest {
    
    @Test
    public void testGameEngineServlet() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        request.getParameter("gametype");
        when(request.getParameter("gametype")).thenReturn("FIVECARD");
        
    }
}
