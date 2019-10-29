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
		Map<String, Game> gameStyle = new HashMap<>();
		gameStyle.put("gui", new GuiGame().init(new HeroDbManager()));
		gameStyle.put("console", new ConsoleGame().init(new HeroDbManager()));
		gameStyle.get(args[0]).start();
//		Game game = new GuiGame().init(new HeroDbManager());
//		game.start();
	}

}
