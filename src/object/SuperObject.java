package object;

import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject {
    public BufferedImage image ;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public abstract void update(Player player);
    public abstract void draw(Graphics2D g2);
}
