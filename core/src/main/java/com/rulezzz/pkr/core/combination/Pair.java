package com.rulezzz.pkr.core.combination;

import java.util.LinkedList;
import java.util.List;

import com.rulezzz.pkr.core.Card;

public class Pair implements ICombination {
    
    private int highness;
    private String name;
    private List<Card> kickers = new LinkedList<Card>();

    public Pair(List<Card> cardList) {
        this.highness = 2;
        this.name = "Pair";
    }
    
    @Override
    public int getHighness() {
        return this.highness;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Card> getKickersList() {
        return this.kickers;
    }

}
