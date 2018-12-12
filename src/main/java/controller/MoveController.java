package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import view.ComplexView;
import view.MoveViewComplex;
import view.cons.MoveConsoleView;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoveController extends AbstractController {
    Map<String, Runnable> actionList = new HashMap<>();
    UnaryOperator<Integer> getMapSize = i-> (i - 1) * 5 + 10 - (i % 2);
    MoveViewComplex moveView;



    public MoveController() {
        moveView = new MoveConsoleView();

        actionList.put("south", this::goSouth);
        actionList.put("north", this::goNorth);
        actionList.put("west", this::goWest);
        actionList.put("east", this::goEast);
    }

    @Override
    public void process() {

        Integer size = getMapSize.apply(context.getHero().getLevel());
        moveView.drawMap(size, context.getHero().getCoordinates().getX(), context.getHero().getCoordinates().getY(), context.getHero());

        String selectedWay = moveView.readUserInput();
        if (selectedWay.equals("menu")){
            menuController.process();
            this.process();
        }
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
