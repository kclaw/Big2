package model.Big2Game.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import model.Big2Game.Brain;
import model.Big2Game.Decision;
import model.Big2Game.DecisionType;
import model.Big2Game.PlayRecord;
import model.Big2Game.PlayType;
import model.Big2Game.Player;
import model.Card.Card;
import model.Card.CardRank;
import model.Card.CardSuit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.DeckController;
import controller.PlayerController;

public class PlayerImpl implements Player {

	String name;
	List<Card> cards;
	Brain brain;

	private static final Logger logger = LogManager.getLogger(PlayerImpl.class);

	public PlayerImpl(String name) {
		this.name = name;
		this.brain = new BrainImpl();
		logger.info("player created with name: " + name);
	}

	@Override
	public void receiveCards(List<Card> cards) {
		this.cards = new ArrayList<Card>(cards);
		logger.info("player " + name + " receiveCards with length: " + cards.size());
	}

	@Override
	public PlayRecord playCards(PlayType type, DeckController deckController) {
		PlayRecord record = new PlayRecord();
		record.discard = false;

		this.brain.findOutPossiblePlay(type, deckController, this.cards).stream().forEach(s -> {
			
			  System.out.println("hi there!"); for (Card c:s.getCards())
			 System.out.println(c.getCardRank()+"///"+c.getCardSuit()+" value:~"+s.getValue
			  ().getValue());
			 /// System.out.println("\uD83C\uDCA1");
		});
		Scanner sc = new Scanner(System.in);
		int number = deckController.getDeckLastRecord() != null ? deckController.getDeckLastRecordCardsSize() : -1;
		if (type == PlayType.FREE) {
			System.out.println("choose number of cards you play (from 1 to 5)");
			number = sc.nextInt();
		}
		int[] input = new int[5];
		for (int i = 0; i < number; i++) {
			System.out.println("choose your cards (from 0 to "+(this.cards.size()-1)+")");
			input[i] = sc.nextInt();
			// System.out.println("you have played card " +
			// this.cards.get(input[i]).getCardRank() +"///"+
			// this.cards.get(input[i]).getCardSuit());
			if (null==record.cards)
				record.cards = new ArrayList<Card>();
			record.cards.addAll(this.cards.subList(i, i+1));

		}
		record.cards = new ArrayList<>(record.cards);
		this.cards.removeAll(record.cards);
		this.cards.stream().forEach(s -> System.out.println(s.getCardRank() + "***" + s.getCardSuit()));

		logger.info("player " + name + " playcards");
		return record;
	}

	@Override
	public PlayRecord discard() {
		PlayRecord record = new PlayRecord();
		record.discard = true;
		logger.info("player " + name + " discards");
		return record;
	}

	@Override
	public boolean haveThreeOfDiamonds() {
		for (Card card : cards)
			if (card.getCardRank().equals(CardRank.Three) && card.getCardSuit().equals(CardSuit.Diamonds)) {
				logger.info("player " + name + " haveThreeOfDiamonds");
				return true;
			}
		return false;
	}

	@Override
	public boolean haveNoCards() {
		return this.cards.isEmpty();
	}

	@Override
	public List<Card> getPlayerCards() {
		return this.cards;
	}

	@Override
	public Decision makeDecision(PlayerController playerController, DeckController deckController) {
		deckController.updateView();
		while (true) {
			playerController.updateView();
			this.cards.stream().forEach(c -> System.out.print(c.getCardRank() + " of " + c.getCardSuit() + " ///"));
			System.out.println();
			Scanner sc = new Scanner(System.in);
			String input = sc.next();

			if (!input.isEmpty()) {
				if (input.equals("p")) {
					playerController.updateView(true);
					Decision decision = new Decision();
					decision.type = DecisionType.PLAY;
					//sc.close();
					return decision;
				} else if (input.equals("d")) {
					playerController.updateView(false);
					Decision decision = new Decision();
					decision.type = DecisionType.DISCARD;
					//sc.close();
					return decision;
				} else {
					break;
				}
			}
		}
		return null;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
