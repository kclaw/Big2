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
		//int playCardSize = deck.getLastRecordCardsSize();
		
		
		
		CardCombinationFactory factory = new CardCombinationFactoryImpl();
		
	
		
		//return (List<CardCombination>) findDuplicateInStream(set1.stream()).stream().sorted((o1,o2)-> o1.getValue().compareTo(o2.getValue())).collect(Collectors.toList())*/
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
