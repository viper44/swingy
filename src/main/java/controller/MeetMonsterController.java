package controller;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Coordinates;
import model.characters.monsters.Monster;
import model.characters.monsters.MonsterFactory;
import view.ComplexView;
import view.SimpleView;

import java.util.Random;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetMonsterController extends AbstractController {
	FightController fightController;
	ComplexView<Monster> meetMonsterView;
	SimpleView escapeS;
	SimpleView escapeF;
	Monster monster;

	public MeetMonsterController(FightController fightController, ComplexView<Monster> meetMonsterView, SimpleView escapeF, SimpleView escapeS) {
		this.meetMonsterView = meetMonsterView;
		this.fightController = fightController;
		this.escapeF = escapeF;
		this.escapeS = escapeS;
	}

	@Override
	public void process() {
		if (new Random().nextDouble() < 0.68) {
			monster = MonsterFactory.newMonster(context.getHero().getLevel());
			meetMonsterView.render(monster);
			fightController.setMonster(monster);
			String command = meetMonsterView.readUserInput().toLowerCase();
			if (command.equals("fight")) {
				fightController.process();
			} else if (command.equals("run")) {
				if (new Random().nextBoolean()) {
					escapeS.render();
					context.setHero(context.getHero().setCoordinates(new Coordinates(context.getPreviousX(), context.getPreviousY())));
				} else {
					escapeF.render();
					fightController.process();
				}
			}
		}
	}
}
