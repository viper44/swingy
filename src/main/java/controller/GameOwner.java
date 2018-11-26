package controller;

import view.ViewFunc;
import view.gui.ViewGui;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Hero;
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameOwner {
    Hero hero;
    ViewFunc ViewFunc;
    static GameOwner gameOwner;

    private GameOwner(){}

    public void regHero(Hero hero){
        this.hero = hero;
    }
    public void setViewGui(ViewFunc ViewFunc){
        this.ViewFunc = ViewFunc;
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
}
