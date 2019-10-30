import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import game.ConsoleGame;
import game.Game;
import game.GuiGame;

import storage.HeroDbManager;


/**
 * Created by msemenov on 11/14/18.
 */
public class Main {


	public static void main(String[] args)  {
		Game newOne;
		Map<String, Game> gameStyle = new HashMap<>();
//		if (args[0].equals("console")){
//			Game consoleGame = new ConsoleGame();
//			 newOne = consoleGame.init(new HeroDbManager());
//		} else if (args[0].equals("gui")){
//			newOne = new GuiGame().init(new HeroDbManager());
//		} else {
//			System.out.println("Wrong arg");
//			System.exit(1);
//		}
		gameStyle.put("gui", new GuiGame().init(new HeroDbManager()));
		gameStyle.put("console", new ConsoleGame().init(new HeroDbManager()));
//		gameStyle.get(args[0]).start();
		newOne = gameStyle.get(args[0]).init(new HeroDbManager());
		newOne.start();
//		Game game = new GuiGame().init(new HeroDbManager());
//		game.start();
	}

}
