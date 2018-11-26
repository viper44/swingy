package model.characters.hero;

import model.equipment.Armor.Armor;
import model.equipment.Armor.ArmorFactory;
import model.equipment.Armor.HeavyArmor;
import model.equipment.Armor.Tunic;
import model.equipment.Equipment;
import model.equipment.helmet.Bucket;
import model.equipment.helmet.Crown;
import model.equipment.helmet.Helmet;
import model.equipment.helmet.HelmetFactory;
import model.equipment.weapon.Staff;
import model.equipment.weapon.Sword;
import model.equipment.weapon.Weapon;
import model.equipment.weapon.WeaponFactory;

import java.util.ArrayList;

/**
 * Created by msemenov on 11/14/18.
 */
public class DarkKnight extends Hero {
    DarkKnight(){
        super(WeaponFactory.newWeapon(Sword.class),
                ArmorFactory.newArmor(HeavyArmor.class),
                HelmetFactory.newHelmet(Bucket.class));
    }
    public void getLoot(ArrayList<? extends Equipment> e){

    }
}
