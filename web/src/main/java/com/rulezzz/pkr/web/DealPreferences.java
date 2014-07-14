package com.rulezzz.pkr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rulezzz.pkr.core.gameengine.Table;

public class DealPreferences extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 7568943857505520837L;
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/nextDealParameters.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Table table = (Table) req.getSession().getAttribute("table");
        table.getBoxes().clear();
        table.getDefaultBets().clear();
        table.getDealerBox().getHand().getCards().clear();
        int boxCount = Integer.parseInt(req.getParameter("boxCount"));
        int[] antes = new int[boxCount];
        for (int i = 0; i < boxCount; i++) {
            antes[i] = Integer.parseInt(req.getParameter("bet" + i));
        }
        table.makeBets(antes);
        table.deal();
        req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    }
}
