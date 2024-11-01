package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    public GamePanel gp;
    public String obj_name;
    public int x, y;

    public int speed;

    public BufferedImage[] rightSprites,leftSprites, upSprites, downSprites, idleSprites;
    public BufferedImage image;
    public String direction;
    public int frameCount;

    public int Countersprite = 0;
    public int Numsprite = 1;
    public Rectangle solidregion;
    public boolean collisionOn = false;


    //Character status
    public int maxLife;
    public int life;
    public int attack;
    public int maxMana;
    public int mana;
    public boolean alive;
    public Projectile projectile;
    public int actionLockCounter;



    public Entity(GamePanel gp) {
    }

    public abstract void update();

    public abstract void draw(Graphics2D g2);

    public abstract void getImage();
}

