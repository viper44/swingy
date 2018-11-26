package model.characters;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Characters {
    int hpCur;
    int hpMax;
    int damage;
    int defense;
    String name;

    public Characters(int hp, int damage, int defense){
        this.hpCur = hp;
        this.hpMax = hp;
        this.damage = damage;
        this.defense = defense;
    }
}
