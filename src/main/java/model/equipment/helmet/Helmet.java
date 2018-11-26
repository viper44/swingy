package model.equipment.helmet;

import model.equipment.Equipment;

/**
 * Created by msemenov on 11/14/18.
 */
public class Helmet extends Equipment {
    private int hp;

    Helmet(final int hp){
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return "Helmet{" +
                "hp=" + hp +
                ", type='" + this.getClass().getSimpleName() + '\'' +
                '}';
    }
}
