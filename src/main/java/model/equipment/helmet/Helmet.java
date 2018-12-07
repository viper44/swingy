package model.equipment.helmet;

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
@DynamicInsert
@DynamicUpdate
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Helmet extends Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String type;
    int hp;
    Helmet(final int hp){
        this.hp = hp;
        this.type = this.getClass().getSimpleName();
    }
    Helmet(){}
    public String toString(){
        return type + " hp: " + this.hp;
    }
}
