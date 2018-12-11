package controller;

import model.characters.hero.GetLoot;
import model.characters.hero.Handler;
import model.equipment.Equipment;
import model.equipment.armor.Armor;
import model.equipment.armor.ArmorFactory;
import model.equipment.helmet.Helmet;
import model.equipment.helmet.HelmetFactory;
import model.equipment.weapon.Weapon;
import model.equipment.weapon.WeaponFactory;
import view.ComplexView;

import javax.persistence.Transient;
import java.util.*;
import java.util.function.Supplier;

public class GetLootController  extends AbstractController{
    ArrayList<Equipment> equipment;
    ComplexView<Equipment> lootView;

    @Transient
    final Map<Class<? extends Equipment>, Handler> equipSetter = new HashMap<>();

    public GetLootController(ComplexView lootView){
        this.lootView = lootView;
    }

    private void initer(){
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
                    lootView.render(eq);
                    if (lootView.readUserInput().toLowerCase().equals("y")) {
                        equipSetter.get(eq.getClass()).handle(eq);
                    }
                }
            }
        }
    }

    private static final ArrayList<Supplier<Equipment>> LOOT_CREATOR = new ArrayList<>();

    static {
        LOOT_CREATOR.add(WeaponFactory::newWeapon);
        LOOT_CREATOR.add(HelmetFactory::newHelmet);
        LOOT_CREATOR.add(ArmorFactory::newArmor);
    }
    private static  <W extends Equipment> W lootGen(){
        int index = new Random().nextInt(LOOT_CREATOR.size());
        return (W)LOOT_CREATOR.get(index).get();
    }

    private void getLoot(){
        ArrayList<Equipment> ret = new ArrayList<>();
        ret.add(lootGen());
        ret.add(lootGen());
        equipment = ret;
    }

}
