package view.cons;

import view.SimpleView;

public class WinConsoleView implements SimpleView {
	@Override
	public void render() {
		System.out.println("You win. GG");
	}

	@Override
	public String readUserInput() {
		return null;
	}
}
