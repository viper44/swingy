package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by msemenov on 11/14/18.
 */
public class SimpleGUI extends JFrame{
    private JButton buttonStart = new JButton("Start");
    private JButton buttonLoad = new JButton("Load");
    private JButton buttonExit = new JButton("Exit");
    private JTextField input = new JTextField(30);
    private JLabel label = new JLabel("Input Hero name:");
    private JRadioButton radio1 = new JRadioButton("Select this");
    private JRadioButton radio2 = new JRadioButton("Select that");
    private JCheckBox check = new JCheckBox("Check", false);

    public SimpleGUI() {

        super("Main menu");
        this.setSize(600, 1000);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(300,0,0,0));
        panel.setLayout(new GridLayout(3, 1, 50, 50));
        panel.setPreferredSize(new Dimension(300, 600));
        panel.setMinimumSize(new Dimension(300, 600));
        panel.setMaximumSize(new Dimension(300, 800));
        buttonStart.setPreferredSize(new Dimension(200, 30));
        buttonStart.setMaximumSize(new Dimension(200, 30));
        buttonStart.setMinimumSize(new Dimension(200, 30));
        buttonLoad.setPreferredSize(new Dimension(200, 30));
        buttonLoad.setMaximumSize(new Dimension(200, 30));
        buttonExit.setPreferredSize(new Dimension(200, 30));
        buttonExit.setMaximumSize(new Dimension(200, 30));
        buttonStart.addActionListener(new ButtonEventListener());
        panel.add(buttonStart);
        panel.add(buttonLoad);
        panel.add(buttonExit);
        this.add(panel);

    }
    class ButtonEventListener implements ActionListener{
        public void actionPerformed(ActionEvent event){

        }
    }

}
