package model.characters.hero;

import model.equipment.Equipment;

import java.util.ArrayList;

/**
 * Created by msemenov on 11/22/18.
 */
public interface GetLoot {
   void getLoot(ArrayList<? extends Equipment> e);
}
