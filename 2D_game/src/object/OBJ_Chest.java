package object;

import main.GamePanel;

import entity.Entity;
import main.UtilityTool;
import sprite.SpriteSheet;

import java.awt.*;


public class OBJ_Chest extends Entity {

    GamePanel gp;
    public OBJ_Chest(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name = "Chest";
        solidregion= new Rectangle(4,0,32,32);
        description = "[" + name + "]\nMake a key";
        //get image
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/chest2_closed.png", gp.originalTileSize, gp.originalTileSize,1,1);
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