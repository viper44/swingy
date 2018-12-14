package game;

import view.MainMenuView;
import view.gui.MainMenuGuiView;

public class GuiGame  extends Game{
    @Override
    protected MainMenuView mainMenuView() {

        return new MainMenuGuiView();
    }
}
