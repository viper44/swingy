package controller;

import controller.fight.Fight;
import lombok.Data;
import model.Loot;
import model.characters.hero.Coordinates;
import model.characters.monsters.Monster;
import model.characters.monsters.MonsterFactory;
import view.View;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Hero;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameOwner {
    Hero hero;
    Monster monster;
    public View view;
    static GameOwner gameOwner;


    private GameOwner(){}

    public void regHero(){
        this.hero = initHero();
    }
    private void regHero(Hero hero){
        this.hero = hero;
    }
    public void setViewGui(View view){
        this.view = view;
    }

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
                .heroName(view.menu.newGame.getName())
                .exp(0)
                .coordinates(new Coordinates(5, 5))
                .level(1)
                .build(view.menu.newGame.getHeroType());
    }
    public void startGame(){
        view.menu.kek.test();
        move();
    }
    private void move(){
        while(true){
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
        Fight.figthMode(hero, monster);
        if (hero.getHpCur() > 0){
            hero.getLoot(Loot.getLoot());
            hero.updateConditions();
        }
    }

    private void makeMove() {
        switch (view.moveView.drawMap(hero.getLevel())){
            case "South" : {
                hero.getCoordinates().setY(hero.getCoordinates().getY() - 1);
                break;
            }
            case "North" : {
                hero.getCoordinates().setY(hero.getCoordinates().getY() + 1);
                break;
            }
            case "West" : {
                hero.getCoordinates().setX(hero.getCoordinates().getX() - 1);
                break;
            }
            case "East" : {
                hero.getCoordinates().setX(hero.getCoordinates().getX() + 1);
                break;
            }
        }
    }
}
