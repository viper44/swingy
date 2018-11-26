package model.equipment.Armor;

import lombok.Getter;
import model.equipment.Equipment;

/**
 * Created by msemenov on 11/14/18.
 */
@Getter
public class Armor extends Equipment {
    private int defense;

    Armor(final int defense){
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "defense=" + defense +
                ", type='" + this.getClass().getSimpleName() + '\'' +
                '}';
    }
}
