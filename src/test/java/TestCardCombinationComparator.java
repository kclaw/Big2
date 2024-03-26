package test.java;

import model.Card.impl.*;

import java.util.Arrays;
import java.util.List;

import model.Card.*;
import model.Card.Card;

public class TestCardCombinationComparator {

	public void test() {
		Card card1 = new Card(CardRank.A, CardSuit.Diamonds);
		Card card2 = new Card(CardRank.A, CardSuit.Spades);
		Card card3 = new Card(CardRank.A, CardSuit.Hearts);
		Card card4 = new Card(CardRank.A, CardSuit.Clubs);
		Card card5 = new Card(CardRank.Two, CardSuit.Diamonds);
		Card card6 = new Card(CardRank.Two, CardSuit.Spades);
		Card card7= new Card(CardRank.Two, CardSuit.Hearts);
		Card card8 = new Card(CardRank.Two, CardSuit.Clubs);
		Card card9= new Card(CardRank.Three, CardSuit.Diamonds);
		Card card10 = new Card(CardRank.Three, CardSuit.Spades);
		Card card11 = new Card(CardRank.Three, CardSuit.Hearts);
		
		CardCombination single1 = new SingleCardCombination.SingleCardCombinationBuilder()
						.addCard(card1)
						.build();
		CardCombination single2 = new SingleCardCombination.SingleCardCombinationBuilder()
				.addCard(card2)
				.build();
		
		CardCombination pairs = new PairsCardCombination.PairsCardCombinationBuilder()
				.addCard(card1)
				.addCard(card2)
				.build();
		CardCombination pairs2 = new PairsCardCombination.PairsCardCombinationBuilder()
				.addCard(card3)
				.addCard(card4)
				.build();
		CardCombination triples = new TriplesCardCombination.TriplesCardCombinationBuilder()
				.addCard(card9)
				.addCard(card10)
				.addCard(card11)
				.build();
		CardCombination triples2 = new TriplesCardCombination.TriplesCardCombinationBuilder()
				.addCard(card5)
				.addCard(card6)
				.addCard(card7)
				.build();
		CardCombination fiveCardHands = new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
				.addCard(card1)
				.addCard(card2)
				.addCard(card3)
				.addCard(card4)
				.addCard(card5)
				.build();
		CardCombination fiveCardHands2 = new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
				.addCard(card9)
				.addCard(card10)
				.addCard(card11)
				.addCard(card7)
				.addCard(card6)
				.build();
		CardCombinationComparator comparator = new CardCombinationComparatorImpl();
		System.out.println(comparator.isGreaterThan(fiveCardHands2, fiveCardHands));
	}
	
	
}
