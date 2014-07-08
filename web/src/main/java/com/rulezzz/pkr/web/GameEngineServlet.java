package com.rulezzz.pkr.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rulezzz.pkr.core.base.structures.Box.BoxStatus;
import com.rulezzz.pkr.core.base.structures.PlayerBox;
import com.rulezzz.pkr.core.card.Card;
import com.rulezzz.pkr.core.gameengine.Table;

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
        switch (table.getGameStatus()) {
            case BETS: {
                break;
            }
            case DRAWS: {
                prepareChoices(table, req);
                table.handleDetermination();
                break;
            }
            case DETERMINATION: {
                handleChoiceAfterDrawPhase(req, table);
                break;
            }
            case SHOWDOWN: {
                req.getRequestDispatcher("/WEB-INF/jsp/nextDealParameters.jsp").forward(req, resp);
                return;
            }
            case DEALER_DNQ: {
                handleChoiceAfterDealerDNQ(req, table);
                break;
            }
            default: {
                break;
            }
        }
        if ( table.checkAllDeterminated() ) {
            table.calculateDealResult();
        }
        req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    }

    private void handleChoiceAfterDealerDNQ(HttpServletRequest req, Table table) {
        int boxIndex = table.getBoxes().size() - 1;
        while ( boxIndex >= 0 ) {
            String choice = req.getParameter("choice" + boxIndex);
            switch (choice) {
                case "ante" : {
                    table.takeAnte(boxIndex);
                    break;
                }
                case "buy_game" : {
                    table.choiceBuyGameForDealer(boxIndex);
                    break;
                }
                default : {
                    break;
                }
            }
            boxIndex--;
        }
        table.buyGame();
    }

    private void handleChoiceAfterDrawPhase(HttpServletRequest req, Table table) {
        int i = table.getBoxes().size() - 1;
        while ( i >= 0 ) {
            PlayerBox current = table.getBox(i);
            if (current.getStatus() == BoxStatus.DETERMINATION) {
                int currentIndex = table.getBoxes().indexOf(current);
                String boxChoice = req.getParameter("choice" + currentIndex);
                switch(boxChoice) {
                    case "bet" : {
                        table.bet(currentIndex);
                        break;
                    }
                    case "fold" : {
                        table.fold(currentIndex);
                        break;
                    }
                    case "insurance" : {
                        break;
                    }
                    default : {
                        break;
                    }
                }
            }
            i--;
        }
    }
    
    public void prepareChoices(Table table, HttpServletRequest req) {
        for (int boxIndex = table.getBoxes().size() - 1; boxIndex >= 0 ; boxIndex--) {
            String choice = req.getParameter("choice" + boxIndex);
            switch(choice) {
                case "bet" : {
                    table.bet(boxIndex);
                    break;
                }
                case "fold" : {
                    table.fold(boxIndex);
                    break;
                }
                case "draw" : {
                    List<Card> foldCards = new ArrayList<Card>();
                    for ( Card card : table.getBox(boxIndex).getHand().getCards()) {
                        if ( req.getParameter(card.toString()) == null ) {
                            foldCards.add(card);
                        }
                    }
                    table.draw(boxIndex, foldCards);
                    break;
                }
                default : {
                    break;
                }
            }
        }
        
    }
}
