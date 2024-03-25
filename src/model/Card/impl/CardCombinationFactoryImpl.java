package model.Card.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.paukov.combinatorics3.Generator;

import model.Card.Card;
import model.Card.CardCombination;
import model.Card.CardCombinationEvaluator;
import model.Card.CardCombinationFactory;



public final class CardCombinationFactoryImpl extends CardCombinationFactory {

private static CardCombinationFactoryImpl instance  = new CardCombinationFactoryImpl();
	
	public static CardCombinationFactoryImpl getInstance(){
	        return instance;
	}
	private CardCombinationEvaluator<List<CardCombination>> evaluator = null;
	private List<CardCombination> singles = null;
	private List<CardCombination> pairs = null;
	private List<CardCombination> triples = null;
	private List<CardCombination> fiveCardHands = null;
	
	public CardCombinationFactoryImpl() {
		try {
			evaluator = new TriplesCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
			triples = (List<CardCombination>) evaluator.evaluate();
			evaluator = new PairsCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
			pairs = (List<CardCombination>) evaluator.evaluate();
			evaluator = new SingleCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
			singles = (List<CardCombination>) evaluator.evaluate();
			evaluator = new FiveCardHandsCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
			fiveCardHands = (List<CardCombination>) evaluator.evaluate();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Set<CardCombination> createSingle(List<Card> cards) {
		List<CardCombination> template2 = singles;
		SingleCardCombinationEmulator emulator = new SingleCardCombinationEmulator();
		final List<CardCombination> template3 = new ArrayList<>(template2);
		List<CardCombination> emulated = new ArrayList<>();
		
		for (int i=0;i<template3.size();i++)
			   emulated.addAll(emulator.emulate(template3.get(i)));
		
		List<CardCombination> emulated2 = emulated.stream()
				.filter(cc->{
					if(cc.getCards().retainAll(cards))
						return false;
					return true;
				}).collect(Collectors.toList());
		Set<CardCombination> list = Generator.combination(cards)
	       .simple(1)
	       .stream()
	       .map(s-> emulator.emulate(new SingleCardCombination.SingleCardCombinationBuilder()
					.addCard(s.get(0))
					.build()))
	      .map(m-> {
	    	  Set<CardCombination> set = new HashSet<CardCombination>(emulated2);
	    		 if (set.retainAll(m)) 
	    			   return set;
				return null;
				})
	       .filter(Objects::nonNull)
	       .flatMap(Collection::stream)
	       .collect(Collectors.toSet());
		return list;
	}

	@Override
	public Set<CardCombination> createPairs(List<Card> cards) {
		List<CardCombination> template2 = pairs;
		PairsCardCombinationEmulator emulator = new PairsCardCombinationEmulator();
		final List<CardCombination> template3 = new ArrayList<>(template2);
		List<CardCombination> emulated = new ArrayList<>();
		for (int i=0;i<template3.size();i++)
			   emulated.addAll(emulator.emulate(template3.get(i)));
		
		List<CardCombination> emulated2 = emulated.stream()
				.filter(cc->{
					if(cc.getCards().retainAll(cards))
						return false;
					return true;
				}).collect(Collectors.toList());
		
		Set<CardCombination> list = Generator.combination(cards)
	       .simple(2)
	       .stream()
	       .map(s-> emulator.emulate(new PairsCardCombination.PairsCardCombinationBuilder()
					.addCard(s.get(0))
					.addCard(s.get(1))
					.build()))
	      .map(m-> {
	    	  Set<CardCombination> set = new HashSet<CardCombination>(emulated2);
	    		 if (set.retainAll(m)) 
	    			   return set;
				return null;
				}) 
	       .filter(Objects::nonNull)
	       .flatMap(Collection::stream)
	       .collect(Collectors.toSet());
		return list;
	}

	@Override
	public Set<CardCombination> createTriples(List<Card> cards) {
		TriplesCardCombinationEmulator emulator = new TriplesCardCombinationEmulator();
		List<CardCombination> template2 = triples;
		final List<CardCombination> template3 = new ArrayList<>(template2);
		List<CardCombination> emulated = new ArrayList<>();
		for (int i=0;i<template3.size();i++)
			   emulated.addAll(emulator.emulate(template3.get(i)));
		return cards.stream()
				.flatMap(card1 -> cards.stream().flatMap(card2->{
					return cards.stream().map(card3 -> {
						if (card1.getCardRank().equals(card2.getCardRank()) && card2.getCardRank().equals(card3.getCardRank())) 
						{
							Set<CardCombination> cc = new HashSet<>();
								cc.add(new TriplesCardCombination.TriplesCardCombinationBuilder().addCard(card1).addCard(card2).addCard(card3).build());
							Set<CardCombination> template4 = new HashSet<>(emulated);
							if(template4.retainAll(cc) && template4.size()>0)
								return new TriplesCardCombination.TriplesCardCombinationBuilder()
									.addCard(card1)
									.addCard(card2)
									.addCard(card3)
									.setCardCombinationValue(new ArrayList<>(template4).get(0).getValue())
									.build();							
						}
						return null;
					});
				}))
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());
		
		
	}

	@Override
	public Set<CardCombination> createFiveHands(List<Card> cards) { 
		List<CardCombination> template2 = fiveCardHands;
		FiveCardHandsCardCombinationEmulator emulator = new FiveCardHandsCardCombinationEmulator(cards);
		final List<CardCombination> template3 = new ArrayList<>(template2);
		List<CardCombination> emulated = new ArrayList<>();
		for (int i=0;i<template3.size();i++)
			   emulated.addAll(emulator.emulate(template3.get(i)));
		
		List<CardCombination> emulated2 = emulated.stream()
				.filter(cc->{
					if(cc.getCards().retainAll(cards))
						return false;
					return true;
				}).collect(Collectors.toList());
		
		List<CardCombination> list = Generator.combination(cards)
	       .simple(5)
	       .stream()
	       .map(s-> emulator.emulate(new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
					.addCard(s.get(0))
					.addCard(s.get(1))
					.addCard(s.get(2))
					.addCard(s.get(3))
					.addCard(s.get(4))
					.build()))
	      .map(m-> {
	    	  Set<CardCombination> set = new HashSet<CardCombination>(emulated2);
	    		 if (set.retainAll(m)) 
	    			   return set;
				return null;
				})
	       .filter(Objects::nonNull)
	       .flatMap(Collection::stream)
	       .collect(Collectors.toList());
		return list.stream().collect(Collectors.toSet());
			
	}
}
