package view;

import model.GameContext;
import model.characters.hero.Hero;
import view.View;


public interface MoveViewComplex extends View {
    void drawMap(Integer size, Integer x, Integer y, GameContext gameContext);

    String readUserInput();
}
