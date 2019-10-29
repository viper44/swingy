package view.cons;

import view.SimpleView;

public class DiedConsoleView implements SimpleView {
	@Override
	public void render() {
		System.out.println("You died. GG");
	}

	@Override
	public String readUserInput() {
		return null;
	}
}
