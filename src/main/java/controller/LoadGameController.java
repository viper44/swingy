package controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Hero;
import view.ComplexView;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoadGameController extends AbstractController {
    ComplexView<List<Hero>> loadGameView;

    public LoadGameController(ComplexView<List<Hero>> loadGameView) {
        this.loadGameView = loadGameView;
    }

    @Override
    public void process() {
        List<Hero> heroList = dbManager.getHeroByid();

        loadGameView.render(heroList);
        String selectedHero = loadGameView.readUserInput();
        Hero hero = heroList.stream().filter(h -> h.getId() != Integer.valueOf(selectedHero)).collect(Collectors.toList()).get(0);
        hero.setPicPath(hero.initPic());
        context.setHero(hero);
    }
}
