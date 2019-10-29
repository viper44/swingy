package view.gui;

import com.google.common.collect.Lists;
import view.SimpleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MenuViewGui implements SimpleView {
	private String message;

	@Override
	public void render() {
		CountDownLatch c = new CountDownLatch(1);
		new MenuViewGui.ThreadStopper(c);
		try {
			c.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String readUserInput() {
		return message;
	}

	class ThreadStopper implements Runnable {
		CountDownLatch cdl;

		private void view() {
			TestPanel.panel.setLayout(new GridBagLayout());
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(400, 400));
			JButton buttonNewGame = new JButton("    Resume    ");
			JButton buttonLoad = new JButton("     Save     ");
			JButton buttonExit = new JButton("     Exit     ");
			JButton buttonChange = new JButton("   Change   ");
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			List<JButton> components = Lists.newArrayList(buttonNewGame, buttonLoad, buttonExit, buttonChange);
			components.forEach(button -> button.addActionListener(new ButtonEventListener()));
			components.forEach(button -> button.setPreferredSize(new Dimension(300, 50)));
			components.forEach(button -> panel.add(button, gbc));
			TestPanel.panel.add(panel, new GridBagConstraints());
			TestPanel.panel.repaint();
			TestPanel.frame.revalidate();
		}

		private ThreadStopper(CountDownLatch c) {
			cdl = c;
			this.run();
		}

		@Override
		public void run() {
			view();
		}

		class ButtonEventListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {

				message = e.getActionCommand().replaceAll("\\s+", "").toLowerCase();
				TestPanel.panel.removeAll();
				TestPanel.panel.revalidate();
				TestPanel.panel.repaint();
				cdl.countDown();
			}
		}
	}
}
