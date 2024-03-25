package model.Big2Game;

import java.util.*;

/**
 * 
 */
interface PlaySpecification {

    /**
     * @param records 
     * @return
     */
    public boolean isValidPlay(List<PlayRecord> records);

}