package controller;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Coordinates;
import model.characters.hero.Hero;
import model.characters.hero.Hero.HeroBuilder;
import model.characters.hero.HeroClass;
import model.dto.NewHeroRequest;
import view.SimpleView;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NewGameController extends AbstractController {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    SimpleView nameView;
    SimpleView typeView;

    public NewGameController(SimpleView nameView, SimpleView typeView) {
        this.nameView = nameView;
        this.typeView = typeView;
    }

    @Override
    public void process() {
        String heroName = getHeroName();

        NewHeroRequest newHeroRequest = new NewHeroRequest()
                .setName(heroName);
        if (!isValid(newHeroRequest)) {
            heroName = getHeroName();
        }

        typeView.render();
        String heroType = typeView.readUserInput();

        Hero hero = new HeroBuilder()
                .heroName(heroName)
                .exp(0)
                .coordinates(new Coordinates(5, 5))
                .level(1)
                .build(HeroClass.valueOf(heroType));

        context.setHero(hero);
    }

    private String getHeroName() {
        nameView.render();
        return nameView.readUserInput();
    }

    private boolean isValid(NewHeroRequest newHeroRequest) {
        Validator validator = factory.getValidator();
        return validator.validate(newHeroRequest).isEmpty();
    }
}
