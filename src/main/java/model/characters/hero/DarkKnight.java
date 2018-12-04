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
        for (Equipment eq: e) {
            if (eq instanceof Weapon){
                if (eq.getClass().equals(Sword.class) && ((Weapon) eq).getDmg() > this.weapon.getDmg()){
                    System.out.println("You found new Sword!\nThis sword damage "
                            + ((Weapon) eq).getDmg() +" and my sword damage " + this.weapon.getDmg() +
                            ". I'll take new one!");
                    this.setWeapon((Weapon)eq);
                }
            }
            if (eq instanceof Armor){
                if (eq.getClass().equals(HeavyArmor.class) && ((Armor) eq).getDefense() > this.armor.getDefense()){
                    System.out.println("You found new HeavyArmor!\nThis HeavyArmor defense "
                            + ((Armor) eq).getDefense() +" and my HeavyArmor defense " + this.armor.getDefense() +
                            ". I'll take new one!");
                    this.setArmor((Armor)eq);
                }
            }
            if (eq instanceof Helmet){
                if (eq.getClass().equals(Bucket.class) && ((Helmet) eq).getHp() > this.helmet.getHp()){
                    System.out.println("You found new Bucket!\nThis Bucket hp "
                            + ((Helmet) eq).getHp() +" and my Bucket hp " + this.helmet.getHp() +
                            ". I'll take new one!");
                    this.setHelmet((Helmet)eq);
                }
            }
            System.out.println(eq);
        }
    }
}
