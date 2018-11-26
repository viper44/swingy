package model.equipment.helmet;

import model.equipment.Equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Function;

/**
 * Created by msemenov on 11/14/18.
 */
public abstract class HelmetFactory {
    private static final HashMap<Class<? extends Helmet>, Function<Integer, Helmet>> HELMET_CREATOR = new HashMap<>();
    private static final ArrayList<Class<? extends Helmet>> helmets = new ArrayList<>();
    static{
        HELMET_CREATOR.put(Crown.class, Crown::new);
        HELMET_CREATOR.put(Chub.class, Chub::new);
        HELMET_CREATOR.put(Bucket.class, Bucket::new);
        helmets.add(Crown.class);
        helmets.add(Chub.class);
        helmets.add(Bucket.class);

        }
    public static <H extends Helmet> H newHelmet(Class<? extends Equipment> HelmetClass){
        return (H) HELMET_CREATOR.get(HelmetClass).apply((int)(Math.random()*((50-10)+1)) + 10);
    }

    public static <H extends Helmet> H newHelmet(){
        int index = new Random().nextInt(helmets.size());
        return (H) HELMET_CREATOR.get(helmets.get(index)).apply((int)(Math.random()*((50-10)+1)) + 10);
    }

}
