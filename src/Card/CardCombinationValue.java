package Card;

import java.util.*;

/**
 * 
 */
public class CardCombinationValue implements Comparable<CardCombinationValue> {

    /**
     * Default constructor
     */
    public CardCombinationValue(int value) {
    	this.value = value;
    }

    /**
     * 
     */
    protected int value;

    /**
     * @return
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardCombinationValue other = (CardCombinationValue) obj;
		return value == other.value;
	}

	@Override
	public int compareTo(CardCombinationValue cardCombinationValue) {
		return this.value - cardCombinationValue.getValue();
	}


}