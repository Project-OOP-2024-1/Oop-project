package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.Collision_checker;
import main.GamePanel;
import main.KeyHandler;

import sprite.SpriteSheet;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    int frameCount = 3;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        getImage(); // Load the player's sprites

        setDefaultValue();

        solidregion = new Rectangle(8,16,32,32);

    }

    public void setDefaultValue() {

        x = 200;
        y = 100;
        speed = 4;
        direction = "idle";
    }

    // Load sprite sheet and extract the player's walking animation sprites
    @Override
    public void getImage() {

//        SpriteSheet sheet = new SpriteSheet("/player/walk.png", gp.originalTileSize, gp.originalTileSize, 8, 4);
        SpriteSheet sheet = new SpriteSheet("/SLIME/silme_animation_w_trans.png", gp.originalTileSize, gp.originalTileSize, 8, 4);
//        SpriteSheet sheet = new SpriteSheet("walk.png", gp.originalTileSize, gp.originalTileSize, 8, 4);

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

        if (keyH.upPressed) {
            direction = "up";
            y -= speed;
        }
        else if (keyH.downPressed) {
            direction = "down";
            y += speed;
        }
        else if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        }
        else if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
        }
        else {
            direction = "idle";
        }
        collisionOn=false;
        gp.colis.checkTile(this);
        Countersprite++;
        if (Countersprite > 20){
            if (Numsprite == 1){
                Numsprite =2;
            }
            else if (Numsprite==2){
                Numsprite=3;
            } else if (Numsprite==3) {
                Numsprite=1;
            } else if (Numsprite==4) {
                Numsprite=1;
            }
            Countersprite=0;
        }
    }


    @Override
    // Draw frame
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "right" :
                image = rightSprites[Numsprite-1];
                break;
            case "left" :
                image = leftSprites[Numsprite-1];
                break;
            case "down" :
                image = downSprites[Numsprite-1];
                break;
            case "up" :
                image = upSprites[Numsprite-1];
                break;
            case "idle" :
                image = idleSprites[Numsprite-1];
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
