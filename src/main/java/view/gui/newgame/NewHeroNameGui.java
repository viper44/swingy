package view.gui.newgame;

import view.SimpleView;
import view.gui.TestPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class NewHeroNameGui implements SimpleView {
	private String message;

	public NewHeroNameGui() {
	}

	@Override
	public void render() {
		CountDownLatch c;
		c = new CountDownLatch(1);
		new ThreadStopper(c);
		try {
			c.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String readUserInput() {

		return message;
	}


	class ThreadStopper implements Runnable {
		private CountDownLatch c2;
		private JTextField textField = new JTextField("hero name", 20);
		private JButton button = new JButton("submit");

		private ThreadStopper(CountDownLatch cdl2) {
			this.c2 = cdl2;
			this.run();
		}

		private void view() {

			button.addActionListener(new ButtonEventListener());
			TestPanel.panel.add(button);
			TestPanel.panel.add(textField);
			TestPanel.panel.repaint();
			TestPanel.frame.revalidate();


		}

		@Override
		public void run() {
			view();
		}

		class ButtonEventListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {

				message = textField.getText();
				TestPanel.panel.removeAll();
				TestPanel.panel.revalidate();
				TestPanel.panel.repaint();
				c2.countDown();
			}
		}
	}
}
