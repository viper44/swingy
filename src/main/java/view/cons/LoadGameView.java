package view.cons;

import java.util.List;
import java.util.Scanner;
import model.characters.hero.Hero;
import view.ComplexView;


public class LoadGameView implements ComplexView<List<Hero>> {

    @Override
    public void render(List<Hero> heroes) {
        for (int i = 0; i < heroes.size(); i++) {
            System.out.println("Hero id# " + i + 1);
            System.out.println(heroes.get(i));
        }
    }

    @Override
    public String readUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please choose Hero. Enter id:");
        String selectedHero = sc.nextLine();

        return selectedHero;
    }
}
