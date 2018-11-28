package controller.fight;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.Characters;

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
             int damage = sec.getDefense() - first.getDamage() > 0 ? 0 : sec.getDefense() - first.getDamage();
             sec.setHpCur(sec.getHpCur() + damage);
             System.out.println(first.getName() + " hits " + sec.getName() + " with " +
                     damage * -1 + " " + sec.getName() +
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
