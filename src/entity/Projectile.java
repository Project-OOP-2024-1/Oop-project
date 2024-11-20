package entity;

import main.GamePanel;
import utility.ImageManipulate;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity{
    public int useCost;
    Entity user;
    public int screenX;
    public int screenY;
    public String direction = "right";
    public Projectile(GamePanel gp){
        super(gp);
        this.gp = gp;
        collisionOn=false;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
    }
    public void set(int x, int y, String direction,boolean alive, Entity user){
        this.x = x;
        this.y = y;
        this.direction=direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }
    @Override
    public void update() {
        collisionOn=false;
        gp.colis.checkTile(this);
        if (!user.name.equals("Slime")){
            gp.colis.checkEntity(this, gp.monster);
        }
        else {
            gp.colis.checkPlayer(this);
        }
        gp.colis.checkObject(this,gp.object);
        if (!collisionOn) {
            if (direction.equals("up")) y -= speed;
            if (direction.equals("down") || direction.equals(("idle"))) y += speed;
            if (direction.equals("left")) x -= speed;
            if (direction.equals("right")) x += speed;
            life--;
        }
        else {
            life=0;
        }
        if (life <= 0) alive = false;
        Countersprite++;
        if (Countersprite > 15) {
            if (Numsprite == 1) {
                Numsprite = 2;
            } else if (Numsprite == 2) {
                Numsprite = 1;
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = x - gp.player.x + gp.player.screenX;
        int screenY = y - gp.player.y + gp.player.screenY;
        if (x + gp.tileSize > gp.player.x - gp.player.screenX &&
                x - gp.tileSize < gp.player.x + gp.player.screenX &&
                y + gp.tileSize > gp.player.y - gp.player.screenY &&
                y - gp.tileSize < gp.player.y + gp.player.screenY) {
            switch (direction) {
                case "right":
                    image = rightSprites[Numsprite - 1];
                    break;
                case "left":
                    image = leftSprites[Numsprite - 1];
                    break;
                case "down":
                    image = downSprites[Numsprite - 1];
                    break;
                case "up":
                    image = upSprites[Numsprite - 1];
                    break;
                case "idle":
                    image = downSprites[Numsprite - 1];
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}