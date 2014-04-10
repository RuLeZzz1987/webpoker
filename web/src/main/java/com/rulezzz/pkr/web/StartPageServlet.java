package com.rulezzz.pkr.web;

import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rulezzz.pkr.core.GameType;
import com.rulezzz.pkr.core.Table;

public class StartPageServlet extends HttpServlet {
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getRequestDispatcher("/WEB-INF/jsp/startPage.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String s = req.getParameter("gametype");
		Table t = new Table(GameType.valueOf(s));
    	t.makeBets(5,5,10);
		t.deal();
    	req.setAttribute("table", t);
    	req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    	
    }
}
