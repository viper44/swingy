package model;

import model.equipment.Armor.ArmorFactory;
import model.equipment.Equipment;
import model.equipment.helmet.HelmetFactory;
import model.equipment.weapon.WeaponFactory;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Created by msemenov on 11/22/18.
 */
public class Loot {

   private static final ArrayList<Supplier<Equipment>> LOOT_CREATOR = new ArrayList<>();

    static {
        LOOT_CREATOR.add(WeaponFactory::newWeapon);
        LOOT_CREATOR.add(HelmetFactory::newHelmet);
        LOOT_CREATOR.add(ArmorFactory::newArmor);
    }
    private static  <W extends Equipment> W lootGen(){
        int index = new Random().nextInt(LOOT_CREATOR.size());
            return (W)LOOT_CREATOR.get(index).get();
    }

    public static ArrayList<Equipment> getLoot(){
        ArrayList<Equipment> ret = new ArrayList<>();
        ret.add(lootGen());
        ret.add(lootGen());
        return ret;
    }

}

