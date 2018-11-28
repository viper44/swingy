package view.console;

import model.characters.monsters.Monster;

import java.util.Scanner;

public class MeetMonsterView {

    public String runOrFight(Monster monster){
        System.out.println("You meet monster");
        System.out.println(monster);
        System.out.println("You can RUN or FIGHT");
        return chooseCommand();
    }

    private String chooseCommand() {
        System.out.print("Enter command Run or Fight: ");
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext("\\s*(Run|Fight)\\s*")){
            return sc.nextLine();
        } else {
            System.out.println("You enter wrong command");
            chooseCommand();
        }
        return null;
    }
}
