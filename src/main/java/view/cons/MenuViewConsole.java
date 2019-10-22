package view.cons;

import view.SimpleView;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MenuViewConsole implements SimpleView {
	@Override
	public void render() {
		System.out.println("\n\nResume\n\nSave\n\nExit");
	}

	@Override
	public String readUserInput() {
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNext(Pattern.compile("\\s*(resume|save|exit)\\s*", Pattern.CASE_INSENSITIVE))) {
			sc.nextLine();
		}
		return sc.nextLine().toLowerCase();
	}
}
