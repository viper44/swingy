package model.characters.hero;

import model.equipment.armor.Armor;
import model.equipment.armor.ArmorFactory;
import model.equipment.armor.HeavyArmor;
import model.equipment.Equipment;
import model.equipment.helmet.Bucket;
import model.equipment.helmet.Helmet;
import model.equipment.helmet.HelmetFactory;
import model.equipment.weapon.Sword;
import model.equipment.weapon.Weapon;
import model.equipment.weapon.WeaponFactory;

import javax.persistence.Entity;
import java.util.ArrayList;

/**
 * Created by msemenov on 11/14/18.
 */
@Entity
public class DarkKnight extends Hero {
    DarkKnight(){
        super();
    }

    public Weapon getMyWeapon(){
        return WeaponFactory.newWeapon(Sword.class);
    }
    public Armor getMyArmor(){
        return ArmorFactory.newArmor(HeavyArmor.class);
    }
    public Helmet getMyHelmet(){
        return HelmetFactory.newHelmet(Bucket.class);
    }
    public String initPic(){return "knight.jpg";}

}
