package view.cons;

import java.util.Scanner;
import java.util.regex.Pattern;
import view.MainMenuView;


public class MainMenuConsoleView extends MainMenuView {

    @Override
    public void render() {
        System.out.println("NewGame\nLoad\nExit");
        System.out.print("Please choose the command: ");
    }

    @Override
    public String readUserInput() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNext(Pattern.compile("\\s*(NewGame|Load|Exit)\\s*", Pattern.CASE_INSENSITIVE))) {
            System.out.println("You entered wrong command");
            sc.nextLine();
            System.out.print("Please choose the command: ");
        }

        return sc.nextLine().toLowerCase();
    }
}
