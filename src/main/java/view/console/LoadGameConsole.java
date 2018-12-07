package view.console;

import model.characters.hero.Hero;
import storage.HeroDbManager;
import view.LoadGameInterface;

import java.util.List;
import java.util.Scanner;

public class LoadGameConsole implements LoadGameInterface {

    @Override
    public Hero loadGame(HeroDbManager dbManager){
        Hero result;
        List<Hero> heroList = dbManager.getHeroByid();
        for (int i = 1; i < heroList.size() + 1; i++) {
            System.out.println("Hero id#" + " "  + i);
            System.out.println(heroList.get(i - 1));
        }
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Please choose Hero. Enter id:");
            Integer tmp =  Integer.valueOf(sc.nextLine());
            if (tmp >= 0 && tmp <= heroList.size()){
                result = heroList.get(tmp - 1);
                break;
            }
        }

        return result;
    }
}
