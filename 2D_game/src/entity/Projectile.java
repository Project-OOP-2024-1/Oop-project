package entity;

import main.GamePanel;

import java.awt.*;

public class Projectile extends Entity{
    static int totalNumber = 0;
    public int useCost;
    Entity user;
    public Projectile(GamePanel gp){
        super(gp);
        this.gp = gp;
        obj_name = "Projectile"+totalNumber;
        totalNumber+=1;
    }
    public void set(int worldX, int worldY, String direction,boolean alive, Entity user){
        this.x = worldX;
        this.y = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }
    @Override
    public void update(){
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
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }

    @Override
    public void getImage() {

    }
}
