package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    //Sprite
    public BufferedImage image;
    public BufferedImage[] rightSprites,leftSprites, upSprites, downSprites, idleSprites ;
    public BufferedImage[] rightAttack, leftAttack, upAttack, downAttack;
    public Projectile projectile;
    public int Countersprite = 0;
    public int Numsprite = 1;
    public int Attacksprite = 0;
    public int NumAttack = 0;
    public int invincilbleCounter=0;

    //Control
    public int CounterNPC= 0;
    public Rectangle solidregion;
    public Rectangle Attackregion;
    public boolean collisionOn = false;//for monster and player , vv
    public boolean collision;
    public boolean invincible = false;
    public boolean attack;//attack check
    public boolean damaged;//check for monster damaged
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    //Entity status
    public int maxLife;
    public int x, y;
    public int speed;
    public int life;
    public String direction="down";
    //Item status
    public String name;
    public int damage;
    public boolean alive;
    public String description = "";
    //Set Action for NPC or Monster(Optimal)
    public void setAction(){};
    //get Information about image
    public void getImage(){};
    //create Sprite in every situation
    public void draw(Graphics2D g2){};
    //update Sprite in FPS;
    public void update(){};









}
