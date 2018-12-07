package view.console;

import view.gui.ShowMenuInterface;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ShowMenuConsole implements ShowMenuInterface {

    @Override
    public String showMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("       Menu\n\n\n\n");
        System.out.println("       Resume\n\n");
        System.out.println("       Save\n\n");
        System.out.println("       Exit\n\n");
        System.out.print("Please enter the command: ");

        while(!sc.hasNext(Pattern.compile("\\s*(Resume|Save|Exit)\\s*", Pattern.CASE_INSENSITIVE))){
            System.out.println("You entered wrong command");
            sc.next();
            System.out.print("Please enter the command: ");
        }
        return sc.nextLine();
    }
}
