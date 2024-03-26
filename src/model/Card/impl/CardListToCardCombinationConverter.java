package model.Card.impl;

import java.util.List;

import model.Card.Card;
import model.Card.CardCombination;

public class CardListToCardCombinationConverter {
	
	public CardCombination convert(List<Card> cards) {
		switch(cards.size()) {
			case 1:
				return new SingleCardCombination.SingleCardCombinationBuilder()
						.addCard(cards.get(0))
						.build();
			case 2:
				return new PairsCardCombination.PairsCardCombinationBuilder()
						.addCard(cards.get(0))
						.addCard(cards.get(1))
						.build();
			case 3:
				return new TriplesCardCombination.TriplesCardCombinationBuilder()
						.addCard(cards.get(0))
						.addCard(cards.get(1))
						.addCard(cards.get(2))
						.build();
			case 5:
				return new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
						.addCard(cards.get(0))
						.addCard(cards.get(1))
						.addCard(cards.get(2))
						.addCard(cards.get(3))
						.addCard(cards.get(4))
						.build();
		}
		return null;
	}

}
