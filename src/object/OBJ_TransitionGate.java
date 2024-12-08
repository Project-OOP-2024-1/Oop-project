package object;

import entity.Entity;
import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_TransitionGate extends Entity {
    GamePanel gp;
    BufferedImage inactive, active;
    public OBJ_TransitionGate(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Transition gate";
        description = "[" + name + "]\nThe gate lead to Boss";
        solidregion = new Rectangle(0, 0, 48, 96);
        //get image
        try {
            SpriteSheet sheet = new SpriteSheet("/objects/inactive gate.png", gp.originalTileSize, gp.originalTileSize*2);
            inactive = sheet.getSprite(0, 0);
            sheet=new SpriteSheet("/objects/active door.png", gp.originalTileSize, gp.originalTileSize*2);
            active= sheet.getSprite(0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
    public void update(){
        System.out.println("Successfully!");
        gp.player.x=24*gp.tileSize;
        gp.player.y=44*gp.tileSize;
    }
    public void draw(Graphics2D g2) {
        int screenX = x - gp.player.x + gp.player.screenX;
        int screenY = y - gp.player.y + gp.player.screenY;
        if (x + gp.tileSize > gp.player.x - gp.player.screenX &&
                x - gp.tileSize < gp.player.x + gp.player.screenX &&
                y + gp.tileSize > gp.player.y - gp.player.screenY &&
                y - gp.tileSize < gp.player.y + gp.player.screenY) {
            g2.drawImage(inactive, screenX, screenY, gp.tileSize, gp.tileSize*2, null);
        }
    }    //update Sprite in FPS;

}