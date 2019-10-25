package view.gui.move;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.characters.hero.Hero;
import view.MoveViewComplex;
import view.gui.TestPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class MoveGuiView implements MoveViewComplex {
	private String message;

	@Override
	public void drawMap(Integer size, Integer x, Integer y, Hero hero) {
		CountDownLatch c;
		c = new CountDownLatch(1);
		new MoveGuiView.ThreadStopper(c, size, x, y, hero);
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

	@FieldDefaults(level = AccessLevel.PRIVATE)
	class ThreadStopper implements Runnable {
		CountDownLatch c2;
		Integer size;
		Integer heroXPos;
		Integer heroYPos;
		Hero hero;


		private ThreadStopper(CountDownLatch cdl2, Integer size, Integer x, Integer y, Hero hero) {
			this.c2 = cdl2;
			this.size = size;
			this.heroXPos = x;
			this.heroYPos = y;
			this.hero = hero;
			this.run();
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

		private void view() {
			JButton north = new JButton("North");
			JButton south = new JButton("South");
			JButton west = new JButton("West");
			JButton east = new JButton("East");
			JButton menu = new JButton("Menu");

			TestPanel.panel.setLayout(new FlowLayout());
			Dimension dimension = TestPanel.panel.getSize();
			int mapWidth = dimension.width * 7 / 10;
			int mapHeight = dimension.height * 8 / 9;
			BufferedImage myPicture = null;
			try {
				myPicture = ImageIO.read(new File("D:\\Max\\Java\\java_projects\\swingy\\src\\main\\test.jpeg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			int heroWidth = dimension.width / 4;
			int heroHeight = dimension.height * 8 / 10;
			JPanel heroPanel = new JPanel();
			heroPanel.add(picLabel);
			heroPanel.add(getTwoColumnLayout(hero));
			heroPanel.setPreferredSize(new Dimension(heroWidth, heroHeight));
			JPanel way = new JPanel();
			Border blackLine = BorderFactory.createLineBorder(Color.black);
			heroPanel.setBorder(blackLine);


			north.addActionListener(new ButtonEventListener());
			south.addActionListener(new ButtonEventListener());
			east.addActionListener(new ButtonEventListener());
			west.addActionListener(new ButtonEventListener());
			menu.addActionListener(new ButtonEventListener());

			way.add(north);
			way.add(south);
			way.add(west);
			way.add(east);
			way.add(east);
			way.add(menu);

			way.setPreferredSize(new Dimension(dimension.width * 6 / 10, dimension.height * 2 / 10));
			TestPanel.panel.add(heroPanel);
			TestPanel.panel.add(new DrawMap(size, heroXPos, heroYPos, mapWidth, mapHeight));
			TestPanel.panel.add(way);
			TestPanel.panel.repaint();
			TestPanel.frame.revalidate();
		}

		@FieldDefaults(level = AccessLevel.PRIVATE)
		class DrawMap extends JPanel {
			Integer size;
			Integer heroXPos;
			Integer heroYPos;
			Integer dimX;
			Integer dimY;

			private DrawMap(Integer size, Integer heroXPos, Integer heroYPos, Integer dimX, Integer dimY) {
				this.heroXPos = heroXPos;
				this.heroYPos = heroYPos;
				this.size = size;
				this.dimX = dimX;
				this.dimY = dimY;
			}

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(dimX, dimY);
			}

			protected void paintComponent(Graphics g) {
				BufferedImage myPicture = null;
				try {
					myPicture = ImageIO.read(new File("D:\\Max\\Java\\java_projects\\swingy\\src\\main\\test.jpeg"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				Integer sizeCell = Math.min(getWidth() - 4, getHeight() - 4) / size;
				int y = (getHeight() - (sizeCell * size)) / 2;
				for (int horz = 0; horz < size; horz++) {
					int x = (getWidth() - (sizeCell * size)) / 2;
					for (int vert = 0; vert < size; vert++) {
						if (horz + 1 == heroYPos && vert + 1 == heroXPos) {
							g.drawImage(myPicture, x, y, sizeCell, sizeCell, null);
						} else {
							g.drawRect(x, y, sizeCell, sizeCell);
						}
						x += sizeCell;
					}
					y += sizeCell;
				}
				g2d.dispose();

			}
		}

		private JComponent getTwoColumnLayout(JLabel[] labels, JLabel[] fields) {
			JComponent panel = new JPanel();
			GroupLayout layout = new GroupLayout(panel);
			panel.setLayout(layout);
			layout.setAutoCreateGaps(true);
			GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
			GroupLayout.Group yLabelGroup = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
			hGroup.addGroup(yLabelGroup);
			GroupLayout.Group yFieldGroup = layout.createParallelGroup();
			hGroup.addGroup(yFieldGroup);
			layout.setHorizontalGroup(hGroup);
			GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
			layout.setVerticalGroup(vGroup);

			int p = GroupLayout.PREFERRED_SIZE;
			for (JLabel label : labels) {
				yLabelGroup.addComponent(label);
			}
			for (Component field : fields) {
				yFieldGroup.addComponent(field);
			}
			for (int ii = 0; ii < labels.length; ii++) {
				vGroup.addGroup(layout.createParallelGroup().
						addComponent(labels[ii]).
						addComponent(fields[ii]));
			}
			return panel;
		}

		private JComponent getTwoColumnLayout(Hero hero) {
			String[] labelStrings = {hero.getNameH(), hero.getHeroType().toString(), hero.getLevel().toString(), hero.getExp().toString(), hero.getHpCur().toString() + "/" + hero.getHpMax(),
					hero.getHeroDefense().toString(), hero.getHeroDamage().toString(), hero.getHelmet().toString(), hero.getArmor().toString(), hero.getWeapon().toString()};
			String[] fieldStrings = {"   Name   ", "    Class   ", "   Level    ", "    Exp     ", "     HP      ", " Defense ", " Damage ", " Helmet ", "  Armor  ", "  Weapon "};

			Border blackLine = BorderFactory.createLineBorder(Color.black);
			JLabel[] labels = new JLabel[labelStrings.length];
			JLabel[] fields = new JLabel[fieldStrings.length];
			for (int ii = 0; ii < labels.length; ii++) {
				labels[ii] = new JLabel(labelStrings[ii]);
				Border margin = new EmptyBorder(10,10,10,10);
				labels[ii].setBorder(new CompoundBorder(blackLine, margin));

			}
			for (int ii = 0; ii < fieldStrings.length; ii++) {
				fields[ii] = new JLabel(fieldStrings[ii]);
				Border margin = new EmptyBorder(10,10,10,10);
				fields[ii].setBorder(new CompoundBorder(blackLine, margin));
			}
			return getTwoColumnLayout(fields, labels);
		}
	}

}
