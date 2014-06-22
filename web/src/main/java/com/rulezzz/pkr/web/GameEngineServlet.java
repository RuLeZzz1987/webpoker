package com.rulezzz.pkr.web;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rulezzz.pkr.core.BoxStatus;
import com.rulezzz.pkr.core.Card;
import com.rulezzz.pkr.core.PlayerBox;
import com.rulezzz.pkr.core.Table;

public class GameEngineServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Table table = (Table) req.getSession().getAttribute("table");
        List<String> choiseList = new LinkedList<String>();
        switch (table.getGameStatus()) {
            case DRAWS: {
                
                for (int i = 0; i < table.getBoxes().size(); i++) {
                    if (!req.getParameter("choise" + i).equals("draw")) {
                        choiseList.add(req.getParameter("choise" + i));
                    } else {
                        StringBuilder drawChoise = new StringBuilder("draw ");
                        for (Card card : table.getBox(i).getHand().getCards()) {
                            if (req.getParameter(card.toString()) != null) {
                                drawChoise.append(1);
                            } else {
                                drawChoise.append(0);
                            }
                        }
                        choiseList.add(drawChoise.toString());
                    }
                }
                table.handleDraws(choiseList);
                table.handleDetermination();
                break;
            }
            case DETERMINATION: {
                for (PlayerBox box : table.getBoxes()) {
                    if (box.getStatus() == BoxStatus.DETERMINATION) {
                        choiseList.add(req.getParameter("choise" + table.getBoxes().indexOf(box)));
                    }
                }
                table.handleDraws(choiseList);
                break;
            }
            default: {
                break;
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    }
}
