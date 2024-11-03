package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
    
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage[] rightSprites, leftSprites, downSprites , upSprites, idleSprites;
    public String direction;
    public int frameCount, currentFrame, hold, delay;
    public int Countersprite = 0;
    public int Numsprite = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;


    public abstract void update();

    public abstract void draw(Graphics2D g2);

    public abstract void getImage();
}

