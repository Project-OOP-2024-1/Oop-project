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

    public int[] getAllStatesAsInt() {
        // Convert the direction to an integer
        int directionAsInt;
        switch (direction.toLowerCase()) {
            case "up":
                directionAsInt = 1;
                break;
            case "down":
                directionAsInt = 2;
                break;
            case "left":
                directionAsInt = 3;
                break;
            case "right":
                directionAsInt = 4;
                break;
            default:
                directionAsInt = 0; // Undefined direction
        }

        // Collect all states as integers
        return new int[] {
                x,                   // Position X
                y,                   // Position Y
                speed,               // Speed
                directionAsInt,      // Direction as integer
                maxLife,             // Max life
                life,                // Current life
                attack,              // Attack power
                maxMana,             // Max mana
                mana,                // Current mana
                alive ? 1 : 0,       // Alive status (1 for true, 0 for false)
                actionLockCounter    // Action lock counter
        };
    }

}

