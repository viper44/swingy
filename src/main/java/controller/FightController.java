package controller;

import lombok.Data;
import model.Loot;
import model.characters.Characters;
import model.characters.monsters.Monster;
import view.ComplexView;
import view.cons.FightConsoleView;

import java.util.Random;

@Data
public class FightController extends AbstractController {
    Monster monster;
    Characters first;
    Characters sec;
    GetLootController getLootController;
    ComplexView<String> fightView = new FightConsoleView();
    int checker = new Random().nextInt(10);

    public FightController(GetLootController getLootController){
        this.getLootController = getLootController;
    }
    @Override
    public void process() {
        swap(context.getHero(), monster);
        while(first.getHpCur() > 0 && sec.getHpCur() > 0){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){

            }
            swap(context.getHero(), monster);
            int damage = sec.getDefense() - first.getDamage() > 0 ? 0 : sec.getDefense() - first.getDamage();
            sec.setHpCur(sec.getHpCur() + damage);
            fightView.render(first.getName() + " hits " + sec.getName() + " with " +
                    damage * -1 + " " + sec.getName() +
                    " current hp = " + sec.getHpCur() + "/" + sec.getHpMax());
        }
        if (context.getHero().getHpCur() > 0) {
            getLootController.process();
//            context.getHero().getLoot(Loot.getLoot());
            context.getHero().updateConditions();
        }
        checker =  new Random().nextInt(10);
    }
    private  void swap(Characters hero, Characters monster){
        if (checker % 2 == 0){
            first = hero;
            sec = monster;
        } else {
            sec = hero;
            first = monster;
        }
        checker++;

    }

}
