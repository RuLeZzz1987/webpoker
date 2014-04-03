package com.rulezzz.pkr.core;

import java.util.ArrayList;

	public class GameMath{
	
	private static int f = 0;
	private static ArrayList<ArrayList<Card>> combin = new ArrayList<ArrayList<Card>>();
	
	public static ArrayList<ArrayList<Card>> generateCombinations(ArrayList<Card> cardList, int k) {
		int g = cardList.size();
		combinate(cardList, g, k);
		return combin;
	}

	private static  void combinate(ArrayList<Card> cardList, int size, int k) {
		Card[] data = new Card[k];
		combinationUtil(cardList, data, 0, size-1, 0, k);
		
	}

	private static void combinationUtil(ArrayList<Card> cardList,
			Card[] data, int start, int end, int index, int k) {
		 if (index == k)
		    {		
			 combin.add(new ArrayList<Card>());
		        for (int j=0; j<k; j++)
		            combin.get(f).add(data[j]); 
			 	f++;
			 	return;
		    }
		 	
		    for (int i=start; i<=end && end-i+1 >= k-index; i++)
		    {
		        data[index] = cardList.get(i);
		        combinationUtil(cardList, data, i+1, end, index+1, k);
		    }
		
	}



	
}
