package view.gui.newgame;

import view.SimpleView;
import view.gui.MainMenuGuiView;
import view.gui.TestPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class NewHeroNameGui implements SimpleView {
    private String message;
    private CountDownLatch c;
    private JTextField textField = new JTextField("hero name",20);
    private JButton button = new JButton("submit");
    //private JFrame frame;
    //JFrame frame = new JFrame("Swingy");

    public NewHeroNameGui(){
        //frame = TestPanel.frame;
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
        //frame.setVisible(true);
        //frame.setBounds(500, 300, 1500, 1200);
       // frame.setResizable(false);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = new Container();
        container.setLayout(new GridBagLayout());


        button.addActionListener(new ButtonEventListener());
        container.add(textField);
        container.add(button);
//        TestPanel.frame.setContentPane(container);
//        TestPanel.frame.repaint();

        //Container container = TestPanel.frame.getContentPane();

//        TestPanel.container.setLayout(new GridBagLayout());
        TestPanel.container.add(textField);
        TestPanel.container.add(button);
        TestPanel.container.repaint();

    }


    @Override
    public String readUserInput() {

        return message;
    }


    class ButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

           message = textField.getText();
            c.countDown();
//            frame.removeAll();
//            frame.setVisible(false);
        }
    }
    class ThreadStopper implements  Runnable{

        private ThreadStopper(CountDownLatch cdl2){
            CountDownLatch c2 = cdl2;
            new Thread(this).start();
        }

        @Override
        public void run() {
            view();
        }

    }
}
