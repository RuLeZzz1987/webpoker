package com.rulezzz.pkr.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DealPreferences extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 7568943857505520837L;
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher("/WEB-INF/jsp/nextDealParameters.jsp");
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher("/WEB-INF/jsp/game.jsp");
    }
}
