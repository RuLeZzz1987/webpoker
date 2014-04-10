package com.rulezzz.pkr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GiveMeHi extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req, resp);
	}
}
