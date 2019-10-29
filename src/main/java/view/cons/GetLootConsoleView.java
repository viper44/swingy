package view.cons;

import model.GameContext;
import model.equipment.Equipment;
import view.ComplexView;
import view.LootComplexView;

import java.util.Scanner;
import java.util.regex.Pattern;

public class GetLootConsoleView implements LootComplexView<Equipment> {

	@Override
	public void render(Equipment equipment, GameContext gameContext) {
		System.out.println("You found new equipment" + " " + equipment.toString());
		System.out.print("Do You want to take it ? :");
	}

	@Override
	public String readUserInput() {
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNext(Pattern.compile("\\s*[yn]\\s*", Pattern.CASE_INSENSITIVE))) {
			System.out.println("You enter wrong command");
			sc.next();
			System.out.print("Enter command y or n : ");
		}
		String ret = sc.nextLine().toLowerCase();
		return ret;
	}
}
