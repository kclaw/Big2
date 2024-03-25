package model.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import model.Big2Game.Game;
import model.Big2Game.impl.Big2Game;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	public static void main(String[] argv) {
		Game game = new Big2Game();
		game.startGame();
		logger.debug("end");
	}
}
