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

        SpriteSheet sheet = new SpriteSheet("/SLIME/NPC.png", gp.originalTileSize, gp.originalTileSize);

        idleSprites = new BufferedImage[frameCount];

        for (int i = 0; i < frameCount; i++) {
            idleSprites[i] = sheet.getSprite(i, 0);
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
        gp.colis.checkPlayer(this);
        Countersprite++;
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
    }
}