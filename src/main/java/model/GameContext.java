package model;

import lombok.Data;
import lombok.experimental.Accessors;
import model.characters.hero.Hero;


@Data
@Accessors(chain = true)
public class GameContext {
    Hero hero;
}
