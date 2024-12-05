package object;

import entity.Entity;
import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;

public class OBJ_SlimeHeart extends Entity {
    GamePanel gp;
    public OBJ_SlimeHeart(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Healing pool";
        description = "[" + name + "]\nThis is power stone";
        solidregion = new Rectangle(0, 0, 0, 0);
        //get image
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/slime heart.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
