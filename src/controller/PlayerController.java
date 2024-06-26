package controller;

import java.util.List;
import java.util.Set;

import model.Big2Game.Decision;
import model.Big2Game.PlayRecord;
import model.Big2Game.PlayType;
import model.Big2Game.Player;
import model.Card.Card;
import model.Card.CardCombination;
import view.PlayerView;

public class PlayerController  {

	private Player player;
	private PlayerView view;
	private PlayerController nextPlayerController;
	
	public PlayerController(Player player, PlayerView view) {
		this.player = player;
		this.view = view;
	}
	
	public void playerReceivesCards(List<Card> cards) {
		this.player.receiveCards(cards);
	}

	
	public PlayRecord playerPlaysCards(PlayType type,PlayerController playerController, DeckController deckController) {
		return this.player.playCards(type, this, deckController);
	}


	public PlayRecord playerDiscard() {
		return this.player.discard();
	}


	public boolean playerHasThreeOfDiamonds() {
		return this.player.haveThreeOfDiamonds();
	}


	public boolean playerHasNoCards() {
		return this.player.haveNoCards();
	}


	public List<Card> getPlayerCards() {
		return this.player.getPlayerCards();
	}

	
	public Decision playerMakesDecision(PlayerController playerController, DeckController deckController) {
		return this.player.makeDecision(playerController, deckController);
	}

	public String getPlayerName() {
		return this.player.getName();
	}


	public void setName(String name) {
		this.player.setName(name);
	}

	public void setNextPlayerController(PlayerController nextPlayerController) {
		this.nextPlayerController = nextPlayerController;
	}
	
	public PlayerController getNextPlayerController() {
		return this.nextPlayerController;
	}
	
	
	public void updateView() {
		view.paintIntroduction(this.getPlayerName());
	}
	
	public void updateView(boolean isPlayCard) {
		view.paintChoice(isPlayCard);
	}
	
	public void updateViewChoosingNumberOfCard() {
		view.paintChoosingNumberOfCards();
	}
	
	public void updateViewPickingCards(int size) {
		view.paintPickingCards(size);
	}
	
	public void updateViewCardCombination(Set<CardCombination> comb) {
		view.paintCardCombination(comb);
	}
}
