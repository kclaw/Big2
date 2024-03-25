package model.Big2Game;

import java.util.*;

import model.Card.Card;

/**
 * 
 */
public interface CardDistributer {

    /**
     * @param cards 
     * @return
     */
    public boolean distributeCards(List<Card> cards);

}