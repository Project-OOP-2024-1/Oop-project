package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    //Sprite
    public BufferedImage image;
    public BufferedImage[] rightSprites,leftSprites, upSprites, downSprites, idleSprites, deathSprites;
    public BufferedImage[] rightAttack, leftAttack, upAttack, downAttack;
    public Projectile projectile;
    public int Countersprite = 0;
    public int Numsprite = 1;
    public int Attacksprite = 0;
    public int NumAttack = 0;
    public int Counterdeath= 0;
    public int NumDeath = 0;
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
    public boolean death;//death animation
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    //Entity status
    public int maxLife;
    public int x, y;
    public int speed;
    public int life;
    public String description;
    public String direction="down";
    //Item status
    public String name;
    public int damage;
    public boolean alive;
    public int defenseValue;
    //Set Action for NPC or Monster(Optimal)
    public void setAction(){}
    //get Information about image
    public void getImage(){}
    //create Sprite in every situation
    public void draw(Graphics2D g2) {
        int screenX = x - gp.player.x + gp.player.screenX;
        int screenY = y - gp.player.y + gp.player.screenY;
        if (x + gp.tileSize > gp.player.x - gp.player.screenX &&
                x - gp.tileSize < gp.player.x + gp.player.screenX &&
                y + gp.tileSize > gp.player.y - gp.player.screenY &&
                y - gp.tileSize < gp.player.y + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }    //update Sprite in FPS;
    public void update(){}
    //death_sprite
    public void update_death(){
        Counterdeath++;
        if (Counterdeath>20){
            if (NumDeath>2){
                death=true;
                NumDeath=0;
            }
            NumDeath++;
            Counterdeath=0;
        }
    }
    //
    public void draw_death(Graphics2D g2){}









}

