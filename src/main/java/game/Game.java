package game;

import controller.*;
import lombok.Data;
import lombok.experimental.Accessors;
import model.GameContext;
import storage.HeroDbManager;
import view.MainMenuView;
import view.cons.*;
import view.cons.newgame.NewHeroNameView;
import view.cons.newgame.NewHeroTypeView;
import view.gui.MainMenuGuiView;
import view.gui.MenuViewGui;
import view.gui.endgame.WinGameGuiView;
import view.gui.move.MoveGuiView;
import view.gui.newgame.NewHeroClassGui;
import view.gui.newgame.NewHeroNameGui;

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
	abstract protected Game initGame();

	abstract void setSequence();
	public Game init(HeroDbManager heroDbManager) {
		this.dbManager = heroDbManager;
		this.menuController = initController(new MenuController(new MenuViewGui(), new MenuViewConsole()));
		NewGameController newGameController = initController(new NewGameController(new NewHeroNameGui(), new NewHeroClassGui(), new NewHeroNameView(), new NewHeroTypeView()));
		LoadGameController loadGameController = initController(new LoadGameController(new LoadGameView()));
		ExitController exitController = initController(new ExitController());
		GetLootController getLootController = initController(new GetLootController(new GetLootConsoleView()));
		FightController fightController = initController(new FightController(getLootController, new FightConsoleView(), new DiedConsoleView()));
		MainMenuController mainMenuController = initController(new MainMenuController(mainMenuView(), newGameController, loadGameController, exitController, new MainMenuGuiView()));

		MoveController moveController = initController(new MoveController(new MoveGuiView(), new WinGameGuiView(),new MoveConsoleView(), new WinConsoleView()));
		MeetMonsterController meetMonsterController = initController(new MeetMonsterController(fightController, new MeetMonsterConsoleView(), new EscapeFailConsole(), new EscapeSuccessConsole()));
		setSequence();
		return initGame()
				.setDbManager(dbManager)
				.setMainMenuController(mainMenuController)
				.setMoveController(moveController)
				.setMeetMonsterController(meetMonsterController)
				.setGetLootController(getLootController)
				.setMenuController(menuController);
	}


	public void start() {
		int ret = 0;
		while (ret == 0) {
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
