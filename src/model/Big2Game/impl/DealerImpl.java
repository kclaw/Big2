package model.Big2Game.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Big2Game.Dealer;
import model.Big2Game.Decision;
import model.Big2Game.DecisionType;
import model.Big2Game.Deck;
import model.Big2Game.PlayRecord;
import model.Big2Game.PlayType;
import model.Big2Game.Player;
import model.Big2Game.ShouldAnyNumberOfPlayCardAfterThreeDiscard;
import model.Card.Card;

public final class DealerImpl implements Dealer{
	private Player playerInTurn = null;
	private int firstPlayerIndex = 0;
	private List<Player> players;
	private int turns = 1;
	private Deck deck = null;
	private static final Logger logger = LogManager.getLogger(DealerImpl.class);
	
	@Override
	public boolean distributeCards(List<Card> cards) {
		if(this.shuffleCards(cards)) {
			players.get(0).receiveCards(cards.subList(0, 13));
			players.get(1).receiveCards(cards.subList(13, 26));
			players.get(2).receiveCards(cards.subList(26, 39));
			players.get(3).receiveCards(cards.subList(39, 52));
			return true;
		}
		return false;
	}

	@Override
	public boolean shuffleCards(List<Card> cards) {
		return true;
	}

	@Override
	public boolean determineFirstPlayer(List<Player> players) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).haveThreeOfDiamonds()) {
				playerInTurn = players.get(i);
				firstPlayerIndex = i;
				logger.info("first player is "+i);
				return true;
			};
		}
		return false;
	}

	@Override
	public boolean determineEndGame(Player player) {
		if (player.haveNoCards()) {
			logger.info(player.getName()+" no cards");
			return true;
		}
		return false;
	}

	@Override
	public PlayRecord askForPlay() {
		ShouldAnyNumberOfPlayCardAfterThreeDiscard rule = new ShouldAnyNumberOfPlayCardAfterThreeDiscard();
		boolean meetRule = false;
		if (this.deck.getPlayRecords().size()>=3)
			meetRule = rule.isValidPlay(this.deck.getLastThreeRecords());
		PlayType type = PlayType.INHERIT;
		if (this.players.get(this.firstPlayerIndex)==playerInTurn||meetRule==true)
			type = PlayType.FREE;
		
		Decision decision = this.playerInTurn.makeDecision(type, this.deck);
		if (null!=decision && decision.type==DecisionType.PLAY) {
			PlayRecord record = this.playerInTurn.playCards(type, this.deck);
			record.turns = turns++;
			return record;
		} else if(null!=decision) {
			PlayRecord record = this.playerInTurn.discard();
			record.turns = turns++;
			return record;
		}
		return null;
	}

	@Override
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public List<Player> getPlayers() {
		return this.players;
	}

	@Override
	public Player getPlayerInTurn() {
		return this.playerInTurn;
	}

	@Override
	public boolean chooseNextPlayer() {
		int nextPlayerIndex = (this.firstPlayerIndex+this.turns-1) % 4;
		playerInTurn = players.get(nextPlayerIndex);
		logger.info("next player is " + nextPlayerIndex);
		return true;
	}

	@Override
	public boolean recognisePlayers(List<Player> players) {
		this.players = players;
		return true;
	}

	@Override
	public boolean recogniseDeck(Deck deck) {
		this.deck = deck;
		return true;
	}

}
