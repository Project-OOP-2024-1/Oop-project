package Monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_SlimeHeart;
import object.OBJ_Slimeball;
import sprite.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SLime extends Entity {
    public int frameCount = 4;
    GamePanel gp;
    public int defaultX=0;
    public int defaultY=0;
    public SLime(GamePanel gp){
        super(gp);
        this.gp=gp;
        name = "Slime";
        direction="idle";
        speed=1;
        maxLife=4;
        life=maxLife;
        solidregion= new Rectangle(8,16,32,24);
        Attackregion= new Rectangle(-gp.tileSize*5,-gp.tileSize*5,gp.tileSize*11,gp.tileSize*11);
        getImage();
        attack=false;
        alive=true;
        damaged=false;
        death=false;
        projectile= new OBJ_Slimeball(gp);
    }
    public void getImage(){
        SpriteSheet sheet = new SpriteSheet("/SLIME/silme_animation_w_trans.png", gp.originalTileSize, gp.originalTileSize);

        rightSprites = new BufferedImage[frameCount];
        leftSprites = new BufferedImage[frameCount];
        upSprites = new BufferedImage[frameCount];
        downSprites= new BufferedImage[frameCount];
        idleSprites= new BufferedImage[frameCount];
        deathSprites= new BufferedImage[frameCount];

        for (int i = 0; i < frameCount; i++) {
            rightSprites[i] = sheet.getSprite(i,2 ); // Extract the sprites
            leftSprites[i] = sheet.getSprite(i, 3);
            upSprites[i] = sheet.getSprite(i, 1);
            downSprites[i] = sheet.getSprite(i,0 );
            idleSprites[i] = sheet.getSprite(i, 4);
            deathSprites[i]= sheet.getSprite(i, 5);
        }
    }
    public void setAction(){
        if(defaultX==0 && defaultY==0){
            defaultX=x;
            defaultY=y;
        }
        if (attack && !collisionOn){
            if (Math.abs(gp.player.x-x) < Math.abs(gp.player.y-y)){
                if (Math.abs(gp.player.x-x)>48) {
                    if (gp.player.x - x > 0) {
                        direction = "right";
                    } else if (gp.player.x - x < 0) {
                        direction = "left";
                    }
                }
                else{
                    if (!projectile.alive){
                        projectile.set(x,y,direction,true,this);
                        gp.projectileList.add(projectile);
                    }
                    if (gp.player.y>y){
                        direction="down";
                    }
                    else {
                        direction="up";
                    }
                }
            }
            else{
                if(Math.abs(gp.player.y-y)>48) {
                    if (gp.player.y > y) {
                        direction = "down";
                    } else if (gp.player.y < y) {
                        direction = "up";
                    }
                }
                else {
                    if (!projectile.alive){
                        projectile.set(x,y,direction,true,this);
                        gp.projectileList.add(projectile);
                    }
                    if (gp.player.x>x){
                        direction="right";
                    }
                    else {
                        direction="left";
                    }
                }
            }
        }
        else{
            if (Math.abs(defaultX-x) < Math.abs(defaultY-y)){
                if (Math.abs(defaultX-x)>48) {
                    if (defaultX - x > 0) {
                        direction = "right";
                    } else  {
                        direction = "left";
                    }
                }
                else{
                    if (defaultY>y){
                        direction="down";
                    }
                    else {
                        direction="up";
                    }
                }
            }
            else{
                if(defaultY-y>24) {
                    if (defaultY> y) {
                        direction = "down";
                    } else {
                        direction = "up";
                    }
                }
                else {
                    if (x<defaultX-gp.tileSize*3){
                        direction="right";
                    }
                    else if(x>defaultX+gp.tileSize*3){
                        direction="left";
                    }
                    else {
                        Random ran = new Random();
                        int num= ran.nextInt(3);
                        switch (num){
                            case 0: direction="right";break;
                            case 1: direction="idle"; break;
                            case 2: direction="left"; break;
                        }
                    }
                }
            }
        }
    }
    public void update() {
        Countersprite++;
        CounterNPC++;
        if (CounterNPC > 30) {
            setAction();
            CounterNPC = 0;
        }
        if (Countersprite > 15) {
            if (Numsprite>3){
                Numsprite=0;
            }
            Numsprite++;
            Countersprite = 0;
        }
        collisionOn = false;
        gp.colis.checkTile(this);
        gp.colis.checkPlayer(this);
        gp.colis.checkObject(this,gp.object);
        gp.colis.checkDanger(this);
        //take damage from player
        if (gp.colis.Damaged(this) && gp.player.attack){
            damaged=true;
        }
        if (damaged && !invincible){
            if (life>0) life--;
            invincible=true;
        }
        if (life<=0) {
            gp.player.inventory.add(new OBJ_SlimeHeart(gp));
            alive=false;
        }
        if (!gp.player.attack) damaged=false;
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "right":
                    x += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
            }
        }
        if (invincible){
            invincilbleCounter++;
            if (invincilbleCounter>60) {
                invincible = false;
                invincilbleCounter=0;
            }
        }
    }
    public void draw(Graphics2D g2) {
        image = null;
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
                    image = idleSprites[Numsprite - 1];
                    break;
            }
            //Hp monster
            g2.setColor(new Color(35,35,35));
            g2.fillRect(screenX,screenY-15,gp.tileSize,10);
            g2.setColor(new Color(255,0,30));
            g2.fillRect(screenX,screenY-15, life*12,10);
            if (invincible){
                if (invincilbleCounter<30){
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
                }
                else {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                }
            }
            if (!alive) {
                draw_death(g2);
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            //reset;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        }
    }

    public void draw_death(Graphics2D g2){
        image=deathSprites[NumDeath];
        if (Counterdeath>10){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.2f));
        }
        else{
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        }
    }
}