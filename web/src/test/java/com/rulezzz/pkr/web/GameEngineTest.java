package com.rulezzz.pkr.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.rulezzz.pkr.core.BoxStatus;
import com.rulezzz.pkr.core.Card;
import com.rulezzz.pkr.core.GameType;
import com.rulezzz.pkr.core.Hand;
import com.rulezzz.pkr.core.Table;

public class GameEngineTest {

    @Test
    public void testGameEngineServlet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher reqDispatcher = mock(RequestDispatcher.class);
        HttpSession session = MockSession.createMockSession();
        Table table = new Table(GameType.FIVECARD);
        table.makeBets(20, 25, 15);
        table.deal();
        Hand testHand = new Hand();
        testHand.add(table.getBox(0).getHand().getCards().get(1));
        testHand.add(table.getBox(0).getHand().getCards().get(3));
        List<Card> handThatDraw = table.getBox(0).getHand().getCards();

        when(req.getParameter(handThatDraw.get(0).getStringCard())).thenReturn(
                null);
        when(req.getParameter(handThatDraw.get(1).getStringCard())).thenReturn(
                new String());
        when(req.getParameter(handThatDraw.get(2).getStringCard())).thenReturn(
                null);
        when(req.getParameter(handThatDraw.get(3).getStringCard())).thenReturn(
                new String());
        when(req.getParameter(handThatDraw.get(4).getStringCard())).thenReturn(
                null);
        when(req.getParameter("choise0")).thenReturn("draw");
        when(req.getParameter("choise1")).thenReturn("fold");
        when(req.getParameter("choise2")).thenReturn("bet");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/jsp/game.jsp")).thenReturn(
                reqDispatcher);

        session.setAttribute("table", table);
        GameEngineServlet engine = new GameEngineServlet();
        engine.doPost(req, resp);

        verify(req, times(1)).getSession();
        Table objTable = (Table) req.getSession().getAttribute("table");
        assertNotNull(objTable);
        assertEquals(2, objTable.getBoxes().size());
        assertEquals(2, objTable.getBox(0).getHand().getCards().size());
        assertEquals(testHand.getCards(), objTable.getBox(0).getHand()
                .getCards());
        assertEquals(BoxStatus.DRAW, objTable.getBox(0).getStatus());
        assertEquals(BoxStatus.BET, objTable.getBox(1).getStatus());
    }
}
