package controller.fight;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.Characters;
import model.characters.hero.Hero;
import model.characters.monsters.Monster;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Fight {
    static Characters first;
    static Characters sec;
    static int checker = new Random().nextInt(10);

     public static void figthMode(Characters hero, Characters monster) {

         Fight.swap(hero, monster);
         while(first.getHpCur() > 0 && sec.getHpCur() > 0){
             Fight.swap(hero, monster);
             sec.setHpCur(sec.getHpCur() + sec.getDefense() - first.getDamage());
             System.out.println(first.getName() + " hits " + sec.getName() + " with " +
                     (first.getDamage() - sec.getDefense()) + " " + sec.getName() +
                     " current hp = " + sec.getHpCur() + "/" + sec.getHpMax());
        }
    }

        public Fight() {
        }

        private static void swap(Characters hero, Characters monster){
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
