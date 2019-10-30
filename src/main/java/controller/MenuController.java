package controller;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import view.SimpleView;

import java.util.HashMap;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuController extends AbstractController {
	Map<Integer, SimpleView> menuViewManager = new HashMap<>();
	Map<String, Runnable> commandMap = new HashMap<>();

	public MenuController(SimpleView menuGuiView, SimpleView menuConsoleView) {
		menuViewManager.put(1, menuGuiView);
		menuViewManager.put(2, menuConsoleView);
		commandMap.put("save", this::saveGame);
		commandMap.put("resume", this::resume);
		commandMap.put("exit", this::exit);
		commandMap.put("change", this::changeView);
	}

	@Override
	public void process() {
		menuViewManager.get(context.getSequence()).render();
		String command = menuViewManager.get(context.getSequence()).readUserInput().toLowerCase();
		commandMap.get(command).run();
	}

	private void saveGame() {
		dbManager.heroAdd(context.getHero());
	}

	private void resume() {
		return;
	}

	private void exit() {
		System.exit(1);
	}

	private void changeView() {
		if (context.getSequence() == 1) {
			context.setSequence(2);
		} else if (context.getSequence() == 2) {
			context.setSequence(1);
		}
	}
}
