package object;

import entity.Projectile;
import main.GamePanel;
import sprite.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import utility.ImageManipulate;

public class OBJ_Fireball extends Projectile {
    static int numbers_of_fireball=0;
    GamePanel gp;

    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;
        obj_name = "Fireball";
        speed = 4;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        getImage();
    }

    public void getImage(){
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/projectile.png"));

            // Load the sprite sheet image
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = ImageManipulate.makeBlackPixelsTransparent(image.getSubimage(16*4+8, 16*2+8, 32, 32));
//        image = image.getSubimage(16*4+8, 16*5, 32, 32);
//        image = image.getSubimage(16*4-8, 16*10-2, 64, 32);


//        SpriteSheet sheet = new SpriteSheet(,gp.originalTileSize,gp.originalTileSize,-1,-1);
//        image = sheet.getSprite(5,3);
        SpriteSheet.saveImageToFile(image,"test.png");
    }
}
