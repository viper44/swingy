package view.console;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class Menu {
   public static NewGame newGame = new NewGame();
    static Map<String, FuncInterTest> commands = new HashMap<>();
    public FuncInterTest kek;
    @NotNull
    @Pattern(regexp = "(NewGame|Load|Exit)", message = "Please choose type hero")
    public String command;
    Menu() {
        getCommand();
        kek = commands.get(command);
    }
    private void getCommand() {
        System.out.println("NewGame\nLoad\nExit");
        System.out.print("Please choose the command: ");
        try {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNext("\\s*(NewGame|Load|Exit)\\s*")) {
                this.command = sc.nextLine();
            } else {
                System.out.println("You entered wrong command");
                getCommand();
            }

        } catch (Exception e){

        }
    }
    static {
        commands.put("NewGame", newGame::StartNewGame);
        commands.put("Exit", new Exit()::finishGame);
    }

}
