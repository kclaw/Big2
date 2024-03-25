package model.Big2Game;

import java.util.*;

import controller.DeckController;
import controller.PlayerController;

/**
 * 
 */
public interface Dealer extends CardDistributer, CardShuffler {

    public boolean determineFirstPlayer(List<PlayerController> playersController);

    public boolean determineEndGame(PlayerController playerController);

    public PlayRecord askForPlay();
    
    public void setPlayers(List<PlayerController> playerController);
    
    public List<PlayerController> getPlayerController();
    
    public PlayerController getPlayerControllerInTurn();

    public boolean chooseNextPlayer();
    
    public boolean recognisePlayerController(List<PlayerController> playerControllers);
    
    public boolean recogniseDeckController(DeckController deck);
}