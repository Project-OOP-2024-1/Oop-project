package object;

import entity.Entity;
import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;

public class OBJ_Stone extends Entity {
    GamePanel gp;
    public OBJ_Stone(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Stone";
        description = "[" + name + "]\nThis is special \ningredient";
        solidregion = new Rectangle(0, 0, 0, 0);
        //get image
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/stone.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
