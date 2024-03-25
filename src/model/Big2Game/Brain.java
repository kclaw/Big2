package model.Big2Game;

import java.util.*;

import model.Card.Card;
import model.Card.CardCombination;
import model.Card.impl.FiveCardHandsCardCombination;
import model.Card.impl.PairsCardCombination;
import model.Card.impl.SingleCardCombination;
import model.Card.impl.TriplesCardCombination;

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