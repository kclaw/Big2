package model.Card.impl;

import model.Card.CardCombination;

public class PairsCardCombination extends CardCombination {

	public PairsCardCombination(CardCombinationBuilder builder) {
		super(builder);
	}
	
	public static class PairsCardCombinationBuilder extends CardCombination.CardCombinationBuilder {

		@Override
		public CardCombination build() {
			super.build();
			if (this.noOfCards!=2)
				return null;
			return new PairsCardCombination(this);
		}
	}
}
