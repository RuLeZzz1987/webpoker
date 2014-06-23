package com.rulezzz.pkr.core.engine;

import com.google.common.base.Objects;

public class Combination implements Comparable<Combination> {

    private String code;
    private String name;
    private int highness;
    private static final int MAXCARDSCOUNT = 6;
    private int[] kickers = new int[MAXCARDSCOUNT];
    private static final int ACEKINGHIGH = 1;
    private static final int DNQ = 0;
    private static final int ROYALFLUSH = 10;
    private static final int PAIR = 2;
    private static final int TWOPAIRS = 3;
    private static final int SET = 4;
    private static final int STRAIGHT = 5;
    private static final int FLUSH = 6;
    private static final int FULLHOUSE = 7;
    private static final int KARE = 8;
    private static final int STRAIGHTFLUSH = 9;

    public Combination(String code) {
        if (!code.equals("draw")) {
            this.code = code;
            setName(code);
            setKickersList(code);
        } else {
            this.name = null;
            this.code = null;
            this.highness = -1;
        }
    }

    public int getHighness() {
        return this.highness;
    }

    public String getCombCode() {
        return this.code;
    }

    private void setKickersList(String comboCode) {
        int i = 0;
        String bufCode = comboCode + " ";
        while (bufCode.compareTo(" ") != 0) {
            String bufstr = bufCode.substring(0, bufCode.indexOf(' '));
            kickers[i] = Integer.parseInt(bufstr);
            bufCode = bufCode.substring(bufCode.indexOf(' ') + 1);
            i++;
        }

    }

    public int[] getKickers() {
        return this.kickers;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private void setName(String code) {
        switch (code.charAt(0)) {
        case '0': {
            this.name = "Don't qualify";
            this.highness = DNQ;
            break;
        }
        case '1': {
            if (code.charAt(1) != '0') {
                this.name = "Ace & King";
                this.highness = ACEKINGHIGH;
            } else {
                this.name = "Royal flush";
                this.highness = ROYALFLUSH;
            }
            break;
        }
        case '2': {
            this.name = "Pair";
            this.highness = PAIR;
            break;
        }
        case '3': {
            this.name = "Two pairs";
            this.highness = TWOPAIRS;
            break;
        }
        case '4': {
            this.name = "Three of a kind";
            this.highness = SET;
            break;
        }
        case '5': {
            this.name = "Straight";
            this.highness = STRAIGHT;
            break;
        }
        case '6': {
            this.name = "Flush";
            this.highness = FLUSH;
            break;
        }
        case '7': {
            this.name = "Full house";
            this.highness = FULLHOUSE;
            break;
        }
        case '8': {
            this.name = "Four of a kind";
            this.highness = KARE;
            break;
        }
        case '9': {
            this.name = "Straight flush";
            this.highness = STRAIGHTFLUSH;
            break;
        }
        default: {
            throw new IllegalArgumentException(
                    "Unknown combination code. It should be in range 0-10");
        }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.code);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Combination other = (Combination) obj;
        return Objects.equal(this.code, other.code);
    }

    @Override
    public int compareTo(Combination comb) {
        for (int i = 0; i < kickers.length; i++) {
            if (comb.getKickers()[i] > kickers[i]) {
                return -1;
            }
            if (comb.getKickers()[i] < kickers[i]) {
                return 1;
            }
        }
        return 0;
    }
}
