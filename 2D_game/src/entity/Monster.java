package entity;

import main.GamePanel;
import main.KeyHandler;
import sprite.SpriteSheet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Monster extends Entity{
    GamePanel gp;
    public KeyHandler keyMonster = new KeyHandler(KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT);

    int frameCount =4;

    public Monster(GamePanel gp, KeyHandler keyMonster){
        this.gp = gp;
        this.keyMonster = keyMonster;
        System.out.println("Monster Key: "+this.keyMonster.key_up+" "+this.keyMonster.key_down+" "+this.keyMonster.key_left+" "+this.keyMonster.key_right);

        getImage(); //load monster's sprite

        setDefaultValue();

        solidregion = new Rectangle(8,16,32,32);
    }
    public void setDefaultValue() {

        x = 200;
        y = 100;
        speed = 4;
        direction = "idle";
        //player status
        maxLife = 10;
        life = 7;
    }

    // Load sprite sheet and extract the player's walking animation sprites
    @Override
    public void getImage() {

        SpriteSheet sheet = new SpriteSheet("/Monster/SLIME/silme_animation_w_trans.png", gp.originalTileSize, gp.originalTileSize, 8, 4);

        rightSprites = new BufferedImage[frameCount];
        leftSprites = new BufferedImage[frameCount];
        upSprites = new BufferedImage[frameCount];
        downSprites= new BufferedImage[frameCount];
        idleSprites= new BufferedImage[frameCount];

        for (int i = 0; i < frameCount; i++) {
            rightSprites[i] = sheet.getSprite(i,2 ); // Extract the sprites
            leftSprites[i] = sheet.getSprite(i, 3);
            upSprites[i] = sheet.getSprite(i, 1);
            downSprites[i] = sheet.getSprite(i,0 );
            idleSprites[i] = sheet.getSprite(i, 4);
        }
    }

    @Override
    public void update() {

        if (keyMonster.upPressed) {
            direction = "up";
            y -= speed;
        }
        else if (keyMonster.downPressed) {
            direction = "down";
            y += speed;
        }
        else if (keyMonster.rightPressed) {
            direction = "right";
            x += speed;
        }
        else if (keyMonster.leftPressed) {
            direction = "left";
            x -= speed;
        }
        else {
            if (keyMonster.lifeDecPressed){
                if(this.life>0)this.life-=1;
            }
            if(keyMonster.lifeIncPressed){
                if(this.life<6)this.life+=1;
            }
            direction = "idle";
        }

        collisionOn=false;
        gp.colis.checkTile(this);
    }


    @Override
    // Draw frame
    public void draw(Graphics2D g2) {
        System.out.println(direction);
        BufferedImage image = null;

        switch(direction) {
            case "right" :
                image = rightSprites[Numsprite-1];
                System.out.println("Monsterright");
                break;
            case "left" :
                image = leftSprites[Numsprite-1];
                System.out.println("Monsterleft");
                break;
            case "down" :
                image = downSprites[Numsprite-1];
                System.out.println("Monsterdown");
                break;
            case "up" :
                image = upSprites[Numsprite-1];
                System.out.println("Monsterup");
                break;
            case "idle" :
                image = idleSprites[Numsprite-1];
                System.out.println("Monsteridle");
                break;

        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
