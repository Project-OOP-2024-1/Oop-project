package monster;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler_multi_object;
import sprite.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Monster extends Entity {
    static Integer numbers_of_monster = 0;
    GamePanel gp;

    public KeyHandler_multi_object keyMonster;
    int frameCount = 4;
    public Entity target;
    public boolean isFollow = false;

    public Monster(GamePanel gp , KeyHandler_multi_object keyMonster , boolean isFollow) {
        super(gp);
        numbers_of_monster += 1;
        this.gp = gp;
        this.keyMonster = keyMonster;
        this.obj_name = "monster" + numbers_of_monster;
        this.isFollow = isFollow;

        getImage(); //load monster's sprite

        setDefaultValue();
    }
    public Monster(GamePanel gp){
        super(gp);
        numbers_of_monster += 1;
        this.gp = gp;
        this.obj_name = "monster" + numbers_of_monster;
        this.isFollow = true;

        getImage(); //load monster's sprite

        setDefaultValue();
    }

    public void setDefaultValue() {

        x =  gp.tileSize * 21;
        y =  gp.tileSize * 21;
        speed = 2;
        direction = "idle";
        //player status
        maxLife = 10;
        life = 7;
        solidregion = new Rectangle(8 , 16 , 42 , 30);

    }

    public void setTarget(Entity object) {
        target = object;
    }

    String Follow() { //Point out the fastest direction to go to target
        if (Math.abs(x - target.x) < Math.abs(y - target.y)) {
            if (y < target.y) {
//                y += speed;
//                direction = "down";
                return "down";

            } else {
//                y -= speed;
//                direction = "up";
                return "up";
            }
        } else {
            if (x < target.x) {
//                x += speed;
//                direction = "right";
                return "right";
            } else if (x>target.x) {
//                x -= speed;
//                direction = "left";
                return "left";
            } else return "idle";
        }
    }

    // Load sprite sheet and extract the player's walking animation sprites
    @Override
    public void getImage() {
        SpriteSheet sheet = new SpriteSheet("/Monster/SLIME/shit_w_trans.png" , gp.originalTileSize , gp.originalTileSize , 8 , 4);

        rightSprites = new BufferedImage[frameCount];
        leftSprites = new BufferedImage[frameCount];
        upSprites = new BufferedImage[frameCount];
        downSprites = new BufferedImage[frameCount];
        idleSprites = new BufferedImage[frameCount];

        for (int i = 0 ; i < frameCount ; i++) {
            rightSprites[i] = sheet.getSprite(i , 2); // Extract the sprites
            leftSprites[i] = sheet.getSprite(i , 3);
            upSprites[i] = sheet.getSprite(i , 1);
            downSprites[i] = sheet.getSprite(i , 0);
            idleSprites[i] = sheet.getSprite(i , 4);
        }
    }


    @Override
    public void update() {
        //update for collision check
        if (isFollow)direction = Follow();
        collisionOn=false;
        gp.colis.checkTile(this);
        if (!collisionOn){
            if(direction.equals("up") )y -= speed;
            if(direction.equals("down"))y+= speed;
            if(direction.equals("left"))x-=speed;
            if(direction.equals("right"))x+=speed;
        }

//
//        //Test monster by hand
//        if (keyMonster.isPressed(this.obj_name , "up")) {
//            direction = "up";
//            y -= speed;
//        } else if (keyMonster.isPressed(this.obj_name , "down")) {
//            direction = "down";
//            y += speed;
//        } else if (keyMonster.isPressed(this.obj_name , "right")) {
//            direction = "right";
//            x += speed;
//        } else if (keyMonster.isPressed(this.obj_name , "left")) {
//            direction = "left";
//            x -= speed;
//        } else {
////            if (keyMonster.lifeDecPressed){
////                if(this.life>0)this.life-=1;
////            }
////            if(keyMonster.lifeIncPressed){
////                if(this.life<6)this.life+=1;
////            }
//            direction = "idle";
//        }
//
//        collisionOn = false;
//        gp.colis.checkTile(this);
    }


    @Override
    // Draw frame
    public void draw(Graphics2D g2) {
//        System.out.println(direction);
        BufferedImage image = null;

        switch (direction) {
            case "right":
                image = rightSprites[Numsprite - 1];
//                System.out.println("Monsterright");
                break;
            case "left":
                image = leftSprites[Numsprite - 1];
//                System.out.println("Monsterleft");
                break;
            case "down":
                image = downSprites[Numsprite - 1];
//                System.out.println("Monsterdown");
                break;
            case "up":
                image = upSprites[Numsprite - 1];
//                System.out.println("Monsterup");
                break;
            case "idle":
                image = idleSprites[Numsprite - 1];
//                System.out.println("Monsteridle");
                break;

        }
        int screenX = x - gp.player.x + gp.screenWidth/2;
        int screenY = y - gp.player.y + gp.screenHeight/2;
        g2.drawImage(image , screenX , screenY , gp.tileSize , gp.tileSize , null);
    }
}
