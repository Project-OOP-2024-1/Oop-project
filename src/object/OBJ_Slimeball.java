package object;

import entity.Projectile;
import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Slimeball extends Projectile {
    GamePanel gp;
    public OBJ_Slimeball(GamePanel gp){
        super(gp);
        this.gp=gp;
        name="Slimeball";
        speed = 4;
        maxLife = 80;
        life = maxLife;
        damage = 1;
        alive = false;
        solidregion = new Rectangle(0,0,24,24);
        getImage();
    }
    public void getImage() {
        SpriteSheet sheet = new SpriteSheet("/objects/slime_ball.png", gp.originalTileSize, gp.originalTileSize);

        rightSprites = new BufferedImage[2];
        leftSprites = new BufferedImage[2];
        upSprites = new BufferedImage[2];
        downSprites = new BufferedImage[2];

        for (int i = 0; i < 2; i++) {
            rightSprites[i] = sheet.getSpriteforFire(0, 0); // Extract the sprites
            leftSprites[i] = sheet.getSpriteforFire(0, 0);
            upSprites[i] = sheet.getSpriteforFire(0, 0);
            downSprites[i] = sheet.getSpriteforFire(0, 0);
        }
    }
}
