package object;

import entity.Entity;
import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;

public class OBJ_Healing_pool extends Entity {
    GamePanel gp;
    public OBJ_Healing_pool(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Healing pool";
        description = "[" + name + "]\nCure your hurt";
        solidregion = new Rectangle(0, 0, 48, 48);
        //get image
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/healling pool.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
    public void update(){
        Countersprite++;
        if (Countersprite==20){
            if (gp.player.life<6) gp.player.life++;
            Countersprite=0;
        }
    }
}
