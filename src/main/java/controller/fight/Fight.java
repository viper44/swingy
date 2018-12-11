package controller.fight;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.Characters;

import java.util.Random;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Fight {
     Characters first;
     Characters sec;
     int checker = new Random().nextInt(10);

     public  void figthMode(Characters hero, Characters monster) {

         swap(hero, monster);
         while(first.getHpCur() > 0 && sec.getHpCur() > 0){
             try{
                 Thread.sleep(1000);
             } catch (InterruptedException e){

             }
             swap(hero, monster);
             int damage = sec.getDefense() - first.getDamage() > 0 ? 0 : sec.getDefense() - first.getDamage();
             sec.setHpCur(sec.getHpCur() + damage);
             System.out.println(first.getName() + " hits " + sec.getName() + " with " +
                     damage * -1 + " " + sec.getName() +
                     " current hp = " + sec.getHpCur() + "/" + sec.getHpMax());
        }
        checker =  new Random().nextInt(10);
    }

        public Fight() {
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
