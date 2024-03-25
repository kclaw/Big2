package model.Card;

public interface CardCombinationEvaluator <T>{
	T evaluate();
	void close();
}
