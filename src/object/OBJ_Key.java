package object;

import entity.Entity;
import main.GamePanel;

import sprite.SpriteSheet;

import java.awt.*;

public class OBJ_Key extends Entity {
    GamePanel gp;
    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name = "Key";
        solidregion=new Rectangle(4,0,32,32);
        description = "[" + name + "]\nIt opens a chest";
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/key_test.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
    public void draw(Graphics2D g2) {
        int screenX = x - gp.player.x + gp.player.screenX;
        int screenY = y - gp.player.y + gp.player.screenY;
        if (x + gp.tileSize > gp.player.x - gp.player.screenX &&
                x - gp.tileSize < gp.player.x + gp.player.screenX &&
                y + gp.tileSize > gp.player.y - gp.player.screenY &&
                y - gp.tileSize < gp.player.y + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}