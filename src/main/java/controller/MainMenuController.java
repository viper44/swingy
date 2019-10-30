package controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import view.MainMenuView;

import java.util.HashMap;
import java.util.Map;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainMenuController extends AbstractController implements Supporter {
	Map<String, Supporter> menuList = new HashMap<>();
	MainMenuView mainMenuView;
	//Map<Integer, MainMenuView> viewManager = new HashMap<>();

	public MainMenuController(MainMenuView menuView, NewGameController newGameController,
	                          LoadGameController loadGameController, ExitController controller) {
	//	viewManager.put(1, menuView);
	//	viewManager.put(2, menuConsView);
		mainMenuView = menuView;
		menuList.put("newgame", newGameController);
		menuList.put("load", loadGameController);
		menuList.put("exit", controller);
	}

	@Override
	public void process() {

	}

	@Override
	public int supProcess() {
		mainMenuView.render();

		String userInput = mainMenuView.readUserInput();

		return menuList.get(userInput).supProcess();
	}
}
