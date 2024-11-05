package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Slime extends Entity{
	
	public NPC_Slime(GamePanel gp) {
		super(gp);
		
		direction = "up";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
			
			up1 = setup("/npc/purple1");
			up2 = setup("/npc/purple2");
			up3 = setup("/npc/purple3");
			up4 = setup("/npc/purple4");
			
			down1 = setup("/npc/purple_down1");
			down2 = setup("/npc/purple_down2");
			down3 = setup("/npc/purple_down3");
			down4 = setup("/npc/purple_down4");
			
			right1 = setup("/npc/purple_right1");
			right2 = setup("/npc/purple_right2");
			right3 = setup("/npc/purple_right3");
			right4 = setup("/npc/purple_right4");
			
			left1 = setup("/npc/purple_left1");
			left2 = setup("/npc/purple_left2");
			left3 = setup("/npc/purple_left3");
			left4 = setup("/npc/purple_left4");
			
	}
	
	public void setDialogue() {
		dialogues[0] = "Hello, oop.";
		dialogues[1] = "So you've come to this island to \nfind the treasure?";
		dialogues[2] = "I used to be a great wizard but now...\n I'm a bit too old for taking an adventure.";
		dialogues[3] = "Well, good luck on you.";
	}
	public void setAction() {
		
		actionLockCounter ++;
		
		if(actionLockCounter == 120) {
			
			Random random = new Random();
			int i = random.nextInt(100) + 1; //pick up a number from 1 to 100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
	}
	
	public void speak() {
		
		//Do this character specific stuff
		super.speak();
	}
	
}
