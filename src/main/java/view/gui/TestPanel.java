package view.gui;

import javax.swing.*;
import java.awt.*;

public class TestPanel {
	public static JFrame frame = new JFrame("Swingy");
	public static JPanel panel = new JPanel();

	static {
		frame.setVisible(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int height = dimension.height * 6 / 10;
		int width = dimension.width * 6 / 10;
		frame.setBounds(dimension.width / 2 - width / 2, dimension.height / 2 - height / 2, width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new GridBagLayout());
		frame.add(panel);
	}
}
