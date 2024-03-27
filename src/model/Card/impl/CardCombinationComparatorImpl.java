package model.Card.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Card.Card;
import model.Card.CardCombination;
import model.Card.CardCombinationComparator;
import model.Card.CardCombinationEvaluator;
import model.Card.CardCombinationEmulator;

public class CardCombinationComparatorImpl implements CardCombinationComparator {

	private String url = "C:\\\\Users\\\\lawre\\\\OneDrive\\\\Desktop\\\\big2-workspace3.2\\\\BigTwo\\\\src\\\\CardValue.xlsx";
	private List<CardCombinationEvaluator<List<CardCombination>>> evaluators = 
			Arrays.asList(new SingleCardCombinationEvaluator(this.url),
					new PairsCardCombinationEvaluator(this.url),
					new TriplesCardCombinationEvaluator(this.url),
					new FiveCardHandsCardCombinationEvaluator(this.url));
	
	private Map<Integer, Integer> cardNoEvaluatorMap = new HashMap<>();
	
	
	public CardCombinationComparatorImpl() {
		cardNoEvaluatorMap.put(1,  0);
		cardNoEvaluatorMap.put(2,  1);
		cardNoEvaluatorMap.put(3,  2);
		cardNoEvaluatorMap.put(5,  3);
	}

	@Override
	public boolean isGreaterThan(CardCombination cc1, CardCombination cc2) {
		if (cc1.getNoOfCards()!=cc2.getNoOfCards())
			return false;
		int index = cardNoEvaluatorMap.get(cc1.getNoOfCards());
		CardCombinationEvaluator<List<CardCombination>> evaluator = evaluators.get(index);
		List<CardCombination> template = (List<CardCombination>)evaluator.evaluate();
		List<Card> cards = new ArrayList<Card>();
		cards.addAll(cc1.getCards());
		cards.addAll(cc2.getCards());
		CardCombinationEmulator emulator = null;
		if (cc1.getNoOfCards()==5)
			emulator = new FiveCardHandsCardCombinationEmulator(cards);
		else if (cc1.getNoOfCards()==3)
			emulator = new TriplesCardCombinationEmulator();
		else if (cc1.getNoOfCards()==2)
			emulator = new PairsCardCombinationEmulator();
		else if (cc1.getNoOfCards()==1)
			emulator = new SingleCardCombinationEmulator();
		List<CardCombination> emulated = new ArrayList<>();
		for (int i=0;i<template.size();i++)
			   emulated.addAll(emulator.emulate(template.get(i)));
		Set<CardCombination> set3 = new HashSet<CardCombination>(emulated);
		Set<CardCombination> set4 = new HashSet<CardCombination>();
		set4.add(cc2);
		Set<CardCombination> set1 = new HashSet<CardCombination>(emulated);
		Set<CardCombination> set2 = new HashSet<CardCombination>();
		set2.add(cc1);
		if(set3.retainAll(set4) && set1.retainAll(set2)) {
			return new ArrayList<CardCombination>(set1).get(0).getValue().getValue()>
			new ArrayList<CardCombination>(set3).get(0).getValue().getValue();
		}
		return false;
	}

}
