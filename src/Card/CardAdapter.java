package Card;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CardAdapter extends Card {

	private static final Logger logger = LogManager.getLogger(CardAdapter.class);
	public CardAdapter(CardRank rank, CardSuit suit) {
		super(rank, suit);
	}

	@Override
	public int hashCode() {
		logger.debug("CardAdapter hashCode() is called");
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		logger.debug("CardAdapter equals() is called");
		if (this == obj)
			return true;
		CardAdapter other = (CardAdapter)obj;
		if (this.suit==CardSuit.AnySuit && other.suit==CardSuit.Clubs)
			return true;
		if (this.suit==CardSuit.Clubs && other.suit==CardSuit.AnySuit)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	
	

}
