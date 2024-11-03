package object;

import com.main.GamePanel;

import sprite.SpriteSheet;


public class OBJ_Chest extends SuperObject{


    public OBJ_Chest(GamePanel gp) {
        
        name = "Chest";
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/chest2_closed.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
