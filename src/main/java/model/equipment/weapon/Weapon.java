package model.equipment.weapon;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Hero;
import model.equipment.Equipment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by msemenov on 11/14/18.
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Weapon extends Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "damage")
    private int dmg;

     Weapon(int dmg){
        this.dmg = dmg;
    }
     Weapon(){}
    public String toString(){
        return this.getClass().getSimpleName() + " damage: " + this.dmg;
    }
}

