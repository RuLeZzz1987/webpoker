package com.rulezzz.pkr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rulezzz.pkr.core.gameengine.Table;


public class StartPageServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int MINBET = 5;
    private static final int MAXBET = 100;
    private static final int DEFAULTBET = 10;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/startPage.jsp").forward(req,
                resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String gameType = req.getParameter("gametype");
        int boxCount = Integer.parseInt(req.getParameter("boxCount"));
        int bankroll = Integer.parseInt(req.getParameter("bankroll"));
        int bet;
        try {
            bet = Integer.parseInt(req.getParameter("bet"));
        } catch (NumberFormatException e) {
            bet = DEFAULTBET;
        }
        if (bet > MAXBET) {
            bet = MAXBET;
        }
        if (bet < MINBET) {
            bet = MINBET;
        }
        int[] bets = new int[boxCount];
        for (int i = 0; i < bets.length; i++) {
            bets[i] = bet;
        }
        if (gameType == null) {
            gameType = "FIVECARD";
        }
        Table t = new Table();
        t.setBankroll(bankroll);
        t.makeBets(bets);
        t.deal();
        req.getSession().setAttribute("table", t);
        req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);

    }
}
