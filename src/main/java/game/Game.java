package game;

import controller.*;
import lombok.Data;
import lombok.experimental.Accessors;
import model.GameContext;
import storage.HeroDbManager;
import view.MainMenuView;
import view.SimpleView;
import view.cons.*;
import view.cons.newgame.NewHeroNameView;
import view.cons.newgame.NewHeroTypeView;


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
        this.menuController = initController(new MenuController(new MenuViewConsole()));
        NewGameController newGameController = initController(new NewGameController(new NewHeroNameView(), new NewHeroTypeView()));
        LoadGameController loadGameController = initController(new LoadGameController(new LoadGameView()));
        ExitController exitController = initController(new ExitController());
        GetLootController getLootController = initController(new GetLootController(new GetLootConsoleView()));
        FightController fightController =  initController(new FightController(getLootController));
        MainMenuController mainMenuController = initController(new MainMenuController(mainMenuView(), newGameController, loadGameController, exitController));

        MoveController moveController = initController(new MoveController());
        MeetMonsterController meetMonsterController = initController(new MeetMonsterController(fightController, new MeetMonsterConsoleView(), new EscapeFail(), new EscapeSuccess()));


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
        while (true){
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
