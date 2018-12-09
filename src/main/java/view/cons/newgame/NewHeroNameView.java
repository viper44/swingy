package view.cons.newgame;

import java.util.Scanner;
import view.SimpleView;


public class NewHeroNameView implements SimpleView {

    @Override
    public void render() {
        System.out.println("The name must consist of letters and / or numbers (from 3 - 10 characters)");
        System.out.print("Please enter Hero name: ");
    }

    @Override
    public String readUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
