package view.cons;

import view.SimpleView;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MenuViewConsole implements SimpleView {
	@Override
	public void render() {
		System.out.println("\nResume\nSave\nExit\nChange");
	}

	@Override
	public String readUserInput() {
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNext(Pattern.compile("\\s*(resume|save|exit|change)\\s*", Pattern.CASE_INSENSITIVE))) {
			System.out.println("You enter wrong command");
			sc.nextLine();
		}
		return sc.nextLine().toLowerCase();
	}
}
