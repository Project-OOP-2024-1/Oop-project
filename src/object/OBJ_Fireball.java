package object;

import entity.Projectile;
import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;


public class OBJ_Fireball extends Projectile {
    GamePanel gp;

    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Fireball";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        damage = 2;
        useCost = 1;
        alive = false;
        solidregion = new Rectangle(0,0,24,24);
        getImage();
    }

    public void getImage() {
        SpriteSheet sheet = new SpriteSheet("/objects/fire_ball.png", gp.originalTileSize, gp.originalTileSize);

        rightSprites = new BufferedImage[2];
        leftSprites = new BufferedImage[2];
        upSprites = new BufferedImage[2];
        downSprites = new BufferedImage[2];

        for (int i = 0; i < 2; i++) {
            rightSprites[i] = sheet.getSpriteforFire(i, 0); // Extract the sprites
            leftSprites[i] = sheet.getSpriteforFire(i, 1);
            upSprites[i] = sheet.getSpriteforFire(i, 2);
            downSprites[i] = sheet.getSpriteforFire(i, 3);
        }
    }
}
