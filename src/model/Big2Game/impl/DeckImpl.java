package model.Big2Game.impl;

import java.util.ArrayList;
import java.util.List;

import model.Big2Game.Deck;
import model.Big2Game.PlayRecord;
import model.Card.Card;

public class DeckImpl implements Deck {

	private List<PlayRecord> records;
	
	public DeckImpl() {
		this.records = new ArrayList<PlayRecord>();
	}
	
	@Override
	public boolean addPlayRecord(PlayRecord record) {
		return this.records.add(record);
	}

	@Override
	public List<PlayRecord> getPlayRecords() {
		return this.records;
	}

	@Override
	public PlayRecord getLastRecord() {
		if(this.records.size()==0)
			return null;
		return this.records.get(this.records.size()-1);
	}

	@Override
	public List<PlayRecord> getLastThreeRecords() {
		return new ArrayList<PlayRecord>(this.getPlayRecords().subList(Math.max(this.getPlayRecords().size() - 3, 0), this.getPlayRecords().size()));
	}

	@Override
	public int getLastRecordCardsSize() {
		if(getLastRecord()!=null && getLastRecord().cards!=null )
			return getLastRecord().cards.size();
		return 0;
	}

	@Override
	public List<Card> getLastRecordCards() {
		if (getLastRecord()!=null && getLastRecord().cards!=null)
			return getLastRecord().cards;
		return null;
	}

	@Override
	public boolean getLastRecordDiscard() {
		if (getLastRecord()!=null)
			return getLastRecord().discard;
		return false;
	}
	
	

}
