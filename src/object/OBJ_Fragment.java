package object;

import entity.Entity;
import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;

public class OBJ_Fragment extends Entity {
    GamePanel gp;
    public OBJ_Fragment(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Fragment";
        description = "[" + name + "]\nThis is Gate key";
        solidregion = new Rectangle(0, 0, 0, 0);
        //get image
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/fragment.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = false;
    }

}
