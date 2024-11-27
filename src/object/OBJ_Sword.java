package object;

import entity.Entity;
import main.GamePanel;
import sprite.SpriteSheet;

public class OBJ_Sword extends Entity {
    public OBJ_Sword(GamePanel gp){
        super(gp);
        name= "Legend Sword";
        description = "[" + name + "]\nAttack monster";
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/sword.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
        damage=1;
    }
}
