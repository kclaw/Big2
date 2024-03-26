package model.Big2Game.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.DeckController;
import controller.PlayerController;
import model.Big2Game.Dealer;
import model.Big2Game.Decision;
import model.Big2Game.DecisionType;
import model.Big2Game.Deck;
import model.Big2Game.PlayRecord;
import model.Big2Game.PlayType;
import model.Big2Game.Player;
import model.Big2Game.ShouldAnyNumberOfPlayCardAfterThreeDiscard;
import model.Card.Card;

public final class DealerImpl implements Dealer {
	private PlayerController playerControllerInTurn;
	private PlayerController firstPlayerController;
	private List<PlayerController> playerControllers;
	private int turns = 1;
	private DeckController deckController;
	private static final Logger logger = LogManager.getLogger(DealerImpl.class);
	
	@Override
	public boolean distributeCards(List<Card> cards) {
		if(this.shuffleCards(cards)) {
			playerControllers.get(0).playerReceivesCards(cards.subList(0, 13));
			playerControllers.get(1).playerReceivesCards(cards.subList(13, 26));
			playerControllers.get(2).playerReceivesCards(cards.subList(26, 39));
			playerControllers.get(3).playerReceivesCards(cards.subList(39, 52));
			return true;
		}
		return false;
	}

	@Override
	public boolean shuffleCards(List<Card> cards) {
		return true;
	}

	@Override
	public boolean determineFirstPlayer(List<PlayerController> playerControllers) {
		for (int i=0;i<playerControllers.size();i++) {
			if (playerControllers.get(i).playerHasThreeOfDiamonds()) {
				playerControllerInTurn = playerControllers.get(i);
				firstPlayerController = playerControllers.get(i);
				logger.info("first player is "+i);
				return true;
			};
		}
		return false;
	}

	@Override
	public boolean determineEndGame(PlayerController playerController) {
		if (playerController.playerHasNoCards()) {
			logger.info(playerController.getPlayerName()+" no cards");
			return true;
		}
		return false;
	}

	@Override
	public PlayRecord askForPlay() {
		ShouldAnyNumberOfPlayCardAfterThreeDiscard rule = new ShouldAnyNumberOfPlayCardAfterThreeDiscard();
		boolean meetRule = false;
		if (this.deckController.getDeckPlayRecords().size()>=3)
			meetRule = rule.isValidPlay(this.deckController.getDeckLastThreeRecords());
		PlayType type = PlayType.INHERIT;
		if (null!=firstPlayerController && firstPlayerController.equals(playerControllerInTurn)||meetRule==true) {
			type = PlayType.FREE;
			firstPlayerController = null;
		}
		playerControllerInTurn.updateView();
		Decision decision = playerControllerInTurn.playerMakesDecision(playerControllerInTurn, this.deckController);
		if (null!=decision && decision.type==DecisionType.PLAY) {
			PlayRecord record = playerControllerInTurn.playerPlaysCards(type, playerControllerInTurn, this.deckController);
			record.turns = turns++;
			return record;
		} else if(null!=decision) {
			PlayRecord record = this.playerControllerInTurn.playerDiscard();
			record.turns = turns++;
			return record;
		}
		return null;
	}



	@Override
	public boolean chooseNextPlayer() {
		playerControllerInTurn = this.playerControllerInTurn.getNextPlayerController();
		logger.info("next player is " + playerControllerInTurn.getPlayerName());
		return true;
	}

	@Override
	public boolean recognisePlayerController(List<PlayerController> playerControllers) {
		this.playerControllers = playerControllers;
		return true;
	}

	@Override
	public boolean recogniseDeckController(DeckController deckController) {
		this.deckController = deckController;
		return true;
	}

	@Override
	public List<PlayerController> getPlayerController() {
		return this.playerControllers;
	}

	@Override
	public void setPlayers(List<PlayerController> playerControllers) {
		this.playerControllers = playerControllers;
	}

	@Override
	public PlayerController getPlayerControllerInTurn() {
		return this.playerControllerInTurn;
	}

}
