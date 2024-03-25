package model.Big2Game;

import java.util.List;

import controller.DeckController;
import controller.PlayerController;
import model.Card.Card;

public interface Player {

    public void receiveCards(List<Card> cards);

    public PlayRecord playCards(PlayType type, DeckController deckController);

    public PlayRecord discard();
    
    public boolean haveThreeOfDiamonds();
    
    public boolean haveNoCards();
    
    public List<Card> getPlayerCards();
    
    public Decision makeDecision(PlayerController playerController, DeckController deckController);
    
    public String getName();
    
    public void setName(String name);

}