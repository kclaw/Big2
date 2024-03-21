package Card;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public enum CardRank {
	A(12),
	Two(13),
	Three(1),
	Four(2),
	Five(3),
	Six(4),
	Seven(5),
	Eight(6),
	Nine(7),
	Ten(8),
	J(9),
	Q(10),
	K(11),
	AnyLessThan8(999),
	AnyLessThan9(999),
	AnyLessThan10(999),
	AnyLessThanJ(999),
	AnyLessThanQ(999),
	AnyLessThanK(999),
	AnyLessThanA(999),
	AnyLessThan2(999),
	AnyPairs(999),
	AnyRank(999);
	
	public int value;
	private static Map<String, CardRank> indexCardRankMap;
	
	CardRank(int i) {
		this.value = i;
			
	}
	
	static {
		indexCardRankMap = new HashMap<>();
		indexCardRankMap.put("A", CardRank.A);
		indexCardRankMap.put("2", CardRank.Two);
		indexCardRankMap.put("3", CardRank.Three);
		indexCardRankMap.put("4", CardRank.Four);
		indexCardRankMap.put("5", CardRank.Five);
		indexCardRankMap.put("6", CardRank.Six);
		indexCardRankMap.put("7", CardRank.Seven);
		indexCardRankMap.put("8", CardRank.Eight);
		indexCardRankMap.put("9", CardRank.Nine);
		indexCardRankMap.put("10", CardRank.Ten);
		indexCardRankMap.put("J", CardRank.J);
		indexCardRankMap.put("Q", CardRank.Q);
		indexCardRankMap.put("K", CardRank.K);
		indexCardRankMap.put("Any<8", CardRank.AnyLessThan8);
		indexCardRankMap.put("Any<9", CardRank.AnyLessThan9);
		indexCardRankMap.put("Any<10", CardRank.AnyLessThan10);
		indexCardRankMap.put("Any<J", CardRank.AnyLessThanJ);
		indexCardRankMap.put("Any<Q", CardRank.AnyLessThanQ);
		indexCardRankMap.put("Any<K", CardRank.AnyLessThanK);
		indexCardRankMap.put("Any<A", CardRank.AnyLessThanA);
		indexCardRankMap.put("Any<2", CardRank.AnyLessThan2);
		indexCardRankMap.put("AnyPairs", CardRank.AnyPairs);
		indexCardRankMap.put("AnyRank", CardRank.AnyRank);
	}
	
	public boolean isGreaterThan(CardRank rank) {
		return rank.value < this.value;
	}
	
	public boolean isLessThan(CardRank rank) {
		return rank.value > this.value;
	}
	
	public static CardRank getCardRankByNumber(String number) {
		return indexCardRankMap.get(number);
	}
}