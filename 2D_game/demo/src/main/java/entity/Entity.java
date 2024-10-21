package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Entity {
    
    public int x, y;
    public int speed;

    public BufferedImage[] rightSprites, leftSprites, downSprites , upSprites, idleSprites;
    public String direction;
    public int frameCount, currentFrame, hold, delay;

    public abstract void update();

    public abstract void draw(Graphics2D g2);

    public abstract void getImage();
}

