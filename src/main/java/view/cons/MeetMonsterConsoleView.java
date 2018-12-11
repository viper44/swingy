package view.cons;

import model.characters.hero.Hero;
import model.characters.monsters.Monster;
import view.ComplexView;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MeetMonsterConsoleView implements ComplexView<Monster> {
    @Override
    public void render(Monster monster) {
        System.out.println("You meet monster");
        System.out.println(monster);
        System.out.println("You can RUN or FIGHT");
    }

    @Override
    public String readUserInput() {
        System.out.print("Enter command Run or Fight: ");
        Scanner sc = new Scanner(System.in);
        while(!sc.hasNext(Pattern.compile("\\s*(Run|Fight)\\s*", Pattern.CASE_INSENSITIVE)))
        {
            System.out.println("You enter wrong command");
            sc.next();
            System.out.print("Enter command Run or Fight: ");
        }
        return sc.nextLine().toLowerCase();
    }
}
