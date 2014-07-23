package com.rulezzz.pkr.core.base.engines;

import java.util.ArrayList;
import java.util.List;

import com.rulezzz.pkr.core.card.Card;

public final class GameMath {

    private int f = 0;
    private List<ArrayList<Card>> combin = new ArrayList<ArrayList<Card>>();

    public GameMath() {}

    public List<ArrayList<Card>> generateCombinations(List<Card> list,
            int k) {
        int g = list.size();
        combinate(list, g, k);
        return combin;
    }

    private void combinate(List<Card> cardList, int size, int k) {
        Card[] data = new Card[k];
        combinationUtil(cardList, data, 0, size - 1, 0, k);

    }

    private void combinationUtil(List<Card> cardList, Card[] data,
            int start, int end, int index, int k) {
        if (index == k) {
            combin.add(new ArrayList<Card>());
            for (int j = 0; j < k; j++) {
                combin.get(f).add(data[j]);
            }
            f++;
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= k - index; i++) {
            data[index] = cardList.get(i);
            combinationUtil(cardList, data, i + 1, end, index + 1, k);
        }
    }

}
