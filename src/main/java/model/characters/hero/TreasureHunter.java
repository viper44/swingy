package model.characters.hero;

import model.equipment.Armor.Armor;
import model.equipment.Armor.ArmorFactory;
import model.equipment.Armor.LightArmor;
import model.equipment.Armor.Tunic;
import model.equipment.Equipment;
import model.equipment.helmet.Chub;
import model.equipment.helmet.Crown;
import model.equipment.helmet.Helmet;
import model.equipment.helmet.HelmetFactory;
import model.equipment.weapon.Dagger;
import model.equipment.weapon.Staff;
import model.equipment.weapon.Weapon;
import model.equipment.weapon.WeaponFactory;

import java.util.ArrayList;

/**
 * Created by msemenov on 11/14/18.
 */
public class TreasureHunter extends Hero{
    TreasureHunter(){
        super(WeaponFactory.newWeapon(Dagger.class),
                ArmorFactory.newArmor(LightArmor.class),
                HelmetFactory.newHelmet(Chub.class));
    }

    public void getLoot(ArrayList<? extends Equipment> e){

    }
}
