package model;

import com.mysql.cj.protocol.Resultset;
import controller.GameOwner;
import model.characters.hero.*;
import model.equipment.weapon.Staff;
import model.equipment.weapon.WeaponFactory;
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

        String userName = "root";
        String password = "root";
        String connectionUrl = "jdbc:mysql://localhost:3306/test";

        try(Connection connection = DriverManager.getConnection(connectionUrl, userName, password);){

            Statement stat = connection.createStatement();
            stat.execute("drop table if exists Books");
            stat.executeUpdate("CREATE table if not exists Books (id MEDIUMINT not null AUTO_INCREMENT," +
                    "name CHAR(30) not null, img BLOB, PRIMARY KEY (id));");
            BufferedImage image = ImageIO.read(new URL("http://memesmix.net/media/created/gk6kuu.jpg"));
            Blob blob = connection.createBlob();
            try(OutputStream outputStream = blob.setBinaryStream(1)) {
                ImageIO.write(image, "jpg", outputStream);
            }
                PreparedStatement stat2 = connection.prepareStatement("insert into Books (name, img) values (?, ?)");

                stat2.setString(1, "bake");
                stat2.setBlob(2, blob);
                stat2.execute();


            ResultSet resultSet = stat2.executeQuery("select * from Books");
            while (resultSet.next()){
                Blob blob2 = resultSet.getBlob("img");
                BufferedImage image2 = ImageIO.read(blob2.getBinaryStream());
                File outputFile = new File(("saved.jpeg"));
                ImageIO.write(image2, "jpeg", outputFile);
            }
            //while ()


//            Statement statement = connection.createStatement();
//            statement.executeUpdate("drop table Books");
//            statement.executeUpdate("CREATE table if not exists Books (id MEDIUMINT not null AUTO_INCREMENT, name CHAR(30) not null , PRIMARY KEY (id));");
//            statement.executeUpdate("insert  into Books(name) values ('kokoko')");
//            statement.executeUpdate("insert into Books set name = 'zalupa'");
//            ResultSet resultset = statement.executeQuery("select * from Books");
//            while (resultset.next()){
//                System.out.println(resultset.getInt("id"));
//                System.out.println(resultset.getString("name"));
//                System.out.println("---------");
//            }
//            ResultSet res2 = statement.executeQuery("select name from Books where id = 1");
//            res2.next();
//            System.out.println(res2.getString(1));
////
//            Statement statement = connection.createStatement();
//
//            statement.execute("drop table Users");
//            statement.executeUpdate("create table if not exists  Users (id MEDIUMINT not null AUTO_Increment," +
//                    "name varchar(30) not null , password varchar (30) not null, PRIMARY KEY (id))");
//            statement.executeUpdate("insert into Users (name, password) values ('yvasya', '123')");
//            statement.executeUpdate("insert into Users (name, password) values ('griffin', 'lol')");
//            String userId = "1' or 1 = '1'";
////
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from Users where id = ? and name = ?");
//            preparedStatement.setString(1, userId);
//            preparedStatement.setString(2, "yvasya");
////            preparedStatement.setString(2, "userName");
//            ResultSet resultSet= preparedStatement.executeQuery();
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("name"));
//                System.out.println(resultSet.getString("password"));
//            }
        } catch (SQLException | IOException e){

        }

//        Hero albert = new Hero.HeroBuilder()
//                .heroName("pavlo")
//                .exp(0)
//                .level(1)
//                .coordinates(new Coordinates(5, 5))
//                .build(example.get("SpellHowler"));
//        System.out.println(albert);
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
