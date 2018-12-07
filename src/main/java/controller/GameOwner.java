package controller;

import controller.fight.Fight;
import lombok.Data;
import model.Loot;
import model.characters.hero.Coordinates;
import model.characters.monsters.Monster;
import model.characters.monsters.MonsterFactory;
import storage.HeroDbManager;
import view.View;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Hero;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameOwner {
    Hero hero;
    Monster monster;
    public View view;
    static GameOwner gameOwner;
    HeroDbManager dbManager;
    Map<String, Runnable> actionList = new HashMap<>();
    Map<String, Runnable> menuList = new HashMap<>();
    Map<String, Runnable> showMenuList = new HashMap<>();


    private GameOwner(){}

    public void regHero(){
        this.hero = initHero();
        this.hero.getEquip();
    }
    private void regHero(Hero hero){
        this.hero = hero;
    }
    public void setViewGui(View view){
        this.view = view;
    }
    public void setDbMangaer(HeroDbManager dbMangaer){ this.dbManager = dbMangaer;}

    private void changeCondition(){
        this.hero.updateConditions();
    }
    public static GameOwner getOwner(){
        if(gameOwner == null){
            gameOwner = new GameOwner();
        }
        return gameOwner;
    }

    private Hero initHero(){
        return new Hero.HeroBuilder()
                .heroName(view.newGame.NameScanner())
                .exp(0)
                .coordinates(new Coordinates(5, 5))
                .level(1)
                .build(view.newGame.HeroTypeScanner());
    }
    public void startGame(){
        menuList.get(view.menu.getCommand().toLowerCase()).run();
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
        if(new Random().nextBoolean()){
            this.monster = MonsterFactory.newMonster(hero.getLevel());
            if (view.monsterView.runOrFight(monster).equals("Fight")){
                fight();
            } else {
                if(new Random().nextBoolean()){
                    System.out.println("You successfully escape");
                } else {
                    System.out.println("You failed to run. Prepare for battle");
                    fight();
                }
            }
        }
    }

    private void checkCondition(){
        if (hero.getHpCur() <= 0 ){
            System.out.println("You die!\n\n GAME OVER!");
            System.exit(1);
        }

    }
    private void fight(){
        System.out.println(hero);
        Fight.figthMode(hero, monster);
        if (hero.getHpCur() > 0){
            hero.getLoot(Loot.getLoot());
            hero.updateConditions();
        }
    }

    private void makeMove() {
        actionList.get(view.moveView.drawMap(hero.getLevel()).toLowerCase()).run();
    }


    private void goSouth(){
        hero.getCoordinates().setY(hero.getCoordinates().getY() + 1);
    }
    private void goNorth(){
        hero.getCoordinates().setY(hero.getCoordinates().getY() - 1);
    }
    private void goWest(){
        hero.getCoordinates().setX(hero.getCoordinates().getX() - 1);
    }
    private void goEast(){
        hero.getCoordinates().setX(hero.getCoordinates().getX() + 1);
    }
    private void showMenu(){
        showMenuList.get(view.showMenuInterface.showMenu().toLowerCase()).run();
    }

    private void loadGame(){
       hero = view.loadGameInterface.loadGame(dbManager);
    }

    private void exitGame(){
        System.out.println("You exit the Game");
        System.exit(1);
    }

    private void resume(){
        makeMove();
    }


    private void save(){
        dbManager.heroAdd(hero);
    }

    {
        menuList.put("newgame", this::regHero);
        menuList.put("load", this::loadGame);
        menuList.put("exit", this::exitGame);

        actionList.put("south", this::goSouth);
        actionList.put("north", this::goNorth);
        actionList.put("west", this::goWest);
        actionList.put("east", this::goEast);
        actionList.put("menu", this::showMenu);

        showMenuList.put("resume", this::resume);
        showMenuList.put("exit", this::exitGame);
        showMenuList.put("save", this::save);

    }

}
