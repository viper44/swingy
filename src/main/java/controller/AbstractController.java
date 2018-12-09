package controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.GameContext;
import storage.HeroDbManager;


@FieldDefaults(level = AccessLevel.PROTECTED)
abstract public class AbstractController implements Controller {
    HeroDbManager dbManager;
    GameContext context;

    public void setDbManager(HeroDbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void setContext(GameContext context) {
        this.context = context;
    }
}
