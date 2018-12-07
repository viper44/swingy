package view;

import view.console.MeetMonsterView;
import view.console.Menu;
import view.console.MoveView;
import view.console.NewGame;
import view.gui.ShowMenuInterface;

import java.util.HashMap;

public abstract class View {
    public MenuInterface menu;
    public MoveViewInterface moveView;
    public MeetMonsterView monsterView;
    public NewGameInterface newGame;
    public LoadGameInterface loadGameInterface;
    public ShowMenuInterface showMenuInterface;

     public View(MenuInterface menu, MoveViewInterface moveView, MeetMonsterView monsterView,
                 NewGame newGame, LoadGameInterface loadGameInterface, ShowMenuInterface showMenuInterface){
        this.menu = menu;
        this.moveView = moveView;
        this.monsterView = monsterView;
        this.newGame = newGame;
        this.loadGameInterface = loadGameInterface;
        this.showMenuInterface = showMenuInterface;
    }
}
