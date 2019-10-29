package view.gui.endgame;

import view.SimpleView;
import view.gui.TestPanel;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class WinGameGuiView implements SimpleView {
	@Override
	public void render() {
		CountDownLatch c;
		c = new CountDownLatch(1);
		new WinGameGuiView.ThreadStopper(c);
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
			BufferedImage img = null;
			try {
				img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("win.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image dimg = img.getScaledInstance(878, 580,
					Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			TestPanel.panel.setLayout(new FlowLayout());
			JPanel panel = new JPanel();
			JPanel panel2 = new JPanel();
			JPanel win = new JPanel();
			JLabel pic = new JLabel(imageIcon);
			win.setPreferredSize(new Dimension(900, 600));
			panel.setPreferredSize(new Dimension(800, 20));
			panel2.setPreferredSize(new Dimension(800, 20));
			JLabel label = new JLabel("You won!");
			JLabel label2 = new JLabel("My congratulations");
			panel.add(label);
			panel2.add(label2);
			win.add(pic);
			TestPanel.panel.add(panel, new GridBagConstraints());
			TestPanel.panel.add(panel2, new GridBagConstraints());
			TestPanel.panel.add(win, new GridBagConstraints());
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
