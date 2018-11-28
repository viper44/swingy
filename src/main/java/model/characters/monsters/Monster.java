package model.characters.monsters;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import model.characters.Characters;

import java.util.logging.Level;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Monster  extends Characters{

    String name;

    public Monster(int hp, int damage, int defense, String name){
        super(hp, damage, defense);
        this.name = name;
    }
    public String toString(){
        return "Monster name :"  + this.getName() + "\n"+
        "Monster stats:\n" + "Damage: " + this.getDamage() + "\n"+
        "Defense: " + this.getDefense() + "\n" +
        "Health Point: " + this.getHpMax();
    }

}
