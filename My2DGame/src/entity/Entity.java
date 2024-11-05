package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, up3, up4, up5, up6, up7, up8,
						 down1, down2, down3, down4, down5, down6, down7, down8,
						 left1, left2, left3, left4, left5, left6, left7, left8, 
						 right1, right2, right3, right4, right5, right6, right7, right8;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum =1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		//NULL
	}
	public void speak() {

		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		
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
			if(spriteNum < 4) {
				spriteNum ++;
			}
			else if(spriteNum == 4) {
				spriteNum =1;
			}
			spriteCounter = 0;
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;

		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction) {
			case "up":
				switch(spriteNum) {
				case 1: image = up1; break;
				case 2: image = up2; break;
				case 3: image = up3; break;
				case 4: image = up4; break;
				}
				break;
			case "down":
				switch(spriteNum) {
				case 1: image = down1; break;
				case 2: image = down2; break;
				case 3: image = down3; break;
				case 4: image = down4; break;
				}
				break;
			case "right":
				switch(spriteNum) {
				case 1: image = right1; break;
				case 2: image = right2; break;
				case 3: image = right3; break;
				case 4: image = right4; break;
				}
				break;
			case "left":
				switch(spriteNum) {
				case 1: image = left1; break;
				case 2: image = left2; break;
				case 3: image = left3; break;
				case 4: image = left4; break;
				}
				break;
			}
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		}
	}
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}




