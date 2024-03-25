package view;

public class PlayerView {

	public void paintIntroduction(String name) {
		System.out.println("Welcome, player " + name + " can choose play(p)/discard(d)");
		System.out.println();
	}
	
	public void paintChoice(boolean isPlayCard) {
		if (isPlayCard) {
			System.out.println("You should play card");
			System.out.println("Please choose cards");
		}else {
			System.out.println("You have discard this play");
		}
			
	}
}
