package model.characters.hero;

import model.equipment.Armor.Armor;
import model.equipment.Armor.ArmorFactory;
import model.equipment.Armor.Tunic;
import model.equipment.Equipment;
import model.equipment.helmet.Crown;
import model.equipment.helmet.Helmet;
import model.equipment.helmet.HelmetFactory;
import model.equipment.weapon.Staff;
import model.equipment.weapon.Weapon;
import model.equipment.weapon.WeaponFactory;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

/**
 * Created by msemenov on 11/14/18.
 */

public class SpellHowler extends Hero {


    SpellHowler(){
        super(WeaponFactory.newWeapon(Staff.class),
                ArmorFactory.newArmor(Tunic.class),
                HelmetFactory.newHelmet(Crown.class));
    }
    public void getLoot(ArrayList <? extends Equipment> e){

    }

}
