package view;

import java.util.List;

import model.Big2Game.PlayRecord;
import model.Big2Game.PlaySpecification;
import model.Big2Game.ShouldAnyNumberOfPlayCardAfterThreeDiscard;


public class DeckView {
	public void printDeck(List<PlayRecord> record) {
		System.out.println("*********");
		System.out.println("record -3");
		if(record.size()>=3 && null!=record.get(record.size()-3).cards)
		record.get(record.size()-3).cards
		.stream()
		.forEach(card->{
			if(null!=card)
				System.out.print(" "+card.getCardRank()+card.getCardSuit()+ " ");
			else
				System.out.println("Is Discard?"+record.get(record.size()-3).discard);
		});
		System.out.println("record -2");
		if(record.size()>=2 && null!=record.get(record.size()-2).cards)
		record.get(record.size()-2).cards
		.stream()
		.forEach(card->{
			if(null!=card)
				System.out.println(" "+card.getCardRank()+card.getCardSuit()+ " ");
			else
				System.out.println("Is Discard?"+record.get(record.size()-2).discard);
		});
		System.out.println("record -1");
		if(record.size()>=1 && null!=record.get(record.size()-1).cards)
		record.get(record.size()-1).cards
		.stream()
		.forEach(card->{
			if(null!=card)
				System.out.println(" "+card.getCardRank()+card.getCardSuit()+ " ");
			else
				System.out.println("Is Discard?"+record.get(record.size()-2).discard);
		});
		PlaySpecification spec = new ShouldAnyNumberOfPlayCardAfterThreeDiscard();
		if (spec.isValidPlay(record))
			System.out.println("You are first player/ free noOfCard");
		System.out.println("*********");
	}
}
