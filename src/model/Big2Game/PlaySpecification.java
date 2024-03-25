package model.Big2Game;

import java.util.*;

/**
 * 
 */
public interface PlaySpecification {

    /**
     * @param records 
     * @return
     */
    public boolean isValidPlay(List<PlayRecord> records);

}