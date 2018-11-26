package model.equipment.weapon;

/**
 * Created by msemenov on 11/14/18.
 */
public class Staff extends Weapon{
    protected Staff(int dmg){
        super(dmg * 15 / 10);
    }
}
