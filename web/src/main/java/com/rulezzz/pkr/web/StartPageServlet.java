package com.rulezzz.pkr.web;

import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rulezzz.pkr.core.GameType;
import com.rulezzz.pkr.core.Table;

public class StartPageServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getRequestDispatcher("/WEB-INF/jsp/startPage.jsp").forward(req, resp);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String s = req.getParameter("gametype");
    	GameType gametype = GameType.FIVECARD;;
    	switch (s) {
    	case "FIVECARD" : {
    		gametype = GameType.FIVECARD;
    		break;
    	}
    	case "TEXAS" : {
    		gametype = GameType.TEXAS;
    		break;
    	}
    	case "OMAHA" : {
    		gametype = GameType.OMAHA;
    		break;
    	}
    	}
		Table t = new Table(gametype);
    	t.deal(2);
    	req.setAttribute("table", t);
    	req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    	
    }
}
