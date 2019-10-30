package game;

import controller.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import storage.HeroDbManager;
import view.MainMenuView;
import view.cons.*;
import view.cons.newgame.NewHeroNameView;
import view.cons.newgame.NewHeroTypeView;
import view.gui.MainMenuGuiView;
import view.gui.MenuViewGui;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsoleGame extends Game {

	@Override
	protected MainMenuView mainMenuView() {
		return new MainMenuConsoleView();
	}

	@Override
	void setSequence() {
		context.setSequence(2);
	}

	@Override
	protected Game initGame() {
		return new ConsoleGame();
	}
	//	@Override
//	public Game init(HeroDbManager heroDbManager) {
//		this.dbManager = heroDbManager;
//		this.menuController = initController(new MenuController(new MenuViewGui(), new MenuViewConsole()));
//		NewGameController newGameController = initController(new NewGameController(new NewHeroNameView(), new NewHeroTypeView()));
//		LoadGameController loadGameController = initController(new LoadGameController(new LoadGameView()));
//		ExitController exitController = initController(new ExitController());
//		GetLootController getLootController = initController(new GetLootController(new GetLootConsoleView()));
//		FightController fightController = initController(new FightController(getLootController, new FightConsoleView(), new DiedConsoleView()));
//		MainMenuController mainMenuController = initController(new MainMenuController(mainMenuView(), newGameController, loadGameController, exitController, new MainMenuGuiView()));
//
//		MoveController moveController = initController(new MoveController(new MoveConsoleView(), new WinConsoleView()));
//		MeetMonsterController meetMonsterController = initController(new MeetMonsterController(fightController, new MeetMonsterConsoleView(), new EscapeFailConsole(), new EscapeSuccessConsole()));
//		context.setSequence(2);
//
//		return new ConsoleGame()
//				.setDbManager(dbManager)
//				.setMainMenuController(mainMenuController)
//				.setMoveController(moveController)
//				.setMeetMonsterController(meetMonsterController)
//				.setGetLootController(getLootController)
//				.setMenuController(menuController);
//	}

}
