package model;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import model.characters.hero.Hero;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Getter
@Accessors(chain = true)
public class GameContext {
    Hero hero;
    ImageIcon icon;

    public void setHero(Hero hero){
        this.hero = hero;
        setIcon(this.hero);
    }

    public void setIcon(Hero hero){
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(hero.getPicPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(192, 192,
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        this.icon = imageIcon;
    }
}
