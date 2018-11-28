package view.console;

import lombok.Data;
import view.View;
import view.ViewFunc;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ViewConsole extends View implements ViewFunc {

       public ViewConsole(){
        super(new Menu(), new MoveView(), new MeetMonsterView());
    }



}
