package controller;

import java.util.List;

import model.Big2Game.Deck;
import model.Big2Game.PlayRecord;
import model.Card.Card;
import view.DeckView;

public class DeckController {
	private Deck deck;
	private DeckView view;
	
	public DeckController(Deck Deck, DeckView view) {
		this.deck = Deck;
		this.view = view;
	}
	
	public boolean addDeckPlayRecord(PlayRecord record) {
		return this.deck.addPlayRecord(record);
	}
	
	
	public void updateView() {
		view.printDeck(deck.getLastThreeRecords());
	}


	public List<PlayRecord> getDeckPlayRecords() {
		return this.deck.getPlayRecords();
	}

	
	public PlayRecord getDeckLastRecord() {
		return this.deck.getLastRecord();
	}

	
	public List<PlayRecord> getDeckLastThreeRecords() {
		return this.deck.getLastThreeRecords();
	}

	
	public int getDeckLastRecordCardsSize() {
		return this.deck.getLastRecordCardsSize();
	}

	
	public List<Card> getDeckLastRecordCards() {
		return this.deck.getLastRecordCards();
	}

	public boolean getDeckLastRecordDiscard() {
		return this.deck.getLastRecordDiscard();
	};
}
