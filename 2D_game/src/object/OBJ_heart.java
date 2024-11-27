package object;

import entity.Entity;
import entity.Player;
import main.GamePanel;
import utility.ImageManipulate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_heart extends Entity {
    GamePanel gp;
    public BufferedImage[] temp_image;
    public OBJ_heart(GamePanel gp){
        super(gp);
        temp_image = new BufferedImage[3];
        this.gp = gp;
        name = "Heart";
        try{
            temp_image[0] = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            temp_image[0] = ImageManipulate.scaleImage(temp_image[0],3);
            temp_image[1] = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            temp_image[1] = ImageManipulate.scaleImage(temp_image[1],3);
            temp_image[2] = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            temp_image[2] = ImageManipulate.scaleImage(temp_image[2],3);        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(Player player){
        int [][] status = {
                {0,0,0},
                {1,0,0},
                {2,0,0},
                {2,1,0},
                {2,2,0},
                {2,2,1},
                {2,2,2}
        };
        int life = player.life;
        BufferedImage[] tmp = {temp_image[status[life][0]],temp_image[status[life][1]],temp_image[status[life][2]]};
        image = ImageManipulate.concatenateImage(tmp,0);
    }
    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, 0, 0,image.getWidth() , image.getHeight(), null);
    }
}