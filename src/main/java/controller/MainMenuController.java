package controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import view.MainMenuView;

import java.util.HashMap;
import java.util.Map;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainMenuController extends AbstractController implements Supporter {
	Map<String, Supporter> menuList = new HashMap<>();
	Map<Integer, MainMenuView> viewManager = new HashMap<>();

	public MainMenuController(MainMenuView menuView, NewGameController newGameController,
	                          LoadGameController loadGameController, ExitController controller, MainMenuView menuConsView) {
		viewManager.put(1, menuView);
		viewManager.put(2, menuConsView);
		menuList.put("newgame", newGameController);
		menuList.put("load", loadGameController);
		menuList.put("exit", controller);
	}

	@Override
	public void process() {

	}

	@Override
	public int supProcess() {
		viewManager.get(context.getSequence()).render();

		String userInput = viewManager.get(context.getSequence()).readUserInput();

		return menuList.get(userInput).supProcess();
	}
}
