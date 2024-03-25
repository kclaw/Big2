package model.Card;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 */
public class Card {

	private static final Logger logger = LogManager.getLogger(Card.class);
    /**
     * Default constructor
     */
    public Card(CardRank rank,CardSuit suit) {
    	this.rank = rank;
    	this.suit = suit;
    }

    /**
     * 
     */
    protected CardRank rank;

    /**
     * 
     */
    protected CardSuit suit;

    /**
     * @param rank
     */
    public void setCardRank(CardRank rank) {
        this.rank = rank;
    }

    /**
     * @return
     */
    public CardRank getCardRank() {
        return this.rank;
    }

    /**
     * @param suit
     */
    public void setCardSuit(CardSuit suit) {
        this.suit = suit;
    }

    /**
     * @return
     */
    public CardSuit getCardSuit() {
        return this.suit;
    }

	@Override
	public int hashCode() {
		//logger.debug("Card hashCode() is called");
		return Objects.hash(rank, suit.value);
		
	}

	@Override
	public boolean equals(Object obj) {
		//logger.debug("Card equals() is called");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		// Evaluation use
		if (rank==CardRank.AnyLessThan2 && other.rank==CardRank.AnyLessThan2)
			return false;
		if (rank==CardRank.AnyLessThanA && other.rank==CardRank.AnyLessThanA)
			return false;
		if (rank==CardRank.AnyLessThanK && other.rank==CardRank.AnyLessThanK)
			return false;
		if (rank==CardRank.AnyLessThanQ && other.rank==CardRank.AnyLessThanQ)
			return false;
		if (rank==CardRank.AnyLessThanJ && other.rank==CardRank.AnyLessThanJ)
			return false;
		if (rank==CardRank.AnyLessThan10 && other.rank==CardRank.AnyLessThan10)
			return false;
		if (rank==CardRank.AnyLessThan9 && other.rank==CardRank.AnyLessThan9)
			return false;
		if (rank==CardRank.AnyLessThan8 && other.rank==CardRank.AnyLessThan8)
			return false;
		if (suit==CardSuit.AnySuit && other.suit==CardSuit.AnySuit)
			return false;
		return rank == other.rank && suit == other.suit;
	}

}