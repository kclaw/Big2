package model.Card.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.paukov.combinatorics3.Generator;

import model.Card.Card;
import model.Card.CardCombination;
import model.Card.CardCombinationEmulator;
import model.Card.CardCombinationFactory;
import model.Card.CardCombinationFiveCardHandsType;
import model.Card.CardRank;
import model.Card.CardSuit;

public class FiveCardHandsCardCombinationEmulator implements CardCombinationEmulator{
	
	protected List<Card> cards;
	protected List<CardCombination> pairs;
	protected List<CardCombination> triples;
	protected CardCombinationFactory factory;
	protected List<CardCombination> testCases;
	
	public FiveCardHandsCardCombinationEmulator(List<Card> cards) {
		this.cards = cards;
		factory = CardCombinationFactoryImpl.getInstance();
		pairs = factory.createPairs(this.cards).stream().collect(Collectors.toList());
		triples = factory.createTriples(this.cards).stream().collect(Collectors.toList());
		testCases = new ArrayList<>();
	}
	
	@Override
	public List<CardCombination> emulate(CardCombination combination) {
		if (combination.getNoOfCards()!=5)
			return null;
		List<Card> cards = combination.getCards().stream().collect(Collectors.toList());
		Set<List<Card>> cardsSet= Generator.cartesianProduct(elaborate(cards.get(0)), elaborate(cards.get(1)), elaborate(cards.get(2)),elaborate(cards.get(3)),elaborate(cards.get(4))).stream().collect(Collectors.toSet());
		
		// removing duplicates in card set with size of 5.
		return cardsSet.stream().map(dup->{
			Set<Card> set = new HashSet<>();
			set.addAll(dup);
			return set;
		})
		.filter(list-> list.size()==5)
		.map(list->list.stream().collect(Collectors.toList()))
		.distinct()
		.map(s-> new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
				.addCard(s.get(0))
				.addCard(s.get(1))
				.addCard(s.get(2))
				.addCard(s.get(3))
				.addCard(s.get(4))
				.setCardCombinationValue(combination.getValue())
				.setHandType(combination.getHandsType())
				.build())
		.map(s->{
			if (s.getHandsType()!=CardCombinationFiveCardHandsType.FullHouse)
				return s;
				List<CardCombination> testCases = new ArrayList<>();
				for (int i=0;i<triples.size();i++) {
					List<Card> triplesCards = triples.get(i).getCards().stream().collect(Collectors.toList());
					for (int j=0;j<pairs.size();j++) {
						List<Card> pairsCards = pairs.get(j).getCards().stream().collect(Collectors.toList());
						testCases.add(new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
							.addCard(triplesCards.get(0))
							.addCard(triplesCards.get(1))
							.addCard(triplesCards.get(2))
							.addCard(pairsCards.get(0))
							.addCard(pairsCards.get(1))
							.build());
					}
				}
				if (!new HashSet<>(testCases).add(s)) {
					testCases.clear();
					return s;
				}
				testCases.clear();
			return null;
		})
		.filter(Objects::nonNull)
		.collect(Collectors.toList());
	}

	private List<Card> elaborate(Card card){
		List<Card> cardList = new ArrayList<>();
		switch(card.getCardRank()) {
			case AnyPairs:
				for (int i=0;i<this.pairs.size();i++) {
						List<Card> cards = this.pairs.get(i).getCards().stream().collect(Collectors.toList());
						cardList.add(cards.get(0));
						cardList.add(cards.get(1));
				}
				break;
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
		

		return cardList.stream().collect(Collectors.toList());

	}
}
