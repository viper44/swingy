package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import controller.fight.Fight;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import model.GameContext;
import model.Loot;
import model.characters.hero.Hero;
import model.characters.monsters.Monster;
import model.characters.monsters.MonsterFactory;
import storage.HeroDbManager;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameOwner {
    HeroDbManager dbManager;
    GameContext context = new GameContext();



    MainMenuController mainMenu;


    Hero hero;
    Monster monster;
    static GameOwner gameOwner;
    Map<String, Runnable> actionList = new HashMap<>();
    Map<String, Runnable> showMenuList = new HashMap<>();

    private GameOwner(HeroDbManager dbManager) {
//        this.mainMenu = new MainMenuController(dbManager);
    }

    public void setDbMangaer(HeroDbManager dbMangaer) { this.dbManager = dbMangaer;}

    private void changeCondition() {
        this.hero.updateConditions();
    }

    public static GameOwner getOwner(HeroDbManager dbManager) {
        if (gameOwner == null) {
            gameOwner = new GameOwner(dbManager);
        }
        return gameOwner;
    }


    public void startGame() {
        mainMenu.process();
        move();
    }

    private void move() {

        while (true) {
            makeMove();
            isHereMonster();
            checkCondition();
        }
    }

    private void isHereMonster() {
        if (new Random().nextBoolean()) {
            this.monster = MonsterFactory.newMonster(hero.getLevel());
//            if (view.monsterView.runOrFight(monster).equals("Fight")) {
//                fight();
//            } else {
//                if (new Random().nextBoolean()) {
//                    System.out.println("You successfully escape");
//                } else {
//                    System.out.println("You failed to run. Prepare for battle");
//                    fight();
//                }
//            }
        }
    }

    private void checkCondition() {
        if (hero.getHpCur() <= 0) {
            System.out.println("You die!\n\n GAME OVER!");
            System.exit(1);
        }

    }

    private void fight() {
        System.out.println(hero);
   //     Fight.figthMode(hero, monster);
//        if (hero.getHpCur() > 0) {
//            hero.getLoot(Loot.getLoot());
//            hero.updateConditions();
//        }
    }

    private void makeMove() {
//        actionList.get(view.moveView.drawMap(hero.getLevel()).toLowerCase()).run();
    }


    private void goSouth() {
        hero.getCoordinates().setY(hero.getCoordinates().getY() + 1);
    }

    private void goNorth() {
        hero.getCoordinates().setY(hero.getCoordinates().getY() - 1);
    }

    private void goWest() {
        hero.getCoordinates().setX(hero.getCoordinates().getX() - 1);
    }

    private void goEast() {
        hero.getCoordinates().setX(hero.getCoordinates().getX() + 1);
    }

    private void exitGame() {
        System.out.println("You exit the Game");
        System.exit(1);
    }

    private void resume() {
        makeMove();
    }


    private void save() {
        dbManager.heroAdd(hero);
    }

    {
        actionList.put("south", this::goSouth);
        actionList.put("north", this::goNorth);
        actionList.put("west", this::goWest);
        actionList.put("east", this::goEast);
//        actionList.put("menu", this::showMenu);

        showMenuList.put("resume", this::resume);
        showMenuList.put("exit", this::exitGame);
        showMenuList.put("save", this::save);

    }

}
