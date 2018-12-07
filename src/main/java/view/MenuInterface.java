package view;

import view.console.FuncInterTest;

import java.util.HashMap;
import java.util.Map;

public interface MenuInterface {
    Map<String, FuncInterTest> commands = new HashMap<>();
    public FuncInterTest kek = null;
    String getCommand();
}
