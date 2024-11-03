package object;

import com.main.GamePanel;

import sprite.SpriteSheet;

public class OBJ_Key extends SuperObject {

    public OBJ_Key(GamePanel gp) {
        
        name = "Key";
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/key_test.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
