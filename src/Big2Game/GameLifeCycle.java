package Big2Game;

import java.util.*;

/**
 * 
 */
public interface GameLifeCycle {

    /**
     * 
     */
    public void onGameStart();

    /**
     * 
     */
    public void onDistributeCards();

    /**
     * 
     */
    public void onDetermineFirstPlayer();

    /**
     * 
     */
    public void onPlayerTurn();

    /**
     * 
     */
    public void onPlayerTurnEnded();

    /**
     * 
     */
    public void onGameEnded();

}