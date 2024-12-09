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
        solidregion = new Rectangle(0, 0, 48*2, 96*2);
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
        alive=false;
    }
    public void update(){
        Countersprite++;
        if (gp.player.has("Fragment")){
            alive=true;
        }
        if(alive){
            gp.player.x=24*gp.tileSize;
            gp.player.y=44*gp.tileSize;
            gp.ui.addMessage("Transition successfully!");
        }
        else {
            if (Countersprite>90){
                gp.ui.addMessage("You need space fragment!");
                Countersprite=0;
            }
        }

    }
    public void draw(Graphics2D g2) {
        int screenX = x - gp.player.x + gp.player.screenX;
        int screenY = y - gp.player.y + gp.player.screenY;
        if (x + gp.tileSize*2 > gp.player.x - gp.player.screenX &&
                x - gp.tileSize*2 < gp.player.x + gp.player.screenX &&
                y + gp.tileSize*4 > gp.player.y - gp.player.screenY &&
                y - gp.tileSize*4 < gp.player.y + gp.player.screenY) {
            if (alive){
                g2.drawImage(active, screenX, screenY, gp.tileSize*2, gp.tileSize*4, null);
            }
            else {
                g2.drawImage(inactive, screenX, screenY, gp.tileSize*2, gp.tileSize*4, null);
            }
        }
    }    //update Sprite in FPS;
}
