package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Hero;
import view.ComplexView;
import view.SimpleView;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoadGameController extends AbstractController implements Supporter {
	Map<Integer, ComplexView<List<Hero>>> loadViewManager = new HashMap<>();

	public LoadGameController(ComplexView<List<Hero>> loadGuiView, ComplexView<List<Hero>> loadConsoleView) {
		loadViewManager.put(1, loadGuiView);
		loadViewManager.put(2, loadConsoleView);
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
		loadViewManager.get(context.getSequence()).render(heroList);
		String selectedHero = loadViewManager.get(context.getSequence()).readUserInput();
		List<Hero> heroes = heroList.stream().filter(h -> h.getId() == Integer.valueOf(selectedHero)).collect(Collectors.toList());
		Hero hero = heroes.stream().findFirst().get();
		hero.setPicPath(hero.initPic());
		context.setHero(hero);
		return 1;
	}
}
