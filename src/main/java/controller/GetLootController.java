package controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Handler;
import model.equipment.Equipment;
import model.equipment.armor.Armor;
import model.equipment.armor.ArmorFactory;
import model.equipment.helmet.Helmet;
import model.equipment.helmet.HelmetFactory;
import model.equipment.weapon.Weapon;
import model.equipment.weapon.WeaponFactory;
import view.ComplexView;
import view.LootComplexView;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetLootController extends AbstractController {
	private static final ArrayList<Supplier<Equipment>> LOOT_CREATOR = new ArrayList<>();

	static {
		LOOT_CREATOR.add(WeaponFactory::newWeapon);
		LOOT_CREATOR.add(HelmetFactory::newHelmet);
		LOOT_CREATOR.add(ArmorFactory::newArmor);
	}

	@Transient
	final Map<Class<? extends Equipment>, Handler> equipSetter = new HashMap<>();
	ArrayList<Equipment> equipment;
	Map<Integer, LootComplexView<Equipment>> lootViewManager = new HashMap<>();

	public GetLootController(LootComplexView<Equipment> lootGuiView, LootComplexView<Equipment> lootConsoleView)
	{
		lootViewManager.put(1, lootGuiView);
		lootViewManager.put(2, lootConsoleView);
	}

	private static <W extends Equipment> W lootGen() {
		int index = new Random().nextInt(LOOT_CREATOR.size());
		return (W) LOOT_CREATOR.get(index).get();
	}

	private void initer() {
		equipSetter.put(context.getHero().getWeapon().getClass(), (Object o) -> context.getHero().setWeapon((Weapon) o));
		equipSetter.put(context.getHero().getHelmet().getClass(), (Object o) -> context.getHero().setHelmet((Helmet) o));
		equipSetter.put(context.getHero().getArmor().getClass(), (Object o) -> context.getHero().setArmor((Armor) o));
	}

	@Override
	public void process() {
		{
			getLoot();
			initer();
			for (Equipment eq : equipment) {
				if (eq.getClass().equals(context.getHero().getWeapon().getClass()) |
						eq.getClass().equals(context.getHero().getArmor().getClass()) |
						eq.getClass().equals(context.getHero().getHelmet().getClass())) {
					lootViewManager.get(context.getSequence()).render(eq, context);
					String answer = lootViewManager.get(context.getSequence()).readUserInput().toLowerCase();
					if (answer.equals("y") || answer.equals("yes")) {
						equipSetter.get(eq.getClass()).handle(eq);
					}
				}
			}
		}
	}

	private void getLoot() {
		ArrayList<Equipment> ret = new ArrayList<>();
		ret.add(lootGen());
		ret.add(lootGen());
		equipment = ret;
	}

}
