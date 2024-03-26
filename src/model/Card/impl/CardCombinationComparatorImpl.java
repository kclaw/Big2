package model.Card.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Card.Card;
import model.Card.CardCombination;
import model.Card.CardCombinationComparator;
import model.Card.CardCombinationEvaluator;

public class CardCombinationComparatorImpl implements CardCombinationComparator {

	private CardCombinationEvaluator<List<CardCombination>> evaluator = null;
	@Override
	public boolean isGreaterThan(CardCombination cc1, CardCombination cc2) {
		if (cc1.getNoOfCards()!=cc2.getNoOfCards())
			return false;
		try {
			switch (cc1.getNoOfCards()) {
				case 1:
					evaluator = new SingleCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
					List<CardCombination> template = (List<CardCombination>)evaluator.evaluate();
					Set set = new HashSet<CardCombination>(template);
					Set set2 = new HashSet<CardCombination>();
					set2.add(cc1);
					Set set3 = new HashSet<CardCombination>(template);
					Set set4 = new HashSet<CardCombination>();
					set4.add(cc2);
					if(set.retainAll(set2) && set3.retainAll(set4)) {
						return new ArrayList<CardCombination>(set).get(0).getValue().getValue()>
						new ArrayList<CardCombination>(set3).get(0).getValue().getValue();
					}
					return false;
				case 2:
					evaluator = new PairsCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
					List<CardCombination> template2 = (List<CardCombination>)evaluator.evaluate();
					Set set21 = new HashSet<CardCombination>(template2);
					Set set22 = new HashSet<CardCombination>();
					set22.add(cc1);
					Set set23 = new HashSet<CardCombination>(template2);
					Set set24 = new HashSet<CardCombination>();
					set24.add(cc2);
					if(set21.retainAll(set22) && set23.retainAll(set24)) {
						return new ArrayList<CardCombination>(set21).get(0).getValue().getValue()>
						new ArrayList<CardCombination>(set23).get(0).getValue().getValue();
					}
					return false;
				case 3:
					evaluator = new TriplesCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
					List<CardCombination> template3 = (List<CardCombination>)evaluator.evaluate();
					TriplesCardCombinationEmulator emulator = new TriplesCardCombinationEmulator();
					List<CardCombination> emulated = new ArrayList<>();
					for (int i=0;i<template3.size();i++)
						   emulated.addAll(emulator.emulate(template3.get(i)));
					Set set33 = new HashSet<CardCombination>(emulated);
					Set set34 = new HashSet<CardCombination>();
					set34.add(cc2);
					Set set31 = new HashSet<CardCombination>(emulated);
					Set set32 = new HashSet<CardCombination>();
					set32.add(cc1);
					if(set33.retainAll(set34) && set31.retainAll(set32)) {
						return new ArrayList<CardCombination>(set31).get(0).getValue().getValue()>
						new ArrayList<CardCombination>(set33).get(0).getValue().getValue();
					}
					return false;
				case 5:
					evaluator = new FiveCardHandsCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
					List<CardCombination> template4 = (List<CardCombination>)evaluator.evaluate();
					List<Card> cards = new ArrayList<Card>();
					cards.addAll(cc1.getCards());
					cards.addAll(cc2.getCards());
					FiveCardHandsCardCombinationEmulator emulator1 = new FiveCardHandsCardCombinationEmulator(cards);
					List<CardCombination> emulated1 = new ArrayList<>();
					for (int i=0;i<template4.size();i++)
						   emulated1.addAll(emulator1.emulate(template4.get(i)));
					Set set53 = new HashSet<CardCombination>(emulated1);
					Set set54 = new HashSet<CardCombination>();
					set54.add(cc2);
					Set set51 = new HashSet<CardCombination>(emulated1);
					Set set52 = new HashSet<CardCombination>();
					set52.add(cc1);
					if(set53.retainAll(set54) && set51.retainAll(set52)) {
						return new ArrayList<CardCombination>(set51).get(0).getValue().getValue()>
						new ArrayList<CardCombination>(set53).get(0).getValue().getValue();
					}
					return false;
			}
			
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		
		
		if(cc1.getValue().getValue()>cc2.getValue().getValue())
			return true;
		return false;
	}

}
