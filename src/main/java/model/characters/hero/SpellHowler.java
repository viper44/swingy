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
//    @Transient
//    final Map<Class<? extends Equipment>, Handler> equipSetter = new HashMap<>();
//
//    {
//        equipSetter.put(this.weapon.getClass(), (Object o) -> setWeapon((Weapon) o));
//        equipSetter.put(this.helmet.getClass(), (Object o) -> setHelmet((Helmet) o));
//        equipSetter.put(this.armor.getClass(), (Object o) -> setArmor((Armor) o));
//    }


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

//    public void getLoot(ArrayList<? extends Equipment> e) {
//
//        Scanner sc = new Scanner(System.in);
//
//
//        for (Equipment eq : e) {
//                if (eq.getClass().equals(this.weapon.getClass()) | eq.getClass().equals(this.armor.getClass()) | eq.getClass().equals(this.helmet.getClass())) {
//                    System.out.println("You found new equipment" + " " + eq.toString());
//                    System.out.println("Do You want to take it ? :");
//                    while (!sc.hasNext("\\s*[yn]\\s*")) {
//                        sc.nextLine();
//                    }
//                    if (sc.nextLine().toLowerCase().equals("y")) {
//                        equipSetter.get(eq.getClass()).handle(eq);
//                    }
//                    System.out.println(eq);
//                }
//        }
//    }
}
