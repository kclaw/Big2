package Big2Game;

import java.util.*;

import Card.Card;
import Card.CardCombination;
import Card.impl.FiveCardHandsCardCombination;
import Card.impl.PairsCardCombination;
import Card.impl.SingleCardCombination;
import Card.impl.TriplesCardCombination;

/**
 * 
 */
public interface Brain {
    /**
     * @param lastRecord
     * @param cards
     */
    public Set<CardCombination> findOutPossiblePlay(PlayType type,Deck deck, List<Card> cards);
	
    
}