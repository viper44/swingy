package game;

import controller.*;
import storage.HeroDbManager;
import view.MainMenuView;
import view.cons.MainMenuConsoleView;
import view.cons.MenuViewConsole;
import view.gui.LoadGuiView;
import view.gui.MainMenuGuiView;
import view.gui.MenuViewGui;
import view.gui.endgame.DiedGuiView;
import view.gui.endgame.WinGameGuiView;
import view.gui.move.*;
import view.gui.newgame.NewHeroClassGui;
import view.gui.newgame.NewHeroNameGui;

public class GuiGame extends Game {
	@Override
	protected MainMenuView mainMenuView() {
		return new MainMenuGuiView();
	}

	@Override
	protected Game initGame() {
		return new GuiGame();
	}

	@Override
	void setSequence() {
		context.setSequence(1);
	}
	//	@Override
//	public Game init(HeroDbManager heroDbManager) {
//		this.dbManager = heroDbManager;
//		this.menuController = initController(new MenuController(new MenuViewGui(), new MenuViewConsole()));
//		NewGameController newGameController = initController(new NewGameController(new NewHeroNameGui(), new NewHeroClassGui()));
//		LoadGameController loadGameController = initController(new LoadGameController(new LoadGuiView()));
//		ExitController exitController = initController(new ExitController());
//		GetLootController getLootController = initController(new GetLootController(new LootGuiView()));
//		FightController fightController = initController(new FightController(getLootController, new FightGuiView(), new DiedGuiView()));
//		MainMenuController mainMenuController = initController(new MainMenuController(mainMenuView(), newGameController, loadGameController, exitController, new MainMenuConsoleView()));
//
//		MoveController moveController = initController(new MoveController(new MoveGuiView(), new WinGameGuiView()));
//		MeetMonsterController meetMonsterController = initController(new MeetMonsterController(fightController, new MeetMonsterGuiView(), new EscapeFailGui(), new EscapeSuccessGui()));
//		context.setSequence(1);
//
//		return new GuiGame()
//				.setDbManager(dbManager)
//				.setMainMenuController(mainMenuController)
//				.setMoveController(moveController)
//				.setMeetMonsterController(meetMonsterController)
//				.setGetLootController(getLootController)
//				.setMenuController(menuController);
//	}
}
