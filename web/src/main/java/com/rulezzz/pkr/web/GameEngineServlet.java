package com.rulezzz.pkr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rulezzz.pkr.core.Table;

public class GameEngineServlet extends HttpServlet{

	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    }	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Table table = (Table) req.getSession().getAttribute("table");
		
		
		req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
	}
}
