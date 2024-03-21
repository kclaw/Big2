package Card;

import java.util.List;
import java.util.Set;

public abstract class CardCombinationFactory {
	
	public abstract Set<CardCombination> createSingle(List<Card> cards);
	public abstract Set<CardCombination> createPairs(List<Card> cards);
	public abstract Set<CardCombination> createTriples(List<Card> cards);
	public abstract Set<CardCombination> createFiveHands(List<Card> cards);
}
