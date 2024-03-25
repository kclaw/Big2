package model.Big2Game;

import java.util.*;

import controller.DeckController;
import model.Card.Card;
import model.Card.CardCombination;
/**
 * 
 */
public interface Brain {
    /**
     * @param lastRecord
     * @param cards
     */
    public Set<CardCombination> findOutPossiblePlay(PlayType type,DeckController deckController, List<Card> cards);
	
    
}