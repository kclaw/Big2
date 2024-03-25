package model.Big2Game;

import java.util.*;

import model.Card.Card;

/**
 * 
 */
public interface CardShuffler {

    /**
     * @param cards 
     * @return
     */
    public boolean shuffleCards(List<Card> cards);

}