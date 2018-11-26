package model.characters.hero;

import model.equipment.Armor.Armor;
import model.equipment.Equipment;
import model.equipment.weapon.Weapon;

import java.util.ArrayList;

/**
 * Created by msemenov on 11/22/18.
 */
public interface GetLoot {
   void getLoot(ArrayList<? extends Equipment> e);
}
