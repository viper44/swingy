import java.lang.reflect.InvocationTargetException;

import game.Game;
import game.GuiGame;

import storage.HeroDbManager;


/**
 * Created by msemenov on 11/14/18.
 */
public class Main {


	public static void main(String[] args)  {
		Game game = new GuiGame().init(new HeroDbManager());
		game.start();
	}

}
