package entity;

import main.GamePanel;
import utility.ImageManipulate;

import java.awt.*;

public class Projectile extends Entity{
    static int totalNumber = 0;
    public int useCost;
    Entity user;
    public String direction = "right";
    public Projectile(GamePanel gp){
        super(gp);
        this.gp = gp;
        obj_name = "Projectile"+totalNumber;
        totalNumber+=1;

    }
    public void set(int worldX, int worldY, String direction,boolean alive, Entity user){
        this.x = worldX;
        this.y = worldY;
        // align projectile with user when in different directions
        y-=gp.tileSize/2;
        if (direction.equals("up")){
            x-= gp.tileSize/2;
        }
        if (direction.equals("down")){
            x-= gp.tileSize/2;
        }
        if (direction.equals("left")){
            x-= gp.tileSize;
        }
        if (this.direction.equals("up")) image = ImageManipulate.rotateImage(image,-270);
        if (this.direction.equals("left")) image = ImageManipulate.rotateImage(image,-180);
        if (this.direction.equals("down")) image = ImageManipulate.rotateImage(image,-90);
        this.direction = direction;
        if (direction.equals("up")) image = ImageManipulate.rotateImage(image,270);
        if (direction.equals("left")) image = ImageManipulate.rotateImage(image,180);
        if (direction.equals("down")) image = ImageManipulate.rotateImage(image,90);

        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }



    @Override
    public void update(){
        System.out.println("projectile: x:y: "+x+" "+y+direction);
        System.out.println("projectile speed"+speed);
        if (direction.equals("up")) y -= speed;
        if (direction.equals("down")) y += speed;
        if (direction.equals("left")) x -= speed;
        if (direction.equals("right")) x += speed;

        life--;
        if(life<=0)alive=false;
    }

    @Override
    public void draw(Graphics2D g2) {
        System.out.println("draw projectile at" +x+" "+y);
        int screenX = x;
        int screenY = y;
        if (user.obj_name.equals("Player")){
            screenX = screenX - user.x + gp.screenWidth/2;
            screenY = screenY - user.y + gp.screenHeight/2;
        }
//        int screenX = x -user.x

        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);

        System.out.println("drawing projectile");
    }// 21*gp.TileSize

    @Override
    public void getImage() {

    }
}
