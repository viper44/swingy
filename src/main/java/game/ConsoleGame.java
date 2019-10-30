package game;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import view.MainMenuView;
import view.cons.*;



@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsoleGame extends Game {

	@Override
	protected MainMenuView mainMenuView() {
		return new MainMenuConsoleView();
	}

	@Override
	void setSequence() {
		context.setSequence(2);
	}

	@Override
	protected Game initGame() {
		return new ConsoleGame();
	}


}
