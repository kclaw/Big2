package Big2Game.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import Big2Game.Brain;
import Big2Game.Deck;
import Big2Game.PlayType;
import Card.Card;
import Card.CardCombination;
import Card.CardCombinationFactory;
import Card.impl.CardCombinationFactoryImpl;

public class BrainImpl implements Brain{


	@Override
	public Set<CardCombination> findOutPossiblePlay(PlayType type, Deck deck, List<Card> cards) {
		CardCombinationFactory factory = new CardCombinationFactoryImpl();
		if (type==PlayType.INHERIT) {
			switch(deck.getLastRecordCardsSize()) {
				case 1:
					return factory.createSingle(cards);
				case 2:
					return factory.createPairs(cards);
				case 3:
					return factory.createTriples(cards);
				case 5:
					return factory.createFiveHands(cards);
			}
		} else if(type==PlayType.FREE){
			Set<CardCombination> cc = new HashSet<>();
			cc.addAll(factory.createFiveHands(cards));
			cc.addAll(factory.createTriples(cards));
			cc.addAll(factory.createPairs(cards));
			cc.addAll(factory.createSingle(cards));
			return cc;
		}
		return null;
	}

	
	
}
