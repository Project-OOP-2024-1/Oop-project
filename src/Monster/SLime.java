package Monster;

import entity.Entity;
import main.GamePanel;
import sprite.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SLime extends Entity {
    public int frameCount = 4;
    GamePanel gp;
    public SLime(GamePanel gp){
        super(gp);
        this.gp=gp;
        name = "Slime";
        speed=2;
        maxLife=4;
        life=maxLife;
        solidregion= new Rectangle(8,16,32,24);
        getImage();
        alive=true;
        damaged=false;
        death=false;
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
        // Set orbit or same thing here;
        Random rand =new Random();
        int num =rand.nextInt(5);
        switch (num){
            case 0: direction="up"; break;
            case 1: direction="down";break;
            case 2: direction="left"; break;
            case 3: direction="right"; break;
            case 4: direction="idle"; break;
        }
    }
    public void update() {
        Countersprite++;
        CounterNPC++;
        if (CounterNPC > 60) {
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
        //take damage from player
        if (gp.colis.Damaged(this) && gp.player.attack){
            if (!damaged && !invincible){
                if(life>0) life--;
                System.out.println(life);
                invincible=true;
                damaged=true;
            }
        }
        if (life==0) {
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
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
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
