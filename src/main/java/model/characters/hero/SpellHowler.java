package model.characters.hero;

import model.equipment.armor.Armor;
import model.equipment.armor.ArmorFactory;
import model.equipment.armor.Tunic;
import model.equipment.Equipment;
import model.equipment.helmet.Crown;
import model.equipment.helmet.Helmet;
import model.equipment.helmet.HelmetFactory;
import model.equipment.weapon.Staff;
import model.equipment.weapon.Weapon;
import model.equipment.weapon.WeaponFactory;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by msemenov on 11/14/18.
 */

@Entity
public class SpellHowler extends Hero {
    public SpellHowler() {

    }

    public Weapon getMyWeapon() {
        return WeaponFactory.newWeapon(Staff.class);
    }

    public Armor getMyArmor() {
        return ArmorFactory.newArmor(Tunic.class);
    }

    public Helmet getMyHelmet() {
        return HelmetFactory.newHelmet(Crown.class);
    }

    public String initPic(){ return "mage.jpg";}

}
