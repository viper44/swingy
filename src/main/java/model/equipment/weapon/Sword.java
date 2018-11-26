package model.equipment.weapon;

/**
 * Created by msemenov on 11/14/18.
 */
public class Sword extends Weapon{
    protected Sword(int dmg){
        super((dmg * 7 / 10));
    }
}
