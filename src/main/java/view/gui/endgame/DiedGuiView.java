package view.gui.endgame;

import view.SimpleView;
import view.gui.TestPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class DiedGuiView implements SimpleView {
	@Override
	public void render() {
		CountDownLatch c;
		c = new CountDownLatch(1);
		new DiedGuiView.ThreadStopper(c);
		try {
			c.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String readUserInput() {
		return null;
	}

	class ThreadStopper implements Runnable {
		private CountDownLatch c2;


		private ThreadStopper(CountDownLatch cdl2) {
			this.c2 = cdl2;
			this.run();
		}

		private void view() {
			JPanel die = new JPanel();
			die.setPreferredSize(new Dimension(TestPanel.frame.getWidth(), TestPanel.frame.getHeight()));
			BufferedImage img = null;
			try {
				img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("died.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image dimg = img.getScaledInstance(TestPanel.frame.getWidth(), TestPanel.frame.getHeight(),
					Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			JLabel pic = new JLabel(imageIcon);
			die.add(pic);
			TestPanel.panel.add(die, new GridBagConstraints());
			TestPanel.panel.repaint();
			TestPanel.frame.revalidate();
			c2.countDown();
		}


		@Override
		public void run() {
			view();
		}
	}
}
