package model.equipment.weapon;

import model.equipment.Equipment;

/**
 * Created by msemenov on 11/14/18.
 */
public class Weapon extends Equipment {
    private int dmg;

    Weapon(int dmg){
        this.dmg = dmg;
    }

    public int getDmg() {
        return dmg;
    }


    @Override
    public String toString() {
        return "Weapon{" +
                "dmg=" + dmg +
                ", type='" + this.getClass().getSimpleName() + '\'' +
                '}';
    }
}

