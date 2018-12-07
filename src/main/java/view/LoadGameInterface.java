package view;

import model.characters.hero.Hero;
import storage.HeroDbManager;


public interface LoadGameInterface {

    Hero loadGame(HeroDbManager dbManager);
}
