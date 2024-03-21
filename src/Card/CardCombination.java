package Card;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public abstract class CardCombination {

	private static final Logger logger = LogManager.getLogger(CardCombination.class);
	public CardCombination(CardCombinationBuilder builder) {
		this.cards = builder.cards;	
		this.noOfCards = builder.noOfCards;
		this.handsType = builder.handsType;
		this.combinationType = builder.combinationType;
		this.value = builder.value;
	}
	
    public CardCombination(CardCombinationValue value, int noOfCards, Set<Card> cards,
			CardCombinationFiveCardHandsType handsType, CardCombinationType combinationType) {
		super();
		this.value = value;
		this.noOfCards = noOfCards;
		this.cards = cards;
		this.handsType = handsType;
		this.combinationType = combinationType;
	}

    public static class CardCombinationBuilder {
		public Set<Card> cards = new HashSet<>();
		public int noOfCards = 0;
		public CardCombinationValue value = new CardCombinationValue(0);
		public CardCombinationType combinationType = CardCombinationType.None;
		public CardCombinationFiveCardHandsType handsType = CardCombinationFiveCardHandsType.None;;
		public CardCombinationBuilder(Set<Card> cards) {
			this.cards.addAll(cards);
		}
		public CardCombinationBuilder() {
		}
		public CardCombinationBuilder addCard(Card card) {
			this.cards.add(card);
			return this;
		}
		public CardCombinationBuilder setCardCombinationValue(CardCombinationValue value) {
			this.value = value;
			return this;
		}
		public CardCombinationBuilder setHandType(CardCombinationFiveCardHandsType handsType) {
			this.handsType = handsType;
			return this;
		}
		public CardCombinationBuilder setCombinationType(CardCombinationType combinationType) {
			this.combinationType = combinationType;
			return this;
		}
		public CardCombination build() {
			this.noOfCards = this.cards.size();
			return null;
		}
	}
    /**
     * 
     */
    protected CardCombinationValue value;

    /**
     * 
     */
    protected int noOfCards;

    /**
     * 
     */
    protected Set<Card> cards;

    /**
     * 
     */
    protected CardCombinationFiveCardHandsType handsType;

    /**
     * 
     */
    protected CardCombinationType combinationType;

    /**
     * @param type
     */
    public void setHandsType(CardCombinationFiveCardHandsType type) {
       this.handsType = type;
    }

    /**
     * @return
     */
    public CardCombinationFiveCardHandsType getHandsType() {
        return this.handsType;
    }

    /**
     * @param type
     */
    public void setCardCombinationType(CardCombinationType type) {
        this.combinationType = type;
    }

    /**
     * @return
     */
    public CardCombinationType getCardCombinationType() {
        return this.combinationType;
    }

    /**
     * @param value
     */
    public void setValue(CardCombinationValue value) {
        this.value = value;
    }

    /**
     * @return
     */
    public CardCombinationValue getValue() {
        return this.value;
    }

    /**
     * @param number
     */
    public void setNoOfCards(int number) {
        this.noOfCards = number;
    }

    /**
     * @return
     */
    public int getNoOfCards() {
        return this.noOfCards;
    }

    /**
     * @param cards
     */
    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    /**
     * @return
     */
    public Set<Card> getCards() {
        return this.cards;
    }

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}

	@Override
	public boolean equals(Object obj) {
		//this.logger.debug("CardCombination equals() is called");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardCombination other = (CardCombination) obj;
		return Objects.equals(cards, other.cards);
	}
    





}