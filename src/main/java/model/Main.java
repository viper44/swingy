package model;

import java.util.HashMap;
import game.ConsoleGame;
import game.Game;
import model.characters.hero.DarkKnight;
import model.characters.hero.Hero;
import model.characters.hero.SpellHowler;
import model.characters.hero.TreasureHunter;
import storage.HeroDbManager;


/**
 * Created by msemenov on 11/14/18.
 */
public class Main {

    public static void main(String[] args) {
        Game game = new ConsoleGame().init(new HeroDbManager());
        game.start();

    }
    public static final HashMap<String, Class <? extends Hero>> example = new HashMap<>();
    static {
        example.put("SpellHowler", SpellHowler.class);
        example.put("TreasureHunter", TreasureHunter.class);
        example.put("DarkKnight", DarkKnight.class);
    }
}
