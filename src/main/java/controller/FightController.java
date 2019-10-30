package controller;

import lombok.Data;
import model.Loot;
import model.characters.Characters;
import model.characters.monsters.Monster;
import view.ComplexView;
import view.SimpleView;
import view.cons.FightConsoleView;
import view.gui.TestPanel;
import view.gui.move.FightGuiView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Data
public class FightController extends AbstractController {
	Monster monster;
	Characters first;
	Characters sec;
	GetLootController getLootController;
	Map<Integer, ComplexView<String>> fightViewManager = new HashMap();
	Map<Integer, SimpleView> dieViewManager = new HashMap<>();
	int checker = new Random().nextInt(10);

	public FightController(GetLootController getLootController, ComplexView<String> fightGuiView, SimpleView diedGuiView, ComplexView<String> fightConsoleView, SimpleView diedConsoleView) {
		fightViewManager.put(1, fightGuiView);
		fightViewManager.put(2, fightConsoleView);
		dieViewManager.put(1, diedGuiView);
		dieViewManager.put(2, diedConsoleView);
		this.getLootController = getLootController;
	}

	@Override
	public void process() {
		swap(context.getHero(), monster);
		while (first.getHpCur() > 0 && sec.getHpCur() > 0) {
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {

			}
			swap(context.getHero(), monster);
			int damage = sec.getDefense() - first.getDamage() > 0 ? 0 : sec.getDefense() - first.getDamage();
			sec.setHpCur(sec.getHpCur() + damage);
			fightViewManager.get(context.getSequence()).render(first.getName() + " hits " + sec.getName() + " with " +
					damage * -1 + " " + sec.getName() +
					" current hp = " + sec.getHpCur() + "/" + sec.getHpMax());
		}
		if (fightViewManager.get(context.getSequence()).getClass().equals(FightGuiView.class)) {
			TestPanel.panel.removeAll();
			TestPanel.panel.revalidate();
			TestPanel.panel.repaint();
		}
		if (context.getHero().getHpCur() > 0) {
			getLootController.process();
			context.getHero().updateConditions();
		} else {
			dieViewManager.get(context.getSequence()).render();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(1);
		}
		checker = new Random().nextInt(10);
	}

	private void swap(Characters hero, Characters monster) {
		if (checker % 2 == 0) {
			first = hero;
			sec = monster;
		} else {
			sec = hero;
			first = monster;
		}
		checker++;
	}
}
