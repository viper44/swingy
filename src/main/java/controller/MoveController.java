package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import model.GameContext;
import view.ComplexView;
import view.MoveViewComplex;
import view.SimpleView;
import view.cons.MoveConsoleView;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoveController extends AbstractController {
	Map<String, Runnable> actionList = new HashMap<>();
	UnaryOperator<Integer> getMapSize = i -> (i - 1) * 5 + 10 - (i % 2);
	Map<Integer, MoveViewComplex> moveManager = new HashMap<>();
	Map<Integer, SimpleView> winManager = new HashMap<>();


	public MoveController(MoveViewComplex moveGuiView, SimpleView winGuiView, MoveViewComplex moveConsoleView, SimpleView winConsoleView) {
		moveManager.put(1, moveGuiView);
		moveManager.put(2, moveConsoleView);
		winManager.put(1, winGuiView);
		winManager.put(2, winConsoleView);
		actionList.put("south", this::goSouth);
		actionList.put("north", this::goNorth);
		actionList.put("west", this::goWest);
		actionList.put("east", this::goEast);
		actionList.put("menu", this::menu);
	}

	@Override
	public void process() {
		Integer size = getMapSize.apply(context.getHero().getLevel());
		String selectedWay = "menu";
		while(selectedWay.equals("menu")){

			moveManager.get(context.getSequence()).drawMap(size, context.getHero().getCoordinates().getX(), context.getHero().getCoordinates().getY(), context);
			context.setPreviousX(context.getHero().getCoordinates().getX());
			context.setPreviousY(context.getHero().getCoordinates().getY());
			selectedWay = moveManager.get(context.getSequence()).readUserInput().toLowerCase();
			if (selectedWay.equals("menu")) {
				menuController.process();
			}
		}


		actionList.get(selectedWay).run();
		if(context.getHero().getCoordinates().getX() == size + 1 || context.getHero().getCoordinates().getX() == 0 ||
				context.getHero().getCoordinates().getY() == 0 || context.getHero().getCoordinates().getY() == size + 1){
			winManager.get(context.getSequence()).render();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(777);
		}
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

	private void menu(){}
}
