package model.equipment.armor;

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
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
@Entity
public class Armor extends Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private int defense;


    Armor(final int defense){
        this.defense = defense;
    }
    Armor(){}
    public String toString(){
        return this.getClass().getSimpleName() + " defense: " + this.defense;
    }
}
