package view.console;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Menu {
   public static NewGame newGame = new NewGame();
    static Map<String, FuncInterTest> commands = new HashMap<>();
    public FuncInterTest kek;
    public String command;
    Menu() {
        getCommand();
        kek = commands.get(command.toLowerCase());
    }
    private void getCommand() {
        System.out.println("NewGame\nLoad\nExit");
        System.out.print("Please choose the command: ");
        try {
            Scanner sc = new Scanner(System.in);
            while(!sc.hasNext(Pattern.compile("\\s*(NewGame|Load|Exit)\\s*", Pattern.CASE_INSENSITIVE))){
                System.out.println("You entered wrong command");
                sc.nextLine();
                System.out.print("Please choose the command: ");
            }
            this.command = sc.nextLine();
        } catch (Exception e){

        }
    }
    static {
        commands.put("newgame", newGame::StartNewGame);
        commands.put("Exit", new Exit()::finishGame);
    }

}
