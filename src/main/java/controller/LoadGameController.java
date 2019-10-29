package controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Hero;
import view.ComplexView;
import view.SimpleView;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoadGameController extends AbstractController implements Supporter {
	ComplexView<List<Hero>> loadGameView;

	public LoadGameController(ComplexView<List<Hero>> loadGameView) {
		this.loadGameView = loadGameView;
	}

	@Override
	public void process() {
	}

	@Override
	public int supProcess() {
		List<Hero> heroList = dbManager.getHeroByid();
		if (heroList.isEmpty()) {
			return 0;
		}
		loadGameView.render(heroList);
		String selectedHero = loadGameView.readUserInput();
		List<Hero> heroes = heroList.stream().filter(h -> h.getId() == Integer.valueOf(selectedHero)).collect(Collectors.toList());
		Hero hero = heroes.stream().findFirst().get();
		hero.setPicPath(hero.initPic());
		context.setHero(hero);
		return 1;
	}
}
