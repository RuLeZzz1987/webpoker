package com.rulezzz.pkr.web;

import java.io.IOException;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rulezzz.pkr.core.PlayerBox;
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
            ListIterator<PlayerBox> iter = table.getBoxes().listIterator();
            int i=0;
             while ( iter.hasNext() ){
                if (req.getParameter("choise" + i) == "fold" ) {
                    iter.remove();
                }
             }
        }
        default: {}
        }
        req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    }
}
