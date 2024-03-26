package model.Big2Game.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.DeckController;
import controller.PlayerController;
import model.Big2Game.AbstractGameLifeCycle;
import model.Big2Game.Dealer;
import model.Big2Game.Game;
import model.Big2Game.PlayRecord;
import model.Card.Card;

import model.Card.CardRank;
import model.Card.CardSuit;
import view.DeckView;
import view.PlayerView;


public final class Big2Game extends AbstractGameLifeCycle implements Game {

	private Dealer dealer;
	private DeckController deckController;
	private List<PlayerController> playerControllers;
	private List<Card> cards;

	@Override
	public void startGame() {
		this.onGameStart();
		this.playGame();
	}

	@Override
	public void endGame() {
		this.onGameEnded();
	}
	
	@Override
	public void playGame() {
		this.onDistributeCards();
		this.onDetermineFirstPlayer();
		
		boolean gameRunning;
		do {
			this.onPlayerTurn();
			gameRunning = !dealer.determineEndGame(dealer.getPlayerControllerInTurn());
			if (gameRunning)
				this.onPlayerTurnEnded();
		}
		while(gameRunning);
		this.endGame();
	}

	@Override
	public void onGameStart() {
		this.dealer = new DealerImpl();
		this.deckController = new DeckController(new DeckImpl(), new DeckView());
		this.playerControllers = new ArrayList<>();
		PlayerController playerController1 = new PlayerController(new PlayerImpl("1"), new PlayerView());
		PlayerController playerController2 = new PlayerController(new PlayerImpl("2"), new PlayerView());
		PlayerController playerController3 = new PlayerController(new PlayerImpl("3"), new PlayerView());
		PlayerController playerController4 = new PlayerController(new PlayerImpl("4"), new PlayerView());
		playerController1.setNextPlayerController(playerController2);
		playerController2.setNextPlayerController(playerController3);
		playerController3.setNextPlayerController(playerController4);
		playerController4.setNextPlayerController(playerController1);
		this.playerControllers.add(playerController1);
		this.playerControllers.add(playerController2);
		this.playerControllers.add(playerController3);
		this.playerControllers.add(playerController4);
		this.dealer.recognisePlayerController(this.playerControllers);
		this.dealer.recogniseDeckController(this.deckController);
		this.cards = new ArrayList<>();
		this.cards.add(new Card(CardRank.A, CardSuit.Spades));
		this.cards.add(new Card(CardRank.A, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.A, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.A, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.Two, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.Two, CardSuit.Spades));
		this.cards.add(new Card(CardRank.Two, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.Two, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.Three, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.Three, CardSuit.Spades));
		this.cards.add(new Card(CardRank.Three, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.Three, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.Four, CardSuit.Spades));
		this.cards.add(new Card(CardRank.Four, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.Four, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.Four, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.Five, CardSuit.Spades));
		this.cards.add(new Card(CardRank.Five, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.Five, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.Five, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.Six, CardSuit.Spades));
		this.cards.add(new Card(CardRank.Six, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.Six, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.Six, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.Seven, CardSuit.Spades));
		this.cards.add(new Card(CardRank.Seven, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.Seven, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.Seven, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.Eight, CardSuit.Spades));
		this.cards.add(new Card(CardRank.Eight, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.Eight, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.Eight, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.Nine, CardSuit.Spades));
		this.cards.add(new Card(CardRank.Nine, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.Nine, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.Nine, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.Ten, CardSuit.Spades));
		this.cards.add(new Card(CardRank.Ten, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.Ten, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.Ten, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.J, CardSuit.Spades));
		this.cards.add(new Card(CardRank.J, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.J, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.J, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.Q, CardSuit.Spades));
		this.cards.add(new Card(CardRank.Q, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.Q, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.Q, CardSuit.Diamonds));
		this.cards.add(new Card(CardRank.K, CardSuit.Spades));
		this.cards.add(new Card(CardRank.K, CardSuit.Hearts));
		this.cards.add(new Card(CardRank.K, CardSuit.Clubs));
		this.cards.add(new Card(CardRank.K, CardSuit.Diamonds));

	}

	@Override
	public void onDistributeCards() {
		this.dealer.distributeCards(cards);
	}

	@Override
	public void onDetermineFirstPlayer() {
		this.dealer.determineFirstPlayer(playerControllers);
	}

	@Override
	public void onPlayerTurn() {
		PlayRecord record = this.dealer.askForPlay();
		this.deckController.addDeckPlayRecord(record);
	}

	@Override
	public void onPlayerTurnEnded() {
		this.dealer.chooseNextPlayer();
	}

	@Override
	public void onGameEnded() {
		System.out.println("GAME ENDED");
		Scanner sc = new Scanner(System.in);
		sc.close();
	}

	
}
