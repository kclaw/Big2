package Card.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.paukov.combinatorics3.Generator;

import Card.Card;
import Card.CardCombination;
import Card.CardCombinationEmulator;
import Card.CardRank;
import Card.CardSuit;

public class TriplesCardCombinationEmulator implements CardCombinationEmulator{

	@Override
	public List<CardCombination> emulate(CardCombination combination) {
		if (combination.getNoOfCards()!=3)
			return null;
		List<Card> cards = combination.getCards().stream().collect(Collectors.toList());
		
		List<Card> cards2=elaborate(cards.get(0));
		cards2.addAll(elaborate(cards.get(1)));
		cards2.addAll(elaborate(cards.get(2)));
		
		//Do not use cards as input. suit is anysuit. should be interpreted
		Set<Card> cards3 = cards2.stream().collect(Collectors.toSet());
		List<CardCombination> list1= Generator.combination(cards3)
	       .simple(3)
	       .stream()
	       .filter(cl-> {
	      	   List<List<CardRank>> cr = Generator.permutation(cards.get(0).getCardRank(),cards.get(1).getCardRank(),cards.get(2).getCardRank())
			    	   .simple().stream()
			    	   .collect(Collectors.toList());  
	    	   for (int i=0;i<cr.size();i++) 
	    		   if(cl.get(0).getCardRank()==cr.get(i).get(0)&&cl.get(1).getCardRank()==cr.get(i).get(1)&&cl.get(2).getCardRank()==cr.get(i).get(2))
	    			   return true;
	    	   return false;
	    	})
	       .map(comb-> new TriplesCardCombination.TriplesCardCombinationBuilder().addCard(comb.get(0)).addCard(comb.get(1)).addCard(comb.get(2)).setCardCombinationValue(combination.getValue()).build())
	       .collect(Collectors.toList());
		return list1;
	}

	private List<Card> elaborate(Card card){
		List<Card> cardList = new ArrayList<>();
		switch(card.getCardRank()) {
			case AnyPairs:
			case AnyRank:
				if (card.getCardSuit()==CardSuit.AnySuit) {
					cardList.add(new Card(CardRank.Two, CardSuit.Spades));
					cardList.add(new Card(CardRank.Two, CardSuit.Hearts));
					cardList.add(new Card(CardRank.Two, CardSuit.Clubs));
					cardList.add(new Card(CardRank.Two, CardSuit.Diamonds));
				} else {
					cardList.add(new Card(CardRank.Two, card.getCardSuit()));
				}
			case AnyLessThan2:
				if (card.getCardSuit()==CardSuit.AnySuit) {
					cardList.add(new Card(CardRank.A, CardSuit.Spades));
					cardList.add(new Card(CardRank.A, CardSuit.Hearts));
					cardList.add(new Card(CardRank.A, CardSuit.Clubs));
					cardList.add(new Card(CardRank.A, CardSuit.Diamonds));
				} else {
					cardList.add(new Card(CardRank.A, card.getCardSuit()));
				}
			case AnyLessThanA:
				if (card.getCardSuit()==CardSuit.AnySuit) {
					cardList.add(new Card(CardRank.K, CardSuit.Spades));
					cardList.add(new Card(CardRank.K, CardSuit.Hearts));
					cardList.add(new Card(CardRank.K, CardSuit.Clubs));
					cardList.add(new Card(CardRank.K, CardSuit.Diamonds));
				} else {
					cardList.add(new Card(CardRank.K, card.getCardSuit()));
				}
			case AnyLessThanK:
				if (card.getCardSuit()==CardSuit.AnySuit) {
					cardList.add(new Card(CardRank.Q, CardSuit.Spades));
					cardList.add(new Card(CardRank.Q, CardSuit.Hearts));
					cardList.add(new Card(CardRank.Q, CardSuit.Clubs));
					cardList.add(new Card(CardRank.Q, CardSuit.Diamonds));
				} else {
					cardList.add(new Card(CardRank.Q, card.getCardSuit()));
				}
			case AnyLessThanQ:
				if (card.getCardSuit()==CardSuit.AnySuit) {
					cardList.add(new Card(CardRank.J, CardSuit.Spades));
					cardList.add(new Card(CardRank.J, CardSuit.Hearts));
					cardList.add(new Card(CardRank.J, CardSuit.Clubs));
					cardList.add(new Card(CardRank.J, CardSuit.Diamonds));
				} else {
					cardList.add(new Card(CardRank.J, card.getCardSuit()));
				}
			case AnyLessThanJ:
				if (card.getCardSuit()==CardSuit.AnySuit) {
					cardList.add(new Card(CardRank.Ten, CardSuit.Spades));
					cardList.add(new Card(CardRank.Ten, CardSuit.Hearts));
					cardList.add(new Card(CardRank.Ten, CardSuit.Clubs));
					cardList.add(new Card(CardRank.Ten, CardSuit.Diamonds));
				} else {
					cardList.add(new Card(CardRank.Ten, card.getCardSuit()));
				}
			case AnyLessThan10:
				if (card.getCardSuit()==CardSuit.AnySuit) {
					cardList.add(new Card(CardRank.Nine, CardSuit.Spades));
					cardList.add(new Card(CardRank.Nine, CardSuit.Hearts));
					cardList.add(new Card(CardRank.Nine, CardSuit.Clubs));
					cardList.add(new Card(CardRank.Nine, CardSuit.Diamonds));
				} else {
					cardList.add(new Card(CardRank.Nine, card.getCardSuit()));
				}
			case AnyLessThan9:
				if (card.getCardSuit()==CardSuit.AnySuit) {
					cardList.add(new Card(CardRank.Eight, CardSuit.Spades));
					cardList.add(new Card(CardRank.Eight, CardSuit.Hearts));
					cardList.add(new Card(CardRank.Eight, CardSuit.Clubs));
					cardList.add(new Card(CardRank.Eight, CardSuit.Diamonds));
				} else {
					cardList.add(new Card(CardRank.Eight, card.getCardSuit()));
				}
			case AnyLessThan8:
				if (card.getCardSuit()==CardSuit.AnySuit) {
					cardList.add(new Card(CardRank.Three, CardSuit.Spades));
					cardList.add(new Card(CardRank.Three, CardSuit.Hearts));
					cardList.add(new Card(CardRank.Three, CardSuit.Clubs));
					cardList.add(new Card(CardRank.Three, CardSuit.Diamonds));
					cardList.add(new Card(CardRank.Four, CardSuit.Spades));
					cardList.add(new Card(CardRank.Four, CardSuit.Hearts));
					cardList.add(new Card(CardRank.Four, CardSuit.Clubs));
					cardList.add(new Card(CardRank.Four, CardSuit.Diamonds));
					cardList.add(new Card(CardRank.Five, CardSuit.Spades));
					cardList.add(new Card(CardRank.Five, CardSuit.Hearts));
					cardList.add(new Card(CardRank.Five, CardSuit.Clubs));
					cardList.add(new Card(CardRank.Five, CardSuit.Diamonds));
					cardList.add(new Card(CardRank.Six, CardSuit.Spades));
					cardList.add(new Card(CardRank.Six, CardSuit.Hearts));
					cardList.add(new Card(CardRank.Six, CardSuit.Clubs));
					cardList.add(new Card(CardRank.Six, CardSuit.Diamonds));
					cardList.add(new Card(CardRank.Seven, CardSuit.Spades));
					cardList.add(new Card(CardRank.Seven, CardSuit.Hearts));
					cardList.add(new Card(CardRank.Seven, CardSuit.Clubs));
					cardList.add(new Card(CardRank.Seven, CardSuit.Diamonds));
				}else {
					cardList.add(new Card(CardRank.Three, card.getCardSuit()));
					cardList.add(new Card(CardRank.Four, card.getCardSuit()));
					cardList.add(new Card(CardRank.Five, card.getCardSuit()));
					cardList.add(new Card(CardRank.Six, card.getCardSuit()));
					cardList.add(new Card(CardRank.Seven, card.getCardSuit()));
				}
				break;
			default:
				if (card.getCardSuit()==CardSuit.AnySuit) {
					cardList.add(new Card(card.getCardRank(), CardSuit.Spades));
					cardList.add(new Card(card.getCardRank(), CardSuit.Hearts));
					cardList.add(new Card(card.getCardRank(), CardSuit.Clubs));
					cardList.add(new Card(card.getCardRank(), CardSuit.Diamonds));
				}else 
					cardList.add(card);
				break;
		}
		return cardList;
	}
	
}
