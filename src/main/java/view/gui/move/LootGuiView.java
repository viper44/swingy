package view.gui.move;

import com.google.common.collect.Lists;
import model.GameContext;
import model.equipment.Equipment;
import view.LootComplexView;
import view.gui.TestPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class LootGuiView implements LootComplexView<Equipment> {
	private String message;

	@Override
	public void render(Equipment payload, GameContext context) {
		CountDownLatch c;
		c = new CountDownLatch(1);
		Map<Class<?>, Equipment> tmp = new HashMap<>();
		tmp.put(context.getHero().getArmor().getClass(), context.getHero().getArmor());
		tmp.put(context.getHero().getHelmet().getClass(), context.getHero().getHelmet());
		tmp.put(context.getHero().getWeapon().getClass(), context.getHero().getWeapon());
		new LootGuiView.ThreadStopper(c, payload, tmp.get(payload.getClass()));
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
		private Equipment payload;
		private Equipment current;
		private JButton button = new JButton("Yes");
		private JButton button2 = new JButton("No");

		private ThreadStopper(CountDownLatch cdl2, Equipment payLoad, Equipment current) {
			this.c2 = cdl2;
			this.payload = payLoad;
			this.current = current;
			this.run();

		}

		private void view() {
			TestPanel.panel.setLayout(new FlowLayout());
			List<JPanel> panels = Lists.newArrayList(new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel());
			panels.forEach(panel -> panel.setPreferredSize(new Dimension(800, 30)));
			button.addActionListener(new LootGuiView.ThreadStopper.ButtonEventListener());
			button2.addActionListener(new LootGuiView.ThreadStopper.ButtonEventListener());
			JLabel foundLoot = new JLabel("You found new equipment!\n" +
					"Let's look what we got...");
			JLabel equip = new JLabel(payload.toString(), SwingConstants.CENTER);
			JLabel currentEq = new JLabel("Your current equipment :" + current.toString());
			JLabel take = new JLabel("Do you want to take?");
			List<JComponent> components = Lists.newArrayList(foundLoot, equip, currentEq, take, button, button2);
			Iterator<JComponent> comp = components.iterator();
			panels.forEach(panel -> panel.add(comp.next()));
			panels.get(panels.size() - 1).add(button2);
			panels.forEach(p -> TestPanel.panel.add(p, new GridBagConstraints()));
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
