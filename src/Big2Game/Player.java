package Big2Game;

import java.util.List;
import Card.Card;

public interface Player {

    public void receiveCards(List<Card> cards);

    public PlayRecord playCards(PlayType type, Deck deck);

    public PlayRecord discard();
    
    public boolean haveThreeOfDiamonds();
    
    public boolean haveNoCards();
    
    public List<Card> getPlayerCards();
    
    public Decision makeDecision(PlayType type, Deck deck);
    
    public String getName();
    
    public void setName(String name);

}