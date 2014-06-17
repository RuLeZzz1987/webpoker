package com.rulezzz.pkr.core.combination;

import java.util.LinkedList;
import java.util.List;

import com.rulezzz.pkr.core.Card;

public class D_N_Q implements ICombination {

    private int highness;
    private String name;
    private List<Card> kickers = new LinkedList<Card>();
    
    public D_N_Q(List<Card> cardHand) {
        this.highness = 0;
        this.name = "Doesn't qualifier";
        this.kickers = cardHand;
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
