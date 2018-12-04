package model.equipment.armor;

/**
 * Created by msemenov on 11/14/18.
 */
public class HeavyArmor extends Armor {
    HeavyArmor(Integer def){
        super(def * 15 / 10);
    }
}
