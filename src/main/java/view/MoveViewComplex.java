package view;

import model.characters.hero.Hero;
import view.View;


public interface MoveViewComplex extends View {
    void drawMap(Integer size, Integer x, Integer y, Hero hero);
}
