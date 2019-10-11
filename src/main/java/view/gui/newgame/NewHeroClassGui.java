package view.gui.newgame;

import view.SimpleView;
import view.gui.TestPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class NewHeroClassGui implements SimpleView {
    private String message;

    @Override
    public void render() {
        CountDownLatch c;
        c = new CountDownLatch(1);
        new ThreadStopper(c);
        try{
            c.await();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String readUserInput() {

        return message;
    }
    class ThreadStopper implements  Runnable{
        private CountDownLatch c2;
        private JTextField textField = new JTextField("hero name",20);
        private JButton button = new JButton("SpellHowler");
        private JButton button2 = new JButton("TreasureHunter");
        private JButton button3 = new JButton("DarkKnight");
        private ThreadStopper(CountDownLatch cdl2){
            this.c2 = cdl2;
            this.run();
        }
        private void view() {

            TestPanel.panel .setLayout(new FlowLayout());
            JPanel spell = new JPanel();
            JPanel tres = new JPanel();
            JPanel dark = new JPanel();
            Dimension dimension = TestPanel.panel.getSize();
            int width = dimension.width / 3 * 5 / 10;
            int height = dimension.height * 7 / 10;
            spell.setPreferredSize(new Dimension(width, height));
            spell.setBackground(Color.BLUE);
            tres.setPreferredSize(new Dimension(width, height));
            tres.setBackground(Color.YELLOW);
            dark.setPreferredSize(new Dimension(width, height));
            dark.setBackground(Color.GREEN);
            TestPanel.panel.add(spell);
            TestPanel.panel.add(tres);
            TestPanel.panel.add(dark);
            button.addActionListener(new ButtonEventListener());
            button2.addActionListener(new ButtonEventListener());
            button3.addActionListener(new ButtonEventListener());
            TestPanel.panel.add(button);
            TestPanel.panel.add(button2);
            TestPanel.panel.add(button3);
            TestPanel.panel.repaint();
            TestPanel.frame.revalidate();


        }

        class ButtonEventListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                message = ((JButton)e.getSource()).getText();
                TestPanel.panel.removeAll();
                TestPanel.panel.revalidate();
                TestPanel.panel.repaint();
                c2.countDown();
            }
        }
        @Override
        public void run() {
            view();
        }
    }
}
