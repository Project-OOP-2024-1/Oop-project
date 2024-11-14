package entity;

import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_1 extends Entity {
    int frameCount=4;
    public NPC_1(GamePanel gp) {
        super(gp);
        name="village";
        direction = "idle";
        speed = 1;
        getImage();
        solidregion = new Rectangle(8,16,32,32);


    }

    public void getImage() {

        SpriteSheet sheet = new SpriteSheet("/SLIME/shit_w_trans.png", gp.originalTileSize, gp.originalTileSize);

        rightSprites = new BufferedImage[frameCount];
        leftSprites = new BufferedImage[frameCount];
        upSprites = new BufferedImage[frameCount];
        downSprites = new BufferedImage[frameCount];
        idleSprites = new BufferedImage[frameCount];

        for (int i = 0; i < frameCount; i++) {
            rightSprites[i] = sheet.getSprite(i, 2); // Extract the sprites
            leftSprites[i] = sheet.getSprite(i, 3);
            upSprites[i] = sheet.getSprite(i, 1);
            downSprites[i] = sheet.getSprite(i, 0);
            idleSprites[i] = sheet.getSprite(i, 4);

        }

    }
    public void setAction(){
        // Set orbit or same thing here;
        Random rand =new Random();
        int num =rand.nextInt(5);
        switch (num){
            case 0: direction="up"; break;
            case 1: direction="down";break;
            case 2: direction="left"; break;
            case 3: direction="right"; break;
            case 4: direction="idle"; break;
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
                case "down":
                    image = downSprites[Numsprite - 1];
                    break;
                case "up":
                    image = upSprites[Numsprite - 1];
                    break;
                case "idle":
                    image = idleSprites[Numsprite - 1];
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    public void update() {
        Countersprite++;
        CounterNPC++;
        if (CounterNPC > 60) {
            setAction();
            CounterNPC = 0;
        }
        if (Countersprite > 15) {
            if (Numsprite == 1) {
                Numsprite = 2;
            } else if (Numsprite == 2) {
                Numsprite = 3;
            } else if (Numsprite == 3) {
                Numsprite = 4;
            } else if (Numsprite == 4) {
                Numsprite = 1;
            }
            Countersprite = 0;
        }
        collisionOn = false;
        gp.colis.checkTile(this);
        gp.colis.checkPlayer(this);
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "right":
                    x += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
            }
        }
    }
}