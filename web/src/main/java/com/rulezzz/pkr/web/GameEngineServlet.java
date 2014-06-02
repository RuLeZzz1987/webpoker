package com.rulezzz.pkr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rulezzz.pkr.core.Table;

public class GameEngineServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		Table table = (Table) req.getSession().getAttribute("table");
		switch (table.getGameStatus()) {
		case DRAWS: {
			for (int i = table.getBoxes().size() - 1; i >= 0; i++) {
				String choise = req.getParameter("choise" + i);
				switch (choise) {
				case "fold": {
					table.getBoxes().remove(i);
					break;
				}
				case "bet": {
					table.setBankroll(table.getBankroll()
					        - table.getBox(i).getAnte() * 2);
					table.getBox(i).play();
				}
				default: {
					throw new IllegalStateException("unknown choise for box");
				}
				}
			}
			break;
		}
		default: {
			throw new IllegalStateException("unknown game state");
		}
		}

		req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
	}
}
