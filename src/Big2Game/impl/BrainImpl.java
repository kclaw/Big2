package Big2Game.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Big2Game.Brain;
import Big2Game.Deck;
import Big2Game.PlayRecord;
import Big2Game.PlayType;
import Card.Card;
import Card.CardCombination;
import Card.CardCombinationEmulator;
import Card.CardCombinationEvaluator;
import Card.CardCombinationFactory;
import Card.impl.CardCombinationFactoryImpl;
import Card.impl.FiveCardHandsCardCombinationEmulator;
import Card.impl.PairsCardCombinationEvaluator;
import Card.impl.TriplesCardCombinationEmulator;
import Card.impl.TriplesCardCombinationEvaluator;
import Card.impl.FiveCardHandsCardCombinationEvaluator;

public class BrainImpl implements Brain{

	//List<CardCombination> cardcombination = new ArrayList<CardCombination>();

	@Override
	public List<CardCombination> findOutPossiblePlay(PlayType type, Deck deck, List<Card> cards) {
		/*FiveCardHandsCardCombinationEvaluator test = new FiveCardHandsCardCombinationEvaluator();
		List<CardCombination> value = null;
		try {
			value = test.evaluate(new FileInputStream("C:\\Users\\lawre\\Documents\\big2-workspace\\BigTwo\\src\\CardValue.xlsx"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return value;*/
		//int playCardSize = deck.getLastRecordCardsSize();
		
		
		
		CardCombinationFactory combinations = new CardCombinationFactoryImpl();
		
	
		/*CardCombinationEvaluator<List<CardCombination>> el = new FiveCardHandsCardCombinationEvaluator();
		List<CardCombination> template = null;
		try {
			template = (List<CardCombination>) el.evaluate(new FileInputStream("C:\\Users\\lawre\\OneDrive\\Desktop\\big2-workspace3.2\\BigTwo\\src\\CardValue.xlsx"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		CardCombinationEmulator emulator = new FiveCardHandsCardCombinationEmulator();
		List<CardCombination> cclist = new ArrayList<>();
		for(CardCombination cc: template)
			cclist.addAll(emulator.emulate(cc));
		
		List<CardCombination> set1 = combinations.createFiveHands(cards).stream().collect(Collectors.toList());
		System.out.println(set1.size());//1287
		set1.addAll(cclist);
		//System.out.println("tree: "+triples.size());
		System.out.println(set1.size());//19812
		System.out.println(findDuplicateInStream(set1.stream()).size());//2023
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (List<CardCombination>) findDuplicateInStream(set1.stream()).stream().sorted((o1,o2)-> o1.getValue().compareTo(o2.getValue())).collect(Collectors.toList())*/
		return null;
	}
	
	public static <T> Set<T> 
    findDuplicateInStream(Stream<T> stream) 
    { 
  
        // Set to store the duplicate elements 
        Set<T> items = new HashSet<>(); 
  
        // Return the set of duplicate elements 
        return stream 
  
            // Set.add() returns false 
            // if the element was 
            // already present in the set. 
            // Hence filter such elements 
            .filter(n -> !items.add(n)) 
  
            // Collect duplicate elements 
            // in the set 
            //.peek(System.out::println)
            .collect(Collectors.toSet()); 
    } 


	
	
}
