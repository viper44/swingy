package model;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import controller.ExitController;
import controller.LoadGameController;
import controller.MainMenuController;
import controller.NewGameController;
import game.ConsoleGame;
import game.Game;
import game.GuiGame;
import model.characters.hero.DarkKnight;
import model.characters.hero.Hero;
import model.characters.hero.SpellHowler;
import model.characters.hero.TreasureHunter;
import storage.HeroDbManager;
import view.cons.LoadGameView;
import view.cons.newgame.NewHeroNameView;
import view.cons.newgame.NewHeroTypeView;
import view.gui.MainMenuGuiView;

import javax.swing.*;


/**
 * Created by msemenov on 11/14/18.
 */
public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {

        Game game = new GuiGame().init(new HeroDbManager());
        game.start();





    }
    public static final HashMap<String, Class <? extends Hero>> example = new HashMap<>();
    static {
        example.put("SpellHowler", SpellHowler.class);
        example.put("TreasureHunter", TreasureHunter.class);
        example.put("DarkKnight", DarkKnight.class);
    }
}
