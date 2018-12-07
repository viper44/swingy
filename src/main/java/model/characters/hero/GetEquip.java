package model.characters.hero;

import model.equipment.armor.Armor;
import model.equipment.armor.ArmorFactory;
import model.equipment.armor.HeavyArmor;
import model.equipment.helmet.Bucket;
import model.equipment.helmet.Helmet;
import model.equipment.helmet.HelmetFactory;
import model.equipment.weapon.Sword;
import model.equipment.weapon.Weapon;
import model.equipment.weapon.WeaponFactory;

public interface GetEquip {
    public Weapon getMyWeapon();
    public Armor getMyArmor();
    public Helmet getMyHelmet();
}
