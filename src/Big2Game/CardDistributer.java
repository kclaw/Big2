package Big2Game;

import java.util.*;

import Card.Card;

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