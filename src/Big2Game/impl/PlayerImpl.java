package Big2Game.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import Big2Game.Brain;
import Big2Game.Decision;
import Big2Game.DecisionType;
import Big2Game.Deck;
import Big2Game.PlayRecord;
import Big2Game.PlayType;
import Big2Game.Player;
import Big2Game.ShouldAnyNumberOfPlayCardAfterThreeDiscard;
import Card.Card;
import Card.CardRank;
import Card.CardSuit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	public PlayRecord playCards(PlayType type, Deck deck) {
		PlayRecord record = new PlayRecord();
		record.discard = false;

		this.brain.findOutPossiblePlay(type, deck, this.cards).stream().forEach(s -> {
			
			  System.out.println("hi there!"); for (Card c:s.getCards())
			 System.out.println(c.getCardRank()+"///"+c.getCardSuit()+" value:~"+s.getValue
			  ().getValue());
			 /// System.out.println("\uD83C\uDCA1");
		});
		Scanner sc = new Scanner(System.in);
		int number = deck.getLastRecord() != null ? deck.getLastRecordCardsSize() : -1;
		if (type == PlayType.FREE) {
			System.out.println("choose number of cards you play (from 1 to 5)");
			number = sc.nextInt();
		}
		int[] input = new int[5];
		for (int i = 0; i < number; i++) {
			System.out.println("choose your cards (from 0 to 12)");
			input[i] = sc.nextInt();
			// System.out.println("you have played card " +
			// this.cards.get(input[i]).getCardRank() +"///"+
			// this.cards.get(input[i]).getCardSuit());
			if (input[i] == 0) {
				if (record.cards != null && record.cards.size() > 0)
					record.cards.addAll(this.cards.subList(0, 1));
				else
					record.cards = this.cards.subList(0, 1);
				// System.out.println("1"+ record.cards.get(0).getCardSuit());
			} else {
				if (record.cards != null && record.cards.size() > 0)
					record.cards.addAll(this.cards.subList(input[i], input[i] + 1));
				else
					record.cards = this.cards.subList(input[i], input[i] + 1);
				// System.out.println("2"+ record.cards.get(0).getCardSuit());
			}

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
	public Decision makeDecision(PlayType type, Deck deck) {
		PlayRecord lastRecord = deck.getLastRecord();
		System.out.println("Is last player discard? " + deck.getLastRecordDiscard());
		if (null != lastRecord && null != lastRecord.cards)
			lastRecord.cards.stream().forEach(s -> System.out.println(s.getCardRank() + "///" + s.getCardSuit()));
		else
			System.out.println("You are first player/ free noOfCard");

		while (true) {
			System.out.println("player " + name + " can choose play(p)/discard(d)");
			this.cards.stream().forEach(c -> System.out.print(c.getCardRank() + " of " + c.getCardSuit() + " ///"));
			System.out.println();
			Scanner sc = new Scanner(System.in);
			String input = sc.next();

			if (!input.isEmpty()) {
				System.out.println("You have chosen: " + input);
				if (input.equals("p")) {
					Decision decision = new Decision();
					decision.type = DecisionType.PLAY;
					return decision;
				} else if (input.equals("d")) {
					Decision decision = new Decision();
					decision.type = DecisionType.DISCARD;
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
