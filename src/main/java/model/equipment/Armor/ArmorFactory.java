package model.equipment.Armor;

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
            return (A) ARMOR_SUPPLIER.get(ArmorClass).apply((int)(Math.random()*((15-5)+1)) + 5);
        }
    static public <A extends Armor>  A newArmor(){
        int index = new Random().nextInt(armors.size());
        return (A) ARMOR_SUPPLIER.get(armors.get(index)).apply((int)(Math.random()*((15-5)+1)) + 5);
    }
    }
