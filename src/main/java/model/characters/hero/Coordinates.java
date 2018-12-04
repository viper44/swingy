package model.characters.hero;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by msemenov on 11/14/18.
 */
@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int x;
    int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
}
