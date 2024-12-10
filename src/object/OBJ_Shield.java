package object;

import entity.Entity;
import main.GamePanel;
import sprite.SpriteSheet;

public class OBJ_Shield extends Entity {
    public OBJ_Shield(GamePanel gp){
        super(gp);
        name="Dragon Shield";
        description = "[" + name + "]\nAgainst damage\nfrom monster";
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/shield.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
        defenseValue=1;
    }
}
