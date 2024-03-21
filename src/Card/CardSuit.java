package Card;

/**
 * 
 */
public enum CardSuit {
    Spades(4),
    Hearts(3),
    Clubs(2),
    Diamonds(1),
    AnySuit(999);

	
	public int value;
	
	CardSuit(int i) {
		this.value = i;
	}
	
	public boolean isGreaterThan(CardSuit suit) {
		return suit.value < this.value; 
	}
	
	public boolean isLessThan(CardSuit suit) {
		return suit.value > this.value; 
	}
	
	public static CardSuit getCardSuitByAbbreviation(String letter) {
		switch(letter) {
			case "D":
				return CardSuit.Diamonds;
			case "C":
				return CardSuit.Clubs;
			case "S":
				return CardSuit.Spades;
			case "H":
				return CardSuit.Hearts;
			case "AnySuit":
				return CardSuit.AnySuit;
		}
		return null;
	}
	
}