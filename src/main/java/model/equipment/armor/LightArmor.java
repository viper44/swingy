package model.equipment.armor;

/**
 * Created by msemenov on 11/14/18.
 */
public class LightArmor extends Armor {
    LightArmor(Integer def){
        super((def * 9 / 10));
    }
}
