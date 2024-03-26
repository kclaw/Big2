package model.Card.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Card.Card;
import model.Card.CardCombination;
import model.Card.CardCombinationEvaluator;
import model.Card.CardCombinationValueExtractor;

public class CardCombinationValueExtractorImpl implements CardCombinationValueExtractor {

	private CardCombinationEvaluator<List<CardCombination>> evaluator = null;
	
	@Override
	public int extractValue(CardCombination cc) {
		
		 try {
			 switch (cc.getNoOfCards()) {
				case 1:
					evaluator = new SingleCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
					List<CardCombination> template = (List<CardCombination>)evaluator.evaluate();
					Set set = new HashSet<CardCombination>(template);
					Set set2 = new HashSet<CardCombination>();
					set2.add(cc);
		
					if(set.retainAll(set2)) {
						return new ArrayList<CardCombination>(set).get(0).getValue().getValue();
					}
				case 2:
					evaluator = new PairsCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
					List<CardCombination> template2 = (List<CardCombination>)evaluator.evaluate();
					Set set21 = new HashSet<CardCombination>(template2);
					Set set22 = new HashSet<CardCombination>();
					set22.add(cc);
		
					if(set21.retainAll(set22)) {
						return new ArrayList<CardCombination>(set21).get(0).getValue().getValue();
					}
				case 3:
					evaluator = new TriplesCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
					List<CardCombination> template3 = (List<CardCombination>)evaluator.evaluate();
					Set set31 = new HashSet<CardCombination>(template3);
					Set set32 = new HashSet<CardCombination>();
					set32.add(cc);
		
					if(set31.retainAll(set32)) {
						return new ArrayList<CardCombination>(set31).get(0).getValue().getValue();
					}
				case 5:
					evaluator = new FiveCardHandsCardCombinationEvaluator(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
					List<CardCombination> template4 = (List<CardCombination>)evaluator.evaluate();
					List<Card> cards = new ArrayList<Card>();
					cards.addAll(cc.getCards());
					FiveCardHandsCardCombinationEmulator emulator1 = new FiveCardHandsCardCombinationEmulator(cards);
					List<CardCombination> emulated1 = new ArrayList<>();
					for (int i=0;i<template4.size();i++)
						   emulated1.addAll(emulator1.emulate(template4.get(i)));
					Set set51 = new HashSet<CardCombination>(emulated1);
					Set set52 = new HashSet<CardCombination>();
					set52.add(cc);
					
					if(set51.retainAll(set52)) {
						return new ArrayList<CardCombination>(set51).get(0).getValue().getValue();
					}
					return 0;
			}
		} catch(FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}	
}

