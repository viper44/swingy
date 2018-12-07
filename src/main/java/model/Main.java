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


        View view = new ViewConsole();
        GameOwner game = GameOwner.getOwner();
        game.setViewGui(view);
        game.setDbManager(new HeroDbManager());
        game.startGame();

    }
    public static final HashMap<String, Class <? extends Hero>> example = new HashMap<>();
    static {
        example.put("SpellHowler", SpellHowler.class);
        example.put("TreasureHunter", TreasureHunter.class);
        example.put("DarkKnight", DarkKnight.class);
    }
}
