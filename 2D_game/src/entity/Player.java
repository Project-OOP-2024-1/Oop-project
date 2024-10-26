package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

import sprite.SpriteSheet;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int frameCount = 4;//framecount : Tổng số khung hình của hoạt ảnh
    int currentFrame = 0;    // current Frame: khung hình hien tai trong hoạt ảnh

    int hold = 0;    //hold : thời gian lưu giữ cho một khung hình cụ thể
    int delay = 7; //delay : Khoảng thời gian giua các khung hình trong hoạt ảnh

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        getImage(); // Load the player's sprites

        setDefaultValue();
    }

    public void setDefaultValue() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "idle";
    }

    // Load sprite sheet and extract the player's walking animation sprites
    @Override
    public void getImage() {

//        SpriteSheet sheet = new SpriteSheet("/player/walk.png", gp.originalTileSize, gp.originalTileSize, 8, 4);
        SpriteSheet sheet = new SpriteSheet("/player/walk.png", gp.originalTileSize, gp.originalTileSize, 8, 4);
//        SpriteSheet sheet = new SpriteSheet("walk.png", gp.originalTileSize, gp.originalTileSize, 8, 4);

        rightSprites = new BufferedImage[frameCount];
        leftSprites = new BufferedImage[frameCount];
        downSprites = new BufferedImage[frameCount];
        upSprites = new BufferedImage[frameCount]; 
        idleSprites = new BufferedImage[frameCount];

        for (int i = 0; i < frameCount; i++) {
            rightSprites[i] = sheet.getSprite(i, 3); // Extract the sprites
            leftSprites[i] = sheet.getSprite(i, 2);
            downSprites[i] = sheet.getSprite(i, 0);
            upSprites[i] = sheet.getSprite(i, 1);
            idleSprites[i] = sheet.getSprite(i, 7);
            }
    }

    @Override
    public void update() {

        if (keyH.upPressed) {
            direction = "up";
            worldY -= speed;
        }
        else if (keyH.downPressed) {
            direction = "down";
            worldY += speed;
        }
        else if (keyH.rightPressed) {
            direction = "right";
            worldX += speed;
        }
        else if (keyH.leftPressed) {
            direction = "left";
            worldX -= speed;
        }
        else {
            direction = "idle";
        }
    }

    @Override
    // Draw frame
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        // Delay
        hold = (hold + 1) % delay;
        if (hold == 0) {
            currentFrame = (currentFrame + 1) % frameCount;
        }

        switch(direction) {
            case "right" :
                image = rightSprites[currentFrame];
                break;
            case "left" :
                image = leftSprites[currentFrame];
                break;
            case "up" :
                image = upSprites[currentFrame];
                break;
            case "down" :
                image = downSprites[currentFrame];
                break;
            case "idle" :
                image = idleSprites[currentFrame];
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
