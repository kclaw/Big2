package Big2Game;

import java.util.*;

import Card.Card;

/**
 * 
 */
public interface Deck {

    public boolean addPlayRecord(PlayRecord record);

    public List<PlayRecord> getPlayRecords();
        
    public PlayRecord getLastRecord();
    
    public List<PlayRecord> getLastThreeRecords();
    
    public int getLastRecordCardsSize();
    
    public List<Card> getLastRecordCards();
    
    public boolean getLastRecordDiscard();
}