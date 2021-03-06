package model.equipment.weapon;

import model.equipment.Equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Function;

/**
 * Created by msemenov on 11/14/18.
 */
public abstract class WeaponFactory {
    private static final HashMap<Class<? extends Weapon>, Function<Integer, Weapon>> WEAPON_CREATOR = new HashMap<>();
    private static final ArrayList<Class<? extends Weapon>> weapons = new ArrayList<>();
    static {
        WEAPON_CREATOR.put(Dagger.class, Dagger::new);
        WEAPON_CREATOR.put(Staff.class, Staff::new);
        WEAPON_CREATOR.put(Sword.class, Sword::new);
        weapons.add(Dagger.class);
        weapons.add(Staff.class);
        weapons.add(Sword.class);
    }

    public static <W  extends Weapon> W newWeapon(Class <? extends Equipment> weaponClass){
        int damage = new Random().nextInt(60 - 30) + 30;
        return (W)WEAPON_CREATOR.get(weaponClass).apply(damage);
        }

    public static <W  extends Weapon> W newWeapon(){
        int index = new Random().nextInt(weapons.size());
        int damage = new Random().nextInt(60 - 30) + 30;
        return (W)WEAPON_CREATOR.get(weapons.get(index)).apply(damage);
    }
    }
