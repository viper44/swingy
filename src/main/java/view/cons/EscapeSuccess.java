package view.cons;

import view.SimpleView;

public class EscapeSuccess  implements SimpleView {
    @Override
    public void render() {
        System.out.println("You escape successfully ");
    }

    @Override
    public String readUserInput() {
        return null;
    }
}
