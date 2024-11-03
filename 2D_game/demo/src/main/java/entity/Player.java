package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.GamePanel;
import com.main.KeyHandler;

import sprite.SpriteSheet;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;

    int frameCount = 4;
    int currentFrame = 0;
    boolean hasKey = false;

    int hold = 0;
    int delay = 7;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);


        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage(); // Load the player's sprites

        setDefaultValue();
    }

    public void setDefaultValue() {

        worldX = gp.tileSize * 21;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "idle";
    }

    // Load sprite sheet and extract the player's walking animation sprites
    @Override
    public void getImage() {

        SpriteSheet sheet = new SpriteSheet("/player/walk.png", gp.originalTileSize, gp.originalTileSize);
        
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

        // Check direction and move
        if (keyH.upPressed == false & keyH.downPressed == false & keyH.rightPressed == false & keyH.leftPressed == false) {
            direction = "idle";
        }        
        else if (keyH.upPressed == true) {
            direction = "up";
        }
        else if (keyH.downPressed == true) {
            direction = "down";
        }
        else if (keyH.rightPressed == true) {
            direction = "right";
        }
        else if (keyH.leftPressed == true) {
            direction = "left";
        }

        // Check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // Check obj collision
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "right" -> worldX += speed;
                case "left" -> worldX -= speed;
            }
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
            case "right" -> image = rightSprites[currentFrame];
            case "left" -> image = leftSprites[currentFrame];
            case "down" -> image = downSprites[currentFrame];
            case "up" -> image = upSprites[currentFrame];
            case "idle" -> image = idleSprites[currentFrame];
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void pickUpObject(int i) {
        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case ("Key") -> {
                    hasKey = true;
                    gp.obj[i] = null;
                }
                case ("Chest") -> {
                    if (hasKey == true) {
                        gp.obj[i] = null;
                    }
                }
            }
        }

    }
}
