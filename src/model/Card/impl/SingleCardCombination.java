package model.Card.impl;

import model.Card.CardCombination;

public class SingleCardCombination extends CardCombination {

	public SingleCardCombination(CardCombinationBuilder builder) {
		super(builder);
	}
	
	public static class SingleCardCombinationBuilder extends CardCombination.CardCombinationBuilder {
		@Override
		public CardCombination build() {
			super.build();
			return new SingleCardCombination(this);
		}
	}


	
}
