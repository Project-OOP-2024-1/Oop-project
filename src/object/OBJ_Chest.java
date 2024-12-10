package object;

import main.GamePanel;

import entity.Entity;
import sprite.SpriteSheet;

import java.awt.*;


public class OBJ_Chest extends Entity {

    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Chest";
        description = "[" + name + "]\nNeed a key";
        solidregion = new Rectangle(8, 0, 32, 32);
        //get image
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/chest2_closed.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
        alive=true;
    }
}