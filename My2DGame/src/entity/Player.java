package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screenX, screenY;
//	public int hasKey = 0;
	int standCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);

		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize / 2);
		screenY = gp.screenHeight/2 - (gp.tileSize / 2);
		
		solidArea = new Rectangle();
		solidArea.x = 16;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 16;
		solidArea.height = 16;
		
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		
		worldX = gp.tileSize * 22;
		worldY = gp.tileSize * 21;
		speed = 4; 
		direction = "left";
	}
	
	public void getPlayerImage() {
		
		up1 = setup("/player/run_up1");
		up2 = setup("/player/run_up2");
		up3 = setup("/player/run_up3");
		up4 = setup("/player/run_up4");
		up5 = setup("/player/run_up5");
		up6 = setup("/player/run_up6");
		up7 = setup("/player/run_up7");
		up8 = setup("/player/run_up8");
		
		down1 = setup("/player/run_down1");
		down2 = setup("/player/run_down2");
		down3 = setup("/player/run_down3");
		down4 = setup("/player/run_down4");
		down5 = setup("/player/run_down5");
		down6 = setup("/player/run_down6");
		down7 = setup("/player/run_down7");
		down8 = setup("/player/run_down8");
		
		right1 = setup("/player/run_right1");
		right2 = setup("/player/run_right2");
		right3 = setup("/player/run_right3");
		right4 = setup("/player/run_right4");
		right5 = setup("/player/run_right5");
		right6 = setup("/player/run_right6");
		right7 = setup("/player/run_right7");
		right8 = setup("/player/run_right8");
		
		left1 = setup("/player/run_left1");
		left2 = setup("/player/run_left2");
		left3 = setup("/player/run_left3");
		left4 = setup("/player/run_left4");
		left5 = setup("/player/run_left5");
		left6 = setup("/player/run_left6");
		left7 = setup("/player/run_left7");
		left8 = setup("/player/run_left8");
	}
	
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true 
				|| keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if (keyH.downPressed == true) {
				direction = "down";
			}
			else if (keyH.leftPressed == true) {
				direction = "left";
			}
			else if (keyH.rightPressed == true) {
				direction = "right";
			}
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up": worldY -= speed;	break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum < 8) {
					spriteNum ++;
				}
				else if(spriteNum == 8) {
					spriteNum =1;
				}
				spriteCounter = 0;
			}
		}
		
	}
	
	public void pickUpObject(int i) {
		
		if(i != 999) {

//			String objectName = gp.obj[i].name;
//			
//			switch(objectName) {
//			case "Key":
//				hasKey++;
//				gp.obj[i] = null;
//				System.out.println("Key:" + hasKey);
//				break;
//			case "Door":
//				if(hasKey > 0) {
//					gp.obj[i] = null;
//					hasKey--;
//				}
//				System.out.println("Key:" + hasKey);
//				break;
//			}
		}
	}
	
	public void interactNPC(int i) {
		if(i != 999) {
			
			gp.keyH.enterPressed = true;
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.enterPressed = false;
	}
	public void draw(Graphics2D g2) {
		 
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			switch(spriteNum) {
			case 1: image = up1; break;
			case 2: image = up2; break;
			case 3: image = up3; break;
			case 4: image = up4; break;
			case 5: image = up5; break;
			case 6: image = up6; break;
			case 7: image = up7; break;
			case 8: image = up8; break;
			}
			break;
		case "down":
			switch(spriteNum) {
			case 1: image = down1; break;
			case 2: image = down2; break;
			case 3: image = down3; break;
			case 4: image = down4; break;
			case 5: image = down5; break;
			case 6: image = down6; break;
			case 7: image = down7; break;
			case 8: image = down8; break;
			}
			break;
		case "right":
			switch(spriteNum) {
			case 1: image = right1; break;
			case 2: image = right2; break;
			case 3: image = right3; break;
			case 4: image = right4; break;
			case 5: image = right5; break;
			case 6: image = right6; break;
			case 7: image = right7; break;
			case 8: image = right8; break;
			}
			break;
		case "left":
			switch(spriteNum) {
			case 1: image = left1; break;
			case 2: image = left2; break;
			case 3: image = left3; break;
			case 4: image = left4; break;
			case 5: image = left5; break;
			case 6: image = left6; break;
			case 7: image = left7; break;
			case 8: image = left8; break;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, null);
	}
}
