package model.Big2Game.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.Big2Game.Brain;
import model.Big2Game.Decision;
import model.Big2Game.DecisionType;
import model.Big2Game.PlayRecord;
import model.Big2Game.PlayType;
import model.Big2Game.Player;
import model.Card.Card;
import model.Card.CardCombination;
import model.Card.CardRank;
import model.Card.CardSuit;
import model.Card.impl.CardCombinationValidateEnum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.DeckController;
import controller.PlayerController;

public class PlayerImpl implements Player {

	protected String name;
	protected List<Card> cards;
	protected Brain brain;

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
	public PlayRecord playCards(PlayType type, PlayerController playerController, DeckController deckController) {
		PlayRecord record = new PlayRecord();
		record.discard = false;
		Set<CardCombination> comb = this.brain.findOutPossiblePlay(type, deckController, this.cards);
		playerController.updateViewCardCombination(comb);
		Scanner sc = new Scanner(System.in);
		int number = deckController.getDeckLastRecord() != null ? deckController.getDeckLastRecordCardsSize() : -1;
		if (type == PlayType.FREE) {
			playerController.updateViewChoosingNumberOfCard();
			number = sc.nextInt();
		}
			boolean isValidPlay = false;
			mark:do {
					String[] input = new String[5];
					for (int i = 0; i < number; i++) {
						playerController.updateViewPickingCards(this.cards.size()-1);
						input[i] = sc.next();
						if (null==record.cards)
							record.cards = new ArrayList<Card>();
						if (input[i].charAt(0)=='d') {
							record.discard = true;
							break mark;
						}
						int range = Integer.parseInt(input[i]);
						record.cards.addAll(this.cards.subList(range, range+1));
					}
					int length = number;
					isValidPlay = CardCombinationValidateEnum.getValidationByLength(length).execute(type, record, deckController);
				} while(!isValidPlay);
		
			
		if (!record.discard) {
			record.cards = new ArrayList<>(record.cards);
			this.cards.removeAll(record.cards);
		} else {
			record.cards = null;
		}

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
					return decision;
				} else if (input.equals("d")) {
					playerController.updateView(false);
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
