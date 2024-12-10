package object;

import entity.Entity;
import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;

public class OBJ_Mushroom extends Entity {
    GamePanel gp;
    public OBJ_Mushroom(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name = "Mushroom";
        solidregion=new Rectangle(16,8,16,32);
        description = "[" + name + "]\nIt is a medical ingredient";
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/mushroom.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
        collision = true;
        alive=true;
    }

}
