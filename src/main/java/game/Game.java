package game;

import controller.*;
import lombok.Data;
import lombok.experimental.Accessors;
import model.GameContext;
import storage.HeroDbManager;
import view.MainMenuView;
import view.cons.*;
import view.gui.MenuViewGui;
import view.gui.move.EscapeFailGui;
import view.gui.move.EscapeSuccessGui;
import view.gui.move.MeetMonsterGuiView;
import view.gui.move.MoveGuiView;
import view.gui.newgame.NewHeroClassGui;
import view.gui.newgame.NewHeroNameGui;

import java.util.concurrent.CountDownLatch;


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

	public Game init(HeroDbManager dbManager) {
		this.dbManager = dbManager;
		this.menuController = initController(new MenuController(new MenuViewGui()));
		NewGameController newGameController = initController(new NewGameController(new NewHeroNameGui(), new NewHeroClassGui()));
		LoadGameController loadGameController = initController(new LoadGameController(new LoadGameView()));
		ExitController exitController = initController(new ExitController());
		GetLootController getLootController = initController(new GetLootController(new GetLootConsoleView()));
		FightController fightController = initController(new FightController(getLootController));
		MainMenuController mainMenuController = initController(new MainMenuController(mainMenuView(), newGameController, loadGameController, exitController));

		MoveController moveController = initController(new MoveController(new MoveGuiView()));
		MeetMonsterController meetMonsterController = initController(new MeetMonsterController(fightController, new MeetMonsterGuiView(), new EscapeFailGui(), new EscapeSuccessGui()));

		return new ConsoleGame()
				.setDbManager(dbManager)
				.setMainMenuController(mainMenuController)
				.setMoveController(moveController)
				.setMeetMonsterController(meetMonsterController)
				.setGetLootController(getLootController)
				.setMenuController(menuController);
	}

	public void start() {
		mainMenuController.process();
		while (true) {
			moveController.process();
			meetMonsterController.process();
		}
	}

	private <C extends AbstractController> C initController(C controller) {
		controller.setContext(context);
		controller.setDbManager(dbManager);
		controller.setMenuController(menuController);
		return controller;
	}
}
