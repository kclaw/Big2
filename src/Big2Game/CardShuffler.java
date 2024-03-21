package Big2Game;

import java.util.*;

import Card.Card;

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