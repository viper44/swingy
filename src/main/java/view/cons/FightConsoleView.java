package view.cons;

import view.ComplexView;
import view.SimpleView;

public class FightConsoleView implements ComplexView<String> {
    @Override
    public void render(String output) {
        System.out.println(output);
    }

    @Override
    public String readUserInput() {
        return null;
    }
}
