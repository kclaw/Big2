package Card.impl;

import Card.CardCombination;
import Card.CardCombination.CardCombinationBuilder;

public class TriplesCardCombination extends CardCombination {

	public TriplesCardCombination(CardCombinationBuilder builder) {
		super(builder);
	}

	public static class TriplesCardCombinationBuilder extends CardCombination.CardCombinationBuilder {
		@Override
		public CardCombination build() {
			super.build();
			if (this.noOfCards!=3)
				return null;
			return new TriplesCardCombination(this);
		}
	}
}
