package object;

import entity.Entity;
import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;

public class OBJ_Portion extends Entity {
    GamePanel gp;
    public OBJ_Portion(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name = "Portion";
        solidregion=new Rectangle(4,0,32,32);
        description = "[" + name + "]\nIt is a cure portion";
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/portion.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
