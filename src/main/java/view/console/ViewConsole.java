package view.console;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import view.ViewFunc;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.Scanner;

public class ViewConsole implements ViewFunc {
    @NotNull
    @Pattern(regexp = "(SpellHowler|treasureHunter|DarkKnight)", message = "Please choose type hero")
    private String HeroType;
    private String Name;
    @Override
    public void MenuDraw() {
        System.out.println("NewGame\nLoad\nExit");
        System.out.print("Please choose the command: ");
        try(Scanner sc = new Scanner(System.in)) {
            String input = sc.nextLine();
            if (input.equals("NewGame")){
                this.NewGame();
            } else if(input.equals("Load")){
                this.LoadGame();
            } else if (input.equals("Exit")){
                System.out.println("You exit game");
                System.exit(1);
            } else {
                System.out.println("You entered wrong command");
                this.MenuDraw();
            }
        } catch (Exception e){

        }
    }

    @Override
    public void NewGame() {
        HeroTypeScanner(this.HeroType);


    }

    private void NameScanner(String Name){
        try (Scanner sc = new Scanner(System.in)){
            System.out.println("The name must consist of letters and / or numbers (from 2 - 10 characters");
            System.out.print("Please choose Hero class: ");
            if (sc.hasNext("\\s*(SpellHowler|TreasureHunter|DarkKnight)\\s*")){
                Name = sc.nextLine();
            } else {
                System.out.println("You entered wrong hero type");
                this.HeroTypeScanner(Name);
            }
        }
    }

    private void HeroTypeScanner(String HeroType) {
        try (Scanner sc = new Scanner(System.in)){
            System.out.println("SpellHowler           TreasureHunter          DarkKnight");
            System.out.print("Please choose Hero class: ");
            if (sc.hasNext("\\s*(SpellHowler|TreasureHunter|DarkKnight)\\s*")){
                 HeroType = sc.nextLine();
            } else {
                System.out.println("You entered wrong hero type");
                this.HeroTypeScanner(HeroType);
            }
        }
    }

    @Override
    public void LoadGame() {

    }

    @Override
    public void ExitGame() {

    }

    @Override
    public void Game() {

    }

    @Override
    public void FightMode() {

    }
}
