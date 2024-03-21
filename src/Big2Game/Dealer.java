package Big2Game;

import java.util.*;

/**
 * 
 */
public interface Dealer extends CardDistributer, CardShuffler {

    public boolean determineFirstPlayer(List<Player> players);

    public boolean determineEndGame(Player player);

    public PlayRecord askForPlay();
    
    public void setPlayers(List<Player> players);
    
    public List<Player> getPlayers();
    
    public Player getPlayerInTurn();

    public boolean chooseNextPlayer();
    
    public boolean recognisePlayers(List<Player> players);
    
    public boolean recogniseDeck(Deck deck);
}