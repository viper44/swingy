package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;
import view.ComplexView;


public class MoveController extends AbstractController {
    Map<String, Runnable> actionList = new HashMap<>();
    UnaryOperator<Integer> getMapSize = i-> (i - 1) * 5 + 10 - (i % 2);
    ComplexView<Integer> moveView;

    public MoveController() {
        actionList.put("south", this::goSouth);
        actionList.put("north", this::goNorth);
        actionList.put("west", this::goWest);
        actionList.put("east", this::goEast);
    }

    @Override
    public void process() {
        Integer size = getMapSize.apply(context.getHero().getLevel());
        moveView.render(size);

        String selectedWay = moveView.readUserInput();
        actionList.get(selectedWay).run();
    }

    private void goSouth() {
        context.getHero().getCoordinates().setY(context.getHero().getCoordinates().getY() + 1);
    }

    private void goNorth() {
        context.getHero().getCoordinates().setY(context.getHero().getCoordinates().getY() - 1);
    }

    private void goWest() {
        context.getHero().getCoordinates().setX(context.getHero().getCoordinates().getX() - 1);
    }

    private void goEast() {
        context.getHero().getCoordinates().setX(context.getHero().getCoordinates().getX() + 1);
    }
}
