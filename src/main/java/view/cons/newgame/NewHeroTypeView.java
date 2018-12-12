package view.cons.newgame;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import view.SimpleView;


public class NewHeroTypeView implements SimpleView {
    Map<String, String> types = new HashMap<>();

    public  NewHeroTypeView(){
        types.put("sh", "spellhowler");
        types.put("th", "treasurehunter");
        types.put("dk", "darkknight");
    }

    @Override
    public void render() {
        System.out.println("SPELLHOWLER           TREASUREHUNTER          DARKKNIGHT");
        System.out.print("Please choose Hero class: ");
    }

    @Override
    public String readUserInput() {
        Scanner sc = new Scanner(System.in);

        while (!sc.hasNext(Pattern.compile("\\s*(sh|th|dk)\\s*", Pattern.CASE_INSENSITIVE))) {
            System.out.println("You entered wrong hero type");
            sc.nextLine();
            System.out.print("Please choose Hero class: ");
        }
        return types.get(sc.nextLine()).toUpperCase();
    }
}
