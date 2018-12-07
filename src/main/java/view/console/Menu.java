package view.console;


import view.MenuInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Menu implements MenuInterface {

    Menu() {

    }
      public String getCommand() {
        Scanner sc = null;
        System.out.println("NewGame\nLoad\nExit");
        System.out.print("Please choose the command: ");
        try {
            sc = new Scanner(System.in);
            while(!sc.hasNext(Pattern.compile("\\s*(NewGame|Load|Exit)\\s*", Pattern.CASE_INSENSITIVE))){
                System.out.println("You entered wrong command");
                sc.nextLine();
                System.out.print("Please choose the command: ");
            }
        } catch (Exception e){

        }
          return sc.nextLine();
    }
}
