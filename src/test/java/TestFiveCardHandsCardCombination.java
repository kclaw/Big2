package test.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.paukov.combinatorics3.Generator;

import Card.Card;
import Card.CardCombination;
import Card.CardCombinationValue;
import Card.CardRank;
import Card.CardSuit;
import Card.impl.CardCombinationFactoryImpl;
import Card.impl.FiveCardHandsCardCombination;
import Card.impl.FiveCardHandsCardCombinationEmulator;

public class TestFiveCardHandsCardCombination {
	
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
	Card card12 = new Card(CardRank.Three, CardSuit.Clubs);
	Card card13 = new Card(CardRank.Four, CardSuit.Diamonds);
	Card card14 = new Card(CardRank.AnyPairs, CardSuit.AnySuit);
	Card card15 = new Card(CardRank.AnyPairs, CardSuit.AnySuit);
	
	CardCombination cb1= new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
			.addCard(card1)
			.addCard(card2)
			.addCard(card3)
			.addCard(card4)
			.addCard(card5)
			.build();
	CardCombination cb2= new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
			.addCard(card1)
			.addCard(card2)
			.addCard(card3)
			.addCard(card4)
			.addCard(card5)
			.setCardCombinationValue(new CardCombinationValue(10))
			.build();
	
	List<CardCombination> cb3 = new ArrayList<>();
	List<CardCombination> cb4 = new ArrayList<>();
	
	CardCombination cb6= new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
			.addCard(card1)
			.addCard(card2)
			.addCard(card3)
			.addCard(card14)
			.addCard(card15)
			.build();
	
	@Test
	public void test() {
		System.out.println(cb1.equals(cb2));
	}
	
	@Test
	public void test2() {
		System.out.println(cb2.getCards().equals(cb1.getCards()));
	}
	@Test
	public void test3() {
		cb3.add(cb1);
		cb4.add(cb2);
		System.out.println(cb3.equals(cb4));
		Set<CardCombination> set1 = cb3.stream().collect(Collectors.toSet());
		Set<CardCombination> set2 = cb4.stream().collect(Collectors.toSet());
		set1.addAll(set2);
		cb4.addAll(cb3);
		System.out.println(cb4);
		
		System.out.println(findDuplicateInStream(cb4.stream()));
	}
	@Test
	public void test4() {
		CardCombinationFactoryImpl factory = new CardCombinationFactoryImpl();
		List<Card> cards = Arrays.asList(card1, card2, card3,card4,card5, card6,card7,card8,card9,card10,card11,card12,card13);
		Set<CardCombination> set = factory.createFiveHands(cards);
		System.out.println("test4: "+ set.size());
	}
	
	public void test5() {
		List<Card> cards = Arrays.asList(card1, card2, card3,card4,card5, card6,card7,card8,card9,card10,card11,card12,card13);
		List<List<Card>> list = Generator.combination(cards)
			       .simple(5)
			       .stream()
			       .collect(Collectors.toList());
		System.out.println(list.size());
	}
	@Test
	public void test6() {
		List<Card> cards = Arrays.asList(card1, card2, card3,card4,card5, card6,card7,card8,card9,card10,card11,card12,card13);
		FiveCardHandsCardCombinationEmulator em = new FiveCardHandsCardCombinationEmulator(cards);
		em.emulate(cb6);
	}
	
	@Test
	public void testTriplesCardCombination() {
		CardCombinationFactoryImpl factory = new CardCombinationFactoryImpl();
		List<Card> cards = Arrays.asList(card1, card2, card3,card4,card5, card6,card7,card8,card9,card10,card11,card12,card13);
		Set<CardCombination> set = factory.createTriples(cards);
		System.out.println(set.size());
	}
	
	
	
	

	public static <T> Set<T> 
    findDuplicateInStream(Stream<T> stream) 
    { 
  
        // Set to store the duplicate elements 
        Set<T> items = new HashSet<>(); 
  
        // Return the set of duplicate elements 
        return stream 
  
            // Set.add() returns false 
            // if the element was 
            // already present in the set. 
            // Hence filter such elements 
            .filter(n -> !items.add(n)) 
  
            // Collect duplicate elements 
            // in the set 
            //.peek(System.out::println)
            .collect(Collectors.toSet()); 
    } 
}
