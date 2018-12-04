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
        for (Equipment eq: e) {
            if (eq instanceof Weapon){
                if (eq.getClass().equals(Dagger.class) && ((Weapon) eq).getDmg() > this.weapon.getDmg()){
                    System.out.println("You found new Dagger!\nThis Dagger damage "
                            + ((Weapon) eq).getDmg() +" and my Dagger damage " + this.weapon.getDmg() +
                            ". I'll take new one!");
                    this.setWeapon((Weapon)eq);
                }
            }
            if (eq instanceof Armor){
                if (eq.getClass().equals(LightArmor.class) && ((Armor) eq).getDefense() > this.armor.getDefense()){
                    System.out.println("You found new LightArmor!\nThis LightArmor defense "
                            + ((Armor) eq).getDefense() +" and my LightArmor defense " + this.armor.getDefense() +
                            ". I'll take new one!");
                    this.setArmor((Armor)eq);
                }
            }
            if (eq instanceof Helmet){
                if (eq.getClass().equals(Chub.class) && ((Helmet) eq).getHp() > this.helmet.getHp()){
                    System.out.println("You found new Chub!\nThis Chub hp "
                            + ((Helmet) eq).getHp() +" and my Chub hp " + this.helmet.getHp() +
                            ". I'll take new one!");
                    this.setHelmet((Helmet)eq);
                }
            }
            System.out.println(eq);
        }
    }
}
