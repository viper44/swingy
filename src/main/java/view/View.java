package view;

import view.console.MeetMonsterView;
import view.console.Menu;
import view.console.MoveView;
import view.console.NewGame;

import java.util.HashMap;

public abstract class View {
    public Menu menu;
    public MoveView moveView;
    public MeetMonsterView monsterView;

     public View(Menu menu, MoveView moveView, MeetMonsterView monsterView){
        this.menu = menu;
        this.moveView = moveView;
        this.monsterView = monsterView;
    }
//    public void manager(){
//         switch (menu.command){
//             case "NewGame" : {
//                 newGame.StartNewGame();
//                 break;
//             }
//             case "Exit" : {
//                 System.out.println("See You later!");
//                 System.exit(1);
//             }
//         }
//    }
}
