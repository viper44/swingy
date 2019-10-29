package model.characters.hero;

import model.equipment.armor.Armor;
import model.equipment.armor.ArmorFactory;
import model.equipment.armor.LightArmor;
import model.equipment.Equipment;
import model.equipment.helmet.Chub;
import model.equipment.helmet.Helmet;
import model.equipment.helmet.HelmetFactory;
import model.equipment.weapon.Dagger;
import model.equipment.weapon.Weapon;
import model.equipment.weapon.WeaponFactory;

import javax.persistence.Entity;
import java.util.ArrayList;

/**
 * Created by msemenov on 11/14/18.
 */
@Entity
public class TreasureHunter extends Hero{
    TreasureHunter(){
        super();
    }

    public Weapon getMyWeapon(){
        return WeaponFactory.newWeapon(Dagger.class);
    }
    public Armor getMyArmor(){
        return ArmorFactory.newArmor(LightArmor.class);
    }
    public Helmet getMyHelmet(){
        return HelmetFactory.newHelmet(Chub.class);
    }
    public String initPic() {return "treasure.jpg";}
}
