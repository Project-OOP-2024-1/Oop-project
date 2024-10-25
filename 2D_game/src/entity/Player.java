package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

import sprite.SpriteSheet;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    int frameCount = 4;
    int currentFrame = 0;

    int hold = 0;
    int delay = 7;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        getImage(); // Load the player's sprites

        setDefaultValue();
    }

    public void setDefaultValue() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "idle";
    }

    // Load sprite sheet and extract the player's walking animation sprites
    @Override
    public void getImage() {

//        SpriteSheet sheet = new SpriteSheet("/player/walk.png", gp.originalTileSize, gp.originalTileSize, 8, 4);
        SpriteSheet sheet = new SpriteSheet("/resources/player/walk.png", gp.originalTileSize, gp.originalTileSize, 8, 4);
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
            case "right" : image = rightSprites[currentFrame];
            case "left" : image = leftSprites[currentFrame];
            case "down" : image = downSprites[currentFrame];
            case "up" : image = upSprites[currentFrame];
            case "idle" : image = idleSprites[currentFrame];
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
