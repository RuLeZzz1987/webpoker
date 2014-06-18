package com.rulezzz.pkr.core.combination;

import java.util.List;

import com.rulezzz.pkr.core.Card;

public abstract class ICombination {

    public int compareTo(final ICombination combo) {
        if ( this.getHighness() > combo.getHighness() ) {
            return 1;
        }
        if ( this.getHighness() < combo.getHighness() ) {
            return -1;
        }
        if ( this.equals(combo) )  {
            return 0;
        } else {
            for (int i = 0; i < this.getKickersList().size(); i++) {
                switch(this.getKickersList().get(i).compareTo(combo.getKickersList().get(i))) {
                    case -1 : {
                        return -1;
                    }
                    case 1 : {
                        return 1;
                    }
                    default : {
                        break;
                    }
                }
                
            }
        }
        return 0;
    }
    
    public abstract int getHighness();
    
    public abstract String getName();
    
    public abstract List<Card> getKickersList();
}
