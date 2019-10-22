package view.gui.move;

import view.SimpleView;
import view.gui.TestPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class EscapeSuccessGui implements SimpleView {
	@Override
	public void render() {
		CountDownLatch c;
		c = new CountDownLatch(1);
		new EscapeSuccessGui.ThreadStopper(c);
		try {
			c.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String readUserInput() { return null;}

	class ThreadStopper implements Runnable {
		private CountDownLatch c2;


		private ThreadStopper(CountDownLatch cdl2) {
			this.c2 = cdl2;
			this.run();
		}

		private void view() {
			TestPanel.panel.setLayout(new GridBagLayout());
			JButton startCrying = new JButton("I am pussy");
			startCrying.addActionListener(new EscapeSuccessGui.ThreadStopper.ButtonEventListener());
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(600, 600));
			JLabel meetMonster = new JLabel("You run like a pussy");

			panel.add(meetMonster);
			panel.add(startCrying);
			TestPanel.panel.add(panel, new GridBagConstraints());
			TestPanel.panel.repaint();
			TestPanel.frame.revalidate();
		}

		class ButtonEventListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
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
