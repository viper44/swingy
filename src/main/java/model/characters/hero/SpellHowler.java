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

import java.util.ArrayList;

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
        for (Equipment eq: e) {
            if (eq instanceof Weapon){
                if (eq.getClass().equals(Staff.class) && ((Weapon) eq).getDmg() > this.weapon.getDmg()){
                    System.out.println("You found new Staff!\nThis Staff damage "
                            + ((Weapon) eq).getDmg() +" and my Staff damage " + this.weapon.getDmg() +
                            ". I'll take new one!");
                    this.setWeapon((Weapon)eq);
                }
            }
            if (eq instanceof Armor){
                if (eq.getClass().equals(Tunic.class) && ((Armor) eq).getDefense() > this.armor.getDefense()){
                    System.out.println("You found new Tunic!\nThis Tunic defense "
                            + ((Armor) eq).getDefense() +" and my Tunic defense " + this.armor.getDefense() +
                            ". I'll take new one!");
                    this.setArmor((Armor)eq);
                }
            }
            if (eq instanceof Helmet){
                if (eq.getClass().equals(Crown.class) && ((Helmet) eq).getHp() > this.helmet.getHp()){
                    System.out.println("You found new Crown!\nThis Crown hp "
                            + ((Helmet) eq).getHp() +" and my Crown hp " + this.helmet.getHp() +
                            ". I'll take new one!");
                    this.setHelmet((Helmet)eq);
                }
            }
            System.out.println(eq);
        }
    }

}
