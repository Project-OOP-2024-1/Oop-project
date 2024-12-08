package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class OBJ_Soul extends Projectile {
    GamePanel gp;
    public OBJ_Soul(GamePanel gp){
        super(gp);
        this.gp=gp;
        name="Slimeball";
        speed = 1;
        maxLife = 40;
        life = maxLife;
        damage = 1;
        alive = false;
        solidregion = new Rectangle(15,30,30,30);
        getImage();
        attack=true;
    }
    public void getImage() {
        SpriteSheet sheet = new SpriteSheet("/objects/Flame.png", 30, 30);

        rightSprites = new BufferedImage[3];
        leftSprites = new BufferedImage[3];
        upSprites = new BufferedImage[3];
        downSprites = new BufferedImage[3];

        for (int i = 0; i < 3; i++) {
            rightSprites[i] = sheet.getSpriteforFire(i, 0); // Extract the sprites
            leftSprites[i] = sheet.getSpriteforFire(i,0 );
            upSprites[i] = sheet.getSpriteforFire(i, 0);
            downSprites[i] = sheet.getSpriteforFire(i, 0);
        }
    }
    public void setAction(){
        if (Math.abs(gp.player.x-x) < Math.abs(gp.player.y-y)){
                if (Math.abs(gp.player.x-x)>48) {
                    System.out.println(1);
                    if (gp.player.x - x > 0) {
                        direction = "right";
                    } else if (gp.player.x - x < 0) {
                        direction = "left";
                    }
                }
                else{
                    System.out.println(2);
                    if (gp.player.y>y){
                        direction="down";
                    }
                    else {
                        direction="up";
                    }
                }
            }
        else{
                if(Math.abs(gp.player.y-y)>48) {
                    System.out.println(3);
                    if (gp.player.y > y) {
                        direction = "down";
                    } else {
                        direction = "up";
                    }
                }
                else {
                    System.out.println(4);
                    if (gp.player.x>x){
                        direction="right";
                    }
                    else {
                        direction="left";
                    }
                }
            }
    }

    public void update() {
        CounterNPC++;
        if (CounterNPC > 20) {
            setAction();
            CounterNPC = 0;
            life-=speed;
        }
        collisionOn=false;
        gp.colis.checkTile(this);
        gp.colis.checkPlayer(this);
        gp.colis.checkObject(this,gp.object);
        if (!collisionOn) {
            if (direction.equals("up")) y -= speed;
            if (direction.equals("down") || direction.equals(("idle"))) y += speed;
            if (direction.equals("left")) x -= speed;
            if (direction.equals("right")) x += speed;
        }
        else {
            life=0;
        }
        if (life <= 0) alive = false;
        Countersprite++;
        if (Countersprite > 25) {
            Numsprite++;
            if (Numsprite==3) Numsprite=1;
            Countersprite=0;
            if (speed<4) speed++;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = x - gp.player.x + gp.player.screenX;
        int screenY = y - gp.player.y + gp.player.screenY;
        if (x + gp.tileSize > gp.player.x - gp.player.screenX &&
                x - gp.tileSize < gp.player.x + gp.player.screenX &&
                y + gp.tileSize > gp.player.y - gp.player.screenY &&
                y - gp.tileSize < gp.player.y + gp.player.screenY) {
            switch (direction) {
                case "right":
                    image = rightSprites[Numsprite - 1];
                    break;
                case "left":
                    image = leftSprites[Numsprite - 1];
                    break;
                case "down", "idle":
                    image = downSprites[Numsprite - 1];
                    break;
                case "up":
                    image = upSprites[Numsprite - 1];
                    break;
            }
            g2.drawImage(image, screenX, screenY+60, 60, 60, null);
        }
    }
}
