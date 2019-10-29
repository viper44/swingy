package view.cons;

import model.GameContext;
import model.characters.hero.Hero;
import view.ComplexView;
import view.MoveViewComplex;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MoveConsoleView implements MoveViewComplex {
	@Override
	public void drawMap(Integer size, Integer HeroPosX, Integer HeroPosY, GameContext gameContext) {
		System.out.println(gameContext.getHero());
		System.out.print("     ");
		for (int i = 0; i < size; i++) {
			System.out.print('_');
		}
		System.out.println();
		for (int y = 1; y < size + 1; y++) {
			System.out.print("    |");
			for (int x = 1; x < size + 1; x++) {
				if (HeroPosX == x && HeroPosY == y) {
					System.out.print("H");
				} else {
					System.out.print("*");
				}
			}
			System.out.println("|");
		}
		System.out.print("     ");
		for (int i = 0; i < size; i++) {
			System.out.print('¯');
		}
		System.out.println();
		System.out.println("          ↑");
		System.out.println("        North");
		System.out.print("← West     ");
		System.out.println("   East →");
		System.out.println("        South");
		System.out.println("          ↓");
	}

	@Override
	public String readUserInput() {
		System.out.println("To enter main menu type menu");
		System.out.println("do you know da wae?");
		System.out.print("Please choose da wae (North, South, West, East): ");
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNext(Pattern.compile(("\\s*(North|South|East|West|Menu)\\s*"), Pattern.CASE_INSENSITIVE))) {
			System.out.println("You enter wrong way");
			System.out.println("To enter main menu type menu");
			sc.nextLine();
			System.out.print("Please choose da wae (North, South, West, East): ");
		}
		return sc.nextLine().toLowerCase();
	}
}
