package object;

import entity.Entity;
import main.GamePanel;

import sprite.SpriteSheet;

import java.awt.*;

public class OBJ_Key extends Entity {
    GamePanel gp;
    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name = "Key";
        solidregion=new Rectangle(4,0,32,32);
        description = "[" + name + "]\nIt opens a chest";
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/key_test.png", gp.originalTileSize, gp.originalTileSize);
            image = sheet.getSprite(0, 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}