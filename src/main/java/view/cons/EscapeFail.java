package view.cons;

import view.SimpleView;

public class EscapeFail implements SimpleView {
    @Override
    public void render() {
        System.out.println("You fail to escape!\n Prepare to fight!");
    }

    @Override
    public String readUserInput() {
        return null;
    }
}
