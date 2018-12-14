package view.gui;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import view.MainMenuView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainMenuGuiView extends MainMenuView  {
    private String message;
    public CountDownLatch c;
    JButton buttonNewGame = new JButton("    NewGame    ");
    JButton buttonLoad = new JButton("     Load     ");
    JButton buttonExit = new JButton("     Exit     ");

    public MainMenuGuiView(){

    }

    @Override
    public void render() {
        this.c = new CountDownLatch(1);
        new ThreadStopper(c);
        try{
                c.await();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
    }

    private void view() {

//        container = TestPanel.frame.getContentPane();
//        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonNewGame.addActionListener(new ButtonEventListener());
        buttonLoad.addActionListener(new ButtonEventListener());
        buttonExit.addActionListener(new ButtonEventListener());
        buttonLoad.setPreferredSize(new Dimension(300, 50));
        buttonNewGame.setPreferredSize(new Dimension(300, 50));
        buttonExit.setPreferredSize(new Dimension(300, 50));
        TestPanel.container.add(buttonNewGame, gbc);
        TestPanel.container.add(buttonLoad, gbc);
        TestPanel.container.add(buttonExit, gbc);
    }


    @Override
    public String readUserInput() {

        return message;
    }


    class ButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            message = e.getActionCommand().replaceAll("\\s+","").toLowerCase();
          //  TestPanel.frame.getContentPane().removeAll();

            TestPanel.container.revalidate();
            TestPanel.container.repaint();
            c.countDown();

        }
    }
    class ThreadStopper implements  Runnable{

        private ThreadStopper(CountDownLatch cdl2){
            c = cdl2;
            new Thread(this).run();
        }

        @Override
        public void run() {
            view();
        }

    }
}
