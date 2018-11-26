package model.characters.hero;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Damage {
    int damage = 10;

    public Damage(int damage){
        this.damage += damage;
    }
}
