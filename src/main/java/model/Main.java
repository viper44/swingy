package model;

import com.mysql.cj.protocol.Resultset;
import controller.GameOwner;
import model.characters.hero.*;
import model.equipment.weapon.Staff;
import model.equipment.weapon.WeaponFactory;
import storage.HeroDbManager;
import view.View;
import view.console.ViewConsole;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;

/**
 * Created by msemenov on 11/14/18.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {


        HeroDbManager heroDbManager = new HeroDbManager();

        Hero albert = new Hero.HeroBuilder()
                .heroName("pavlo")
                .exp(0)
                .level(1)
                .coordinates(new Coordinates(5, 5))
                .build(example.get("SpellHowler"));
        heroDbManager.heroAdd(albert);
        //
//  System.out.println(albert);
//        albert.setWeapon(WeaponFactory.newWeapon(Staff.class));
//        System.out.println(albert);
//        albert.setWeapon(WeaponFactory.newWeapon(Staff.class));
//        System.out.println(albert);
//
//        View view = new ViewConsole();
//        GameOwner game = GameOwner.getOwner();
//        game.setViewGui(view);
//        game.startGame();

//        System.out.println(game.getHero());
      //  game.initMenu();
        //.game.regHero();

       // view.menu.MenuDraw();
//        System.out.println(view.menu.command);
//        Hero first = new Hero.HeroBuilder()
//                .heroName("pavlo")
//                .exp(0)
//                .level(1)
//                .coordinates(new Coordinates(5, 5))
//                .build(example.get("SpellHowler"));
//        Hero dk = new Hero.HeroBuilder()
//                .heroName("petya")
//                .exp(0)
//                .level(1)
//                .coordinates(new Coordinates(5, 5))
//                .build(example.get("DarkKnight"));
//
//        ArrayList<Equipment> test = new ArrayList<>();
//        test.add(WeaponFactory.newWeapon(Dagger.class));
//        first.getLoot(test);
//        Monster mob = MonsterFactory.newMonster(first.getLevel());
//        System.out.println("First damage = " + first.getDamage());
//        System.out.println(mob.getName() +  "  dmg =  " + mob.getDamage() + " hp " + mob.getHpCur() + " def = " + mob.getDefense());
//       // System.out.println(mob.toString());
//        System.out.println(first.toString());
//        System.out.println(dk.toString());
//       // System.out.println(min.toString());
//       // System.out.println(knight.toString());
//        //SimpleGUI app = new SimpleGUI();
//      //  app.setVisible(true);
//        //System.out.println(max.getDamage());
//        GameOwner game = GameOwner.getOwner();
//        game.regHero(first);
//        Fight.figthMode(first, mob);
//        first.updateConditions();
//
//        first.getLoot(Loot.getLoot());
        //((SpellHowler)first).getLoot();
    }
    public static final HashMap<String, Class <? extends Hero>> example = new HashMap<>();
    static {
        example.put("SpellHowler", SpellHowler.class);
        example.put("TreasureHunter", TreasureHunter.class);
        example.put("DarkKnight", DarkKnight.class);
    }
}
