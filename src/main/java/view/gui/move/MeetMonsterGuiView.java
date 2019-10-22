package view.gui.move;

import com.google.common.collect.Lists;
import model.characters.monsters.Monster;
import view.ComplexView;
import view.gui.TestPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MeetMonsterGuiView implements ComplexView<Monster> {
	private String message;

	@Override
	public void render(Monster payload) {
		CountDownLatch c;
		c = new CountDownLatch(1);
		new MeetMonsterGuiView.ThreadStopper(c, payload);
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
		private JButton button = new JButton("Run");
		private JButton button2 = new JButton("Fight");
		private Monster monster;

		private ThreadStopper(CountDownLatch cdl2, Monster payload) {
			this.c2 = cdl2;
			this.monster = payload;
			this.run();
		}

		private void view() {
			TestPanel.panel.setLayout(new GridBagLayout());
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(600, 600));
			button.addActionListener(new MeetMonsterGuiView.ThreadStopper.ButtonEventListener());
			button2.addActionListener(new MeetMonsterGuiView.ThreadStopper.ButtonEventListener());
			JLabel meetMonster = new JLabel("You meet monster!");
			JLabel monsterLabel = new JLabel(monster.toString());
			JLabel runOrFight = new JLabel("You can run like pussy or fight!");
			List<Component> componentList = Lists.newArrayList(meetMonster, monsterLabel, runOrFight, button, button2);
			componentList.forEach(panel::add);
			TestPanel.panel.add(panel, new GridBagConstraints());
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
