package controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Coordinates;
import model.characters.hero.Hero;
import model.characters.hero.Hero.HeroBuilder;
import model.characters.hero.HeroClass;
import model.dto.NewHeroRequest;
import view.SimpleView;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NewGameController extends AbstractController implements Supporter {
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Map<Integer, SimpleView> nameManger = new HashMap<>();
	Map<Integer, SimpleView> typeManager = new HashMap<>();

	public NewGameController(SimpleView nameGuiView, SimpleView typeGuiView, SimpleView nameConsoleView, SimpleView typeConsoleView) {
		nameManger.put(1, nameGuiView);
		nameManger.put(2, nameConsoleView);
		typeManager.put(1, typeGuiView);
		typeManager.put(2, typeConsoleView);
	}

	public void process() {

	}

	private String getHeroName() {
		nameManger.get(context.getSequence()).render();
		return nameManger.get(context.getSequence()).readUserInput();
	}

	private boolean isValid(NewHeroRequest newHeroRequest) {
		Validator validator = factory.getValidator();
		return validator.validate(newHeroRequest).isEmpty();
	}

	@Override
	public int supProcess() {
		String heroName = getHeroName();

		NewHeroRequest newHeroRequest = new NewHeroRequest()
				.setName(heroName);
		while (!isValid(newHeroRequest)) {
			heroName = getHeroName();
			newHeroRequest.setName(heroName);
		}
		typeManager.get(context.getSequence()).render();
		String heroType = typeManager.get(context.getSequence()).readUserInput();

		Hero hero = new HeroBuilder()
				.heroName(heroName)
				.exp(0)
				.coordinates(new Coordinates(5, 5))
				.level(1)
				.build(HeroClass.valueOf(heroType.toUpperCase()));

		hero.initEquip();
		context.setHero(hero);
		return 1;
	}
}
