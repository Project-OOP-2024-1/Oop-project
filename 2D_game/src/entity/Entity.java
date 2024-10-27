package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    public String obj_name;
    public int x, y;

    public int speed;

    public BufferedImage[] rightSprites,leftSprites, upSprites, downSprites, idleSprites ;
    public String direction;
    public int frameCount;

    public int Countersprite = 0;
    public int Numsprite = 1;
    public Rectangle solidregion;
    public boolean collisionOn = false;


    //Character status
    public int maxLife;
    public int life;

    public abstract void update();

    public abstract void draw(Graphics2D g2);

    public abstract void getImage();
}

