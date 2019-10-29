package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import view.MainMenuView;
import view.gui.MainMenuGuiView;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainMenuController extends AbstractController implements Supporter
{
    Map<String, Supporter> menuList = new HashMap<>();
    private MainMenuView menuView;

    public MainMenuController(MainMenuView menuView, NewGameController newGameController,
            LoadGameController loadGameController, ExitController controller) {
        this.menuView = menuView;

        menuList.put("newgame", newGameController);
        menuList.put("load", loadGameController);
        menuList.put("exit", controller);
    }

    @Override
    public void process() {

    }

    @Override
    public int supProcess() {
        menuView.render();

        String userInput = menuView.readUserInput();

       return menuList.get(userInput).supProcess();
    }
}
