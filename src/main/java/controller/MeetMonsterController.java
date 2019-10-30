package controller;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Coordinates;
import model.characters.monsters.Monster;
import model.characters.monsters.MonsterFactory;
import view.ComplexView;
import view.SimpleView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetMonsterController extends AbstractController {
	FightController fightController;
	Map<Integer, ComplexView<Monster>> meetMonsterViewManager = new HashMap<>();
	Map<Integer, SimpleView> escapeFailViewManager = new HashMap<>();
	Map<Integer, SimpleView> escapeSuccessViewManager = new HashMap<>();
	Monster monster;

	public MeetMonsterController(FightController fightController, ComplexView<Monster> meetMonsterGuiView, SimpleView escapeFGuiView, SimpleView escapeSGuiView,
								 ComplexView<Monster> meetMonsterConsoleView, SimpleView escapeFConsoleView, SimpleView escapeSConsoleView) {
		this.fightController = fightController;
		meetMonsterViewManager.put(1, meetMonsterGuiView);
		meetMonsterViewManager.put(2, meetMonsterConsoleView);
		escapeFailViewManager.put(1, escapeFGuiView);
		escapeFailViewManager.put(2, escapeFConsoleView);
		escapeSuccessViewManager.put(1, escapeSGuiView);
		escapeSuccessViewManager.put(2, escapeSConsoleView);
	}

	@Override
	public void process() {
		if (new Random().nextDouble() < 0.68) {
			monster = MonsterFactory.newMonster(context.getHero().getLevel());
			meetMonsterViewManager.get(context.getSequence()).render(monster);
			fightController.setMonster(monster);
			String command = meetMonsterViewManager.get(context.getSequence()).readUserInput().toLowerCase();
			if (command.equals("fight")) {
				fightController.process();
			} else if (command.equals("run")) {
				if (new Random().nextBoolean()) {
					escapeSuccessViewManager.get(context.getSequence()).render();
					context.setHero(context.getHero().setCoordinates(new Coordinates(context.getPreviousX(), context.getPreviousY())));
				} else {
					escapeFailViewManager.get(context.getSequence()).render();
					fightController.process();
				}
			}
		}
	}
}
