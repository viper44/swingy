package game;

import controller.AbstractController;
import controller.ExitController;
import controller.LoadGameController;
import controller.MainMenuController;
import controller.MoveController;
import controller.NewGameController;
import lombok.Data;
import lombok.experimental.Accessors;
import model.GameContext;
import storage.HeroDbManager;
import view.MainMenuView;
import view.cons.LoadGameView;
import view.cons.newgame.NewHeroNameView;
import view.cons.newgame.NewHeroTypeView;


@Data
@Accessors(chain = true)
abstract public class Game {
    GameContext context = new GameContext();
    HeroDbManager dbManager;
    MainMenuController mainMenuController;
    MoveController moveController;

    abstract protected MainMenuView mainMenuView();

    public Game init(HeroDbManager dbManager) {
        NewGameController newGameController = initController(new NewGameController(new NewHeroNameView(), new NewHeroTypeView()));
        LoadGameController loadGameController = initController(new LoadGameController(new LoadGameView()));
        ExitController exitController = initController(new ExitController());

        MainMenuController mainMenuController = initController(new MainMenuController(mainMenuView(), newGameController, loadGameController, exitController));

        MoveController moveController = new MoveController();

        return new ConsoleGame()
                .setDbManager(dbManager)
                .setMainMenuController(mainMenuController)
                .setMoveController(moveController);
    }

    public void start() {
        mainMenuController.process();
        moveController.process();

    }

    private <C extends AbstractController> C initController(C controller) {
        controller.setContext(context);
        controller.setDbManager(dbManager);

        return controller;
    }
}
