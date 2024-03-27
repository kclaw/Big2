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
import model.Card.CardCombinationEvaluator;
import model.Card.CardCombinationValueExtractor;
import model.Card.CardCombinationEmulator;

public class CardCombinationValueExtractorImpl implements CardCombinationValueExtractor {

	private String url = "C:\\\\Users\\\\lawre\\\\OneDrive\\\\Desktop\\\\big2-workspace3.2\\\\BigTwo\\\\src\\\\CardValue.xlsx";
	private List<CardCombinationEvaluator<List<CardCombination>>> evaluators = 
			Arrays.asList(new SingleCardCombinationEvaluator(this.url),
					new PairsCardCombinationEvaluator(this.url),
					new TriplesCardCombinationEvaluator(this.url),
					new FiveCardHandsCardCombinationEvaluator(this.url));
	private Map<Integer, Integer> cardNoEvaluatorMap = new HashMap<>();
	
	
	public CardCombinationValueExtractorImpl() {
		cardNoEvaluatorMap.put(1,  0);
		cardNoEvaluatorMap.put(2,  1);
		cardNoEvaluatorMap.put(3,  2);
		cardNoEvaluatorMap.put(5,  3);
	}
	
	
	@Override
	public int extractValue(CardCombination cc) {
		int index = cardNoEvaluatorMap.get(cc.getNoOfCards());
		CardCombinationEvaluator<List<CardCombination>> evaluator = evaluators.get(index);
		List<CardCombination> template = (List<CardCombination>)evaluator.evaluate();
		List<Card> cards = new ArrayList<Card>();
		cards.addAll(cc.getCards());
		CardCombinationEmulator emulator = null;
		if (cc.getNoOfCards()==5)
			emulator = new FiveCardHandsCardCombinationEmulator(cards);
		else if(cc.getNoOfCards()==3)
			emulator = new TriplesCardCombinationEmulator();
		else if(cc.getNoOfCards()==2)
			emulator = new PairsCardCombinationEmulator();
		else if(cc.getNoOfCards()==1)
			emulator = new SingleCardCombinationEmulator();
		List<CardCombination> emulated = new ArrayList<>();
		for (int i=0;i<template.size();i++)
			   emulated.addAll(emulator.emulate(template.get(i)));	
		Set set = new HashSet<CardCombination>(emulated);
		Set set2 = new HashSet<CardCombination>();
		set2.add(cc);
		if(set.retainAll(set2)) {
			return new ArrayList<CardCombination>(set).get(0).getValue().getValue();
		} 
		return 0;
	}	
}

