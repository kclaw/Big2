package Card.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.paukov.combinatorics3.Generator;

import Card.Card;
import Card.CardCombination;
import Card.CardCombinationEmulator;
import Card.CardRank;
import Card.CardSuit;

public class Temp {


	public List<CardCombination> emulate(CardCombination combination) {
		if (combination.getNoOfCards()!=3)
			return null;
		List<Card> cards = combination.getCards().stream().collect(Collectors.toList());
		boolean[] shouldProcessRank = new boolean[5];
		boolean[] shouldProcessSuit = new boolean[5]; 
		for (int i=0;i<cards.size();i++) {
			switch(cards.get(i).getCardRank()) {
				case AnyLessThan8:
					//3,4,5,6,7
				case AnyLessThan9:
					//3,4,5,6,7
					//3,4,5,6,8
					//3,4,5,7,8
					//3,4,6,7,8
					//3,5,6,7,8
					//4,5,6,7,8
				case AnyLessThan10:
					//3,4,5,6,7,8,9
				case AnyLessThanJ:
				case AnyLessThanQ:
				case AnyLessThanK:
				case AnyLessThanA:
				case AnyLessThan2:
				case AnyPairs:
				case AnyRank:
					shouldProcessRank[i] = true;
					break;
			}
			switch(cards.get(i).getCardSuit()) {
				case AnySuit:
					shouldProcessSuit[i] = true;
					break;
			}
		}
		return null;
	}
	public static List<List<CardRank>> listAnyLessThan(CardRank sample){
		List<List<CardRank>> list = null;
		switch(sample) {
			case AnyLessThan8:
				list = Generator.combination(CardRank.Three, CardRank.Four, CardRank.Five, CardRank.Six, CardRank.Seven)
			       .simple(5)
			       .stream()
			      // .map(rank->{System.out.println(rank); return rank;})
			       .collect(Collectors.toList());
				break;
			case AnyLessThan9:
				list = Generator.combination(CardRank.Three, CardRank.Four, CardRank.Five, CardRank.Six, CardRank.Seven, CardRank.Eight)
			       .simple(5)
			       .stream()
			       //.map(rank->{System.out.println(rank); return rank;})
			       .collect(Collectors.toList());
				break;
			case AnyLessThan10:
				Set<Card> cardlist = new HashSet<>();
				cardlist.add(new Card(CardRank.Three, CardSuit.Spades));
				cardlist.add(new Card(CardRank.Three, CardSuit.Hearts));
				cardlist.add(new Card(CardRank.Three, CardSuit.Clubs));
				cardlist.add(new Card(CardRank.Three, CardSuit.Diamonds));
				
				/*cardlist.add(new Card(CardRank.Four, CardSuit.Spades));
				cardlist.add(new Card(CardRank.Four, CardSuit.Hearts));
				cardlist.add(new Card(CardRank.Four, CardSuit.Clubs));
				cardlist.add(new Card(CardRank.Four, CardSuit.Diamonds));
				
				cardlist.add(new Card(CardRank.A, CardSuit.Spades));
				cardlist.add(new Card(CardRank.A, CardSuit.Hearts));
				cardlist.add(new Card(CardRank.A, CardSuit.Clubs));
				cardlist.add(new Card(CardRank.A, CardSuit.Diamonds));
				
				cardlist.add(new Card(CardRank.Two, CardSuit.Spades));
				cardlist.add(new Card(CardRank.Two, CardSuit.Hearts));
				cardlist.add(new Card(CardRank.Two, CardSuit.Clubs));
				cardlist.add(new Card(CardRank.Two, CardSuit.Diamonds));
				cardlist.add(new Card(CardRank.Five, CardSuit.Diamonds));
				cardlist.add(new Card(CardRank.Five, CardSuit.Spades));
				*/
				List<List<Card>> list1= Generator.combination(cardlist)
			       .simple(3)
			       .stream()
			       .filter(cl-> {

			      	   List<List<CardRank>> cr = Generator.permutation(CardRank.Three,CardRank.Three,CardRank.Three)
					    	   .simple().stream()
					    	   .collect(Collectors.toList());
			  
					    	   
			    	   for (int i=0;i<cr.size();i++) {
			    		   if(cl.get(0).getCardRank()==cr.get(i).get(0)&&cl.get(1).getCardRank()==cr.get(i).get(1)&&cl.get(2).getCardRank()==cr.get(i).get(2))
			    			   return true;
			    	   }
			    	   return false;
			    	})
			       .collect(Collectors.toList());
				
				int counter = 0;
			
				for (List<Card> cl: list1.stream().collect(Collectors.toList())) {
					System.out.println(counter++);
					System.out.println(cl.get(0).getCardRank()+"///"+cl.get(1).getCardRank()+"///"+cl.get(2).getCardRank()+"///");
					System.out.println(cl.get(0).getCardSuit()+"///"+cl.get(1).getCardSuit()+"///"+cl.get(2).getCardSuit()+"///");
					//cl.forEach(value->{System.out.println(value.getCardRank());System.out.println(value.getCardSuit());});
				}
				
		
				break;
			default:
				break;
		}

		return list;
	}
	
}
