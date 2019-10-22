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
	SimpleView menuView;
	Map<String, Runnable> commandMap = new HashMap<>();

	public MenuController(SimpleView menuView) {
		this.menuView = menuView;
		commandMap.put("save", this::saveGame);
		commandMap.put("resume", this::resume);
		commandMap.put("exit", this::exit);
	}


	@Override
	public void process() {
		menuView.render();
		String command = menuView.readUserInput();
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
}
