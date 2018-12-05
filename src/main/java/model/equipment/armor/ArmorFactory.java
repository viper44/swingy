package model.equipment.armor;

import model.equipment.Equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Function;

/**
 * Created by msemenov on 11/14/18.
 */
public abstract class ArmorFactory {
    private final static HashMap<Class<? extends Armor>, Function<Integer, Armor>> ARMOR_SUPPLIER = new HashMap<>();
    private static final ArrayList<Class<? extends Armor>> armors = new ArrayList<>();

    static {
        ARMOR_SUPPLIER.put(Tunic.class, Tunic::new);
        ARMOR_SUPPLIER.put(HeavyArmor.class, HeavyArmor::new);
        ARMOR_SUPPLIER.put(LightArmor.class, LightArmor::new);
        armors.add(Tunic.class);
        armors.add(HeavyArmor.class);
        armors.add(LightArmor.class);
    }

        static public <A extends Armor>  A newArmor(Class<? extends Equipment> ArmorClass){
        int defense = new Random().nextInt(11 - 5) + 5;
            return (A) ARMOR_SUPPLIER.get(ArmorClass).apply(defense);
        }
    static public <A extends Armor>  A newArmor(){
        int index = new Random().nextInt(armors.size());
        int defense = new Random().nextInt(11 - 5) + 5;
        return (A) ARMOR_SUPPLIER.get(armors.get(index)).apply(defense);
    }
}
