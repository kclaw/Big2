package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Big2Game.Game;
import Big2Game.impl.Big2Game;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	public static void main(String[] argv) {
		Game game = new Big2Game();
		game.startGame();
		//new TestFiveCardHandsCardCombination().test();
		//new TestFiveCardHandsCardCombination().test2();
		//new TestFiveCardHandsCardCombination().test3();
		//new TestFiveCardHandsCardCombination().testSingleCardCombination();
		//new TestFiveCardHandsCardCombination().test5();
		/*Answer answer = Dialogue.ask(Question.of(1, "hi???"), Retry.of(3));
		System.out.println(answer.accept());
		Answer answer2 = Dialogue.ask(Question.of(1, "hi!!!"), Retry.of(3));
		System.out.println(answer2.accept());*/
		logger.debug("end");
		
		/*Card card1 = new CardAdapter(CardRank.Three,CardSuit.Clubs);
		Card card2 = new CardAdapter(CardRank.Three,CardSuit.AnySuit);
		//System.out.println(card1.equals(card2));
		Set<Card> cardSet1 = new HashSet<>();
		Set<Card> cardSet2 = new HashSet<>();
		cardSet1.add(card1);
		cardSet2.add(card2);
		System.out.println(cardSet1.containsAll(cardSet2));
		CardCombination cb1= new SingleCardCombination.SingleCardCombinationBuilder().addCard(card1).build();
		CardCombination cb2= new SingleCardCombination.SingleCardCombinationBuilder().addCard(card2).build();*/
		//System.out.println(cb1.equals(cb2));
		/*CardCombination comb = new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
				//.addCard(new Card(CardRank.A,CardSuit.AnySuit))
				//.addCard(new Card(CardRank.Two,CardSuit.AnySuit))
				//.addCard(new Card(CardRank.Three,CardSuit.AnySuit))
				//.addCard(new Card(CardRank.Four,CardSuit.AnySuit))
				//.addCard(new Card(CardRank.Five,CardSuit.Diamonds))
				.addCard(new Card(CardRank.Three,CardSuit.Diamonds))
				.addCard(new Card(CardRank.Three,CardSuit.Spades))
				.addCard(new Card(CardRank.Three,CardSuit.Clubs))
				.addCard(new Card(CardRank.Two,CardSuit.Diamonds))
				.addCard(new Card(CardRank.Two,CardSuit.Spades))
				.build();
		List<CardCombination> comblist=new FiveCardHandsCardCombinationEmulator().emulate(comb);
		 System.out.println("comb: "+comblist.size());
		for (int i=0;i<comblist.size();i++) {
			comblist.get(i).getCards().forEach(s->{System.out.println(">>>>"+s.getCardRank()+"///"+s.getCardSuit());});
			System.out.println("====================");
		}*/
		/*Generator.cartesianProduct(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6),Arrays.asList(7, 8, 9),Arrays.asList(10,11,12))
        .stream()
        .forEach(System.out::println);*/
	}
}
