package model.Card.impl;

import model.Card.CardCombination;

public class FiveCardHandsCardCombination extends CardCombination {

	public FiveCardHandsCardCombination(CardCombinationBuilder builder) {
		super(builder);
	}
	
	public static class FiveCardHandsCombinationBuilder extends CardCombination.CardCombinationBuilder {
		@Override
		public CardCombination build() {
			super.build();
			if(this.noOfCards!=5) {
				return null;
			}
			return new FiveCardHandsCardCombination(this);
		}
	}

}
