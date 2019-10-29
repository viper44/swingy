package view.gui;


import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import view.MainMenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainMenuGuiView extends MainMenuView {
	private String message;

	public MainMenuGuiView() {
	}

	@Override
	public void render() {
		CountDownLatch c = new CountDownLatch(1);
		new ThreadStopper(c);
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
			JButton buttonNewGame = new JButton("    NewGame    ");
			JButton buttonLoad = new JButton("     Load     ");
			JButton buttonExit = new JButton("     Exit     ");
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			buttonNewGame.addActionListener(new ButtonEventListener());
			buttonLoad.addActionListener(new ButtonEventListener());
			buttonExit.addActionListener(new ButtonEventListener());
			List<Component> components = Lists.newArrayList(buttonNewGame, buttonLoad, buttonExit);
			components.forEach(button -> button.setPreferredSize(new Dimension(300, 50)));
			components.forEach(button -> TestPanel.panel.add(button, gbc));
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
