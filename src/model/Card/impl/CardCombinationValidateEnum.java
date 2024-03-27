package model.Card.impl;

import controller.DeckController;
import model.Big2Game.PlayRecord;
import model.Big2Game.PlayType;
import model.Card.CardCombination;
import model.Card.CardCombinationComparator;
import model.Card.CardCombinationValueExtractor;

public enum CardCombinationValidateEnum {
	
	Single(1){

		@Override
		public boolean execute(PlayType type, PlayRecord record, DeckController deckController) {
			CardCombinationValueExtractor extractor = new CardCombinationValueExtractorImpl();
			CardCombinationComparator comparator = new CardCombinationComparatorImpl();
			CardCombination c = new SingleCardCombination.SingleCardCombinationBuilder()
					.addCard(record.cards.get(0))
					.build();
					if (type==PlayType.INHERIT) {
						CardCombination c2 = new CardListToCardCombinationConverter().convert(deckController.getDeckLastRecord().cards);
						if (extractor.extractValue(c)>0 && comparator.isGreaterThan(c, c2)) {
							return true;
						}
					} else if (type==PlayType.FREE) {
						if (extractor.extractValue(c)>0)
							return true;
					}
		    return false;
		}
		
	},
	Pairs(2) {

		@Override
		public boolean execute(PlayType type, PlayRecord record, DeckController deckController) {
			CardCombinationValueExtractor extractor = new CardCombinationValueExtractorImpl();
			CardCombinationComparator comparator = new CardCombinationComparatorImpl();
			CardCombination c1 = new PairsCardCombination.PairsCardCombinationBuilder()
					.addCard(record.cards.get(0))
					.addCard(record.cards.get(1))
					.build();
					if (type==PlayType.INHERIT) {
						CardCombination c2 = new CardListToCardCombinationConverter().convert(deckController.getDeckLastRecord().cards);
						if (extractor.extractValue(c1)>0 && comparator.isGreaterThan(c1, c2))
							return true;
					} else if (type==PlayType.FREE) {
						if (extractor.extractValue(c1)>0)
							return true;
					}
					return false;
		}

		

	},
	Triple(3) {

		@Override
		public boolean execute(PlayType type, PlayRecord record, DeckController deckController) {
			CardCombinationValueExtractor extractor = new CardCombinationValueExtractorImpl();
			CardCombinationComparator comparator = new CardCombinationComparatorImpl();
			CardCombination c11 = new TriplesCardCombination.TriplesCardCombinationBuilder()
					.addCard(record.cards.get(0))
					.addCard(record.cards.get(1))
					.addCard(record.cards.get(2))
					.build();
					if (type==PlayType.INHERIT) {
						CardCombination c2 = new CardListToCardCombinationConverter().convert(deckController.getDeckLastRecord().cards);
						if (extractor.extractValue(c11)>0 && comparator.isGreaterThan(c11, c2))
							return true;
					} else if (type==PlayType.FREE) {
						if (extractor.extractValue(c11)>0)
							return true;
					}
			return false;
		}

	
	
	},
	FiveCardHands(5) {

		@Override
		public boolean execute(PlayType type, PlayRecord record, DeckController deckController) {
			CardCombinationValueExtractor extractor = new CardCombinationValueExtractorImpl();
			CardCombinationComparator comparator = new CardCombinationComparatorImpl();
			CardCombination c111 = new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
					.addCard(record.cards.get(0))
					.addCard(record.cards.get(1))
					.addCard(record.cards.get(2))
					.addCard(record.cards.get(3))
					.addCard(record.cards.get(4))
					.build();
					if (type==PlayType.INHERIT) {
						CardCombination c2 = new CardListToCardCombinationConverter().convert(deckController.getDeckLastRecord().cards);
						if (extractor.extractValue(c111)>0 && comparator.isGreaterThan(c111, c2))
							return true;
					} else if (type==PlayType.FREE) {
						if (extractor.extractValue(c111)>0)
							return true;
					}
				return false;
		}

	
	};
	
	private int index;
	CardCombinationValidateEnum(int i) {
		this.index = i;
	}
	public int getIndex() {
		return this.index;
	}
	
	
	public abstract boolean execute(PlayType type, PlayRecord record, DeckController deckController);
	
	public static CardCombinationValidateEnum getValidationByLength(int length) {
		for(CardCombinationValidateEnum validateEnum: CardCombinationValidateEnum.values())
			if (validateEnum.getIndex()==length)
				return validateEnum;
        throw new IllegalArgumentException("CardCombinationValidate [" + length + "] is not a valid length!");
	}

}
