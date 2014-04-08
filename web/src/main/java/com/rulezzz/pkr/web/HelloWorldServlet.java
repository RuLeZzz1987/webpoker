package com.rulezzz.pkr.web;

import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rulezzz.pkr.core.GameType;
import com.rulezzz.pkr.core.Table;

public class HelloWorldServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String s = req.getParameter("bets");
    	String n = req.getParameter("hands");
    	req.setAttribute("mytxt", s);
    	Table t = new Table(GameType.FIVECARD);
    	t.deal(Integer.parseInt(n));
    	req.setAttribute("table", t);
    	req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    	
    }
}
