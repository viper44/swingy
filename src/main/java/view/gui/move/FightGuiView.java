package view.gui.move;

import view.ComplexView;
import view.gui.TestPanel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FightGuiView implements ComplexView<String> {
	@Override
	public void render(String payload) {
		CountDownLatch c;
		c = new CountDownLatch(1);
		new FightGuiView.ThreadStopper(c, payload);
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
		private String payload;


		private ThreadStopper(CountDownLatch cdl2, String payLoad) {
			this.c2 = cdl2;
			this.payload = payLoad;
			this.run();
		}

		private void view() {
			TestPanel.panel.setLayout(new FlowLayout());
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(800, 20));
			JLabel meetMonster = new JLabel(payload);
			panel.add(meetMonster);
			TestPanel.panel.add(panel, new GridBagConstraints());
			TestPanel.panel.repaint();
			TestPanel.frame.revalidate();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c2.countDown();
		}


		@Override
		public void run() {
			view();
		}
	}
}
