package Big2Game.impl;

import java.util.ArrayList;
import java.util.List;


import Big2Game.AbstractGameLifeCycle;
import Big2Game.Dealer;
import Big2Game.Deck;
import Big2Game.Game;
import Big2Game.PlayRecord;
import Big2Game.Player;
import Card.Card;

import Card.CardRank;
import Card.CardSuit;


public final class Big2Game extends AbstractGameLifeCycle implements Game {

	private Dealer dealer;
	private Deck deck;
	private List<Player> players;
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
			gameRunning = !dealer.determineEndGame(dealer.getPlayerInTurn());
			if (gameRunning)
				this.onPlayerTurnEnded();
		}
		while(gameRunning);
		this.endGame();
	}

	@Override
	public void onGameStart() {
		this.dealer = new DealerImpl();
		this.deck = new DeckImpl();
		this.players = new ArrayList<>();
		this.players.add(new PlayerImpl("1"));
		this.players.add(new PlayerImpl("2"));
		this.players.add(new PlayerImpl("3"));
		this.players.add(new PlayerImpl("4"));
		this.dealer.recognisePlayers(players);
		this.dealer.recogniseDeck(deck);
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
		/*CardCombinationFactory factory = new CardCombinationFactoryImpl();
		Set<CardCombination> combinations = factory.createFiveHands(this.players.get(0).getPlayerCards());
		System.out.println("www"+combinations);
		List<CardCombination> cc = new ArrayList<>(combinations);
		int counter = 0;
		for (CardCombination c:cc) {
			//System.out.println(c);
			for(Card card:c.getCards()) {
				System.out.println(++counter);
				System.out.println(card.getCardRank()+"///"+card.getCardSuit());
			}
			System.out.println("/////end///////");
		}*/
		/*System.out.println(CardRank.K.isGreaterThan(CardRank.A));
		System.out.println(CardRank.Four.isLessThan(CardRank.Three));
		System.out.println(CardSuit.Spades.isGreaterThan(CardSuit.Hearts));*/
	}

	@Override
	public void onDetermineFirstPlayer() {
		this.dealer.determineFirstPlayer(players);
	}

	@Override
	public void onPlayerTurn() {
		//this.dealer.getPlayerInTurn().makeDecision(this.deck);
		PlayRecord record = this.dealer.askForPlay();
		this.deck.addPlayRecord(record);
	}

	@Override
	public void onPlayerTurnEnded() {
		this.dealer.chooseNextPlayer();
	}

	@Override
	public void onGameEnded() {
		System.out.println("GAME ENDED");
	}

	
}
