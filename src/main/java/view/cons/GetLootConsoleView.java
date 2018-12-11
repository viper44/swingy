package view.cons;

import model.equipment.Equipment;
import view.ComplexView;
import view.SimpleView;

import java.util.Scanner;

public class GetLootConsoleView implements ComplexView<Equipment> {

    @Override
    public void render(Equipment equipment) {
        System.out.println("You found new equipment" + " " + equipment.toString());
        System.out.print("Do You want to take it ? :");
    }

    @Override
    public String readUserInput() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNext("\\s*[yn]\\s*")) {
            sc.nextLine();
        }
        return sc.nextLine().toLowerCase();
    }
}
