package view.gui;

import com.google.common.collect.Lists;
import model.characters.hero.Hero;
import view.ComplexView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoadGuiView implements ComplexView<List<Hero>> {
	String message;
	@Override
	public void render(List<Hero> payload) {
		CountDownLatch c;
		c = new CountDownLatch(1);
		new LoadGuiView.ThreadStopper(c, payload);
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
		private List<Hero> payload;

		private ThreadStopper(CountDownLatch cdl2, List<Hero> payload) {
			this.c2 = cdl2;
			this.payload = payload;
			this.run();
		}

		private void view() {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			List<JButton> buttons = Stream.generate(JButton::new).limit(payload.size()).collect(Collectors.toList());
			List<JPanel> panels = Stream.generate(JPanel::new).limit(payload.size()).collect(Collectors.toList());
			List<JLabel> labels = Stream.generate(JLabel::new).limit(payload.size()).collect(Collectors.toList());
			buttons.forEach(button -> {
				button.setPreferredSize(new Dimension(200, 30));
				button.addActionListener(new ButtonEventListener());
			});
			Iterator<Hero> heroes = payload.iterator();
			buttons.forEach(button -> button.setText(String.valueOf(heroes.next().getId())));
			Iterator<Hero> heroes2 = payload.iterator();
			labels.forEach(label -> label.setText(heroes2.next().toString()));
			panels.forEach(panel -> panel.setPreferredSize(new Dimension(1400, 60)));
			Iterator<JLabel> labelIterator = labels.iterator();
			Iterator<JButton> buttonIterator = buttons.iterator();
			panels.forEach(panel -> {
				panel.add(labelIterator.next());
				panel.add(buttonIterator.next());
			});
			panels.forEach(panel -> TestPanel.panel.add(panel, gbc));
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
