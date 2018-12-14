package view.gui;

import javax.swing.*;
import java.awt.*;

public class TestPanel {
    public static JFrame frame = new JFrame("Swingy");
    public static Container container = frame.getContentPane();

    static {
        frame.setVisible(true);
        frame.setBounds(500, 100, 1500, 1200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new GridBagLayout());
    }
}
