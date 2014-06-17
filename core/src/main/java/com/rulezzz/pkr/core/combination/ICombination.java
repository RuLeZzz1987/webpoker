package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.Card;

public interface ICombination {

    public int getHighness();
    
    public String getName();
    
    public List<Card> getKickersList();
    
}
