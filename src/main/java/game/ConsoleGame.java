package game;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import view.MainMenuView;
import view.cons.MainMenuConsoleView;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsoleGame extends Game {

    @Override
    protected MainMenuView mainMenuView() {
        return new MainMenuConsoleView();
    }
}
