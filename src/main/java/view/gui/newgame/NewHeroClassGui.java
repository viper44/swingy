package view.gui.newgame;

import com.google.common.collect.Lists;
import view.SimpleView;
import view.gui.TestPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class NewHeroClassGui implements SimpleView {
	private String message;

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

		private ThreadStopper(CountDownLatch cdl2) {
			this.c2 = cdl2;
			this.run();
		}

		private void view() {
			JButton spellhowler = new JButton("SpellHowler");
			JButton treasure = new JButton("TreasureHunter");
			JButton knight = new JButton("DarkKnight");
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			spellhowler.addActionListener(new NewHeroClassGui.ThreadStopper.ButtonEventListener());
			treasure.addActionListener(new NewHeroClassGui.ThreadStopper.ButtonEventListener());
			knight.addActionListener(new NewHeroClassGui.ThreadStopper.ButtonEventListener());
			List<Component> components = Lists.newArrayList(spellhowler, treasure, knight);
			components.forEach(button -> button.setPreferredSize(new Dimension(300, 50)));
			components.forEach(button -> TestPanel.panel.add(button, gbc));
			TestPanel.panel.repaint();
			TestPanel.frame.revalidate();
		}

		class ButtonEventListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {

				message = ((JButton) e.getSource()).getText();
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
