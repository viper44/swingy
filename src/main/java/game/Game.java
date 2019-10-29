package game;

import controller.*;
import lombok.Data;
import lombok.experimental.Accessors;
import model.GameContext;
import storage.HeroDbManager;
import view.MainMenuView;

@Data
@Accessors(chain = true)
abstract public class Game {
	GameContext context = new GameContext();
	HeroDbManager dbManager;
	MainMenuController mainMenuController;
	MoveController moveController;
	MeetMonsterController meetMonsterController;
	FightController fightController;
	GetLootController getLootController;
	MenuController menuController;

	abstract protected MainMenuView mainMenuView();

	abstract public Game init(HeroDbManager dbManager);


	public void start() {
		context.setGame(this);
		int ret = 0;
		while (ret == 0){
			ret = mainMenuController.supProcess();
		}
		while (true) {
			moveController.process();
			meetMonsterController.process();
		}
	}

	public <C extends AbstractController> C initController(C controller) {
		controller.setContext(context);
		controller.setDbManager(dbManager);
		controller.setMenuController(menuController);
		return controller;
	}
}
