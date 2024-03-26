package view;

public class PlayerView {

	public void paintIntroduction(String name) {
		System.out.println("Welcome, player " + name + " can choose play(p)/discard(d)");
		System.out.println();
	}
	
	public void paintChoice(boolean isPlayCard) {
		if (isPlayCard) {
			System.out.println("You shall play card");
			System.out.println("Please choose cards");
		}else {
			System.out.println("You have discard this play");
		}
			
	}
	
	public void paintChoosingNumberOfCards() {
		System.out.println("choose number of cards you play (from 1 to 5)");
	}
	
	public void paintPickingCards(int size) {
		System.out.println("choose your cards (from 0 to "+ size +")");
	}
}
