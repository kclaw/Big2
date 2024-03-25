package model.Big2Game;

import java.util.*;

import model.Card.Card;

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