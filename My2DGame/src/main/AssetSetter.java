package main;

import entity.NPC_Slime;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
//		gp.obj[0] = new OBJ_Key(gp);
//		gp.obj[0].worldX = 17 * gp.tileSize;
//		gp.obj[0].worldY = 4 * gp.tileSize;
//		
//		gp.obj[1] = new OBJ_Key(gp);
//		gp.obj[1].worldX = 28 * gp.tileSize;
//		gp.obj[1].worldY = 21 * gp.tileSize;
//		
//		gp.obj[2] = new OBJ_Door(gp);
//		gp.obj[2].worldX = 45 * gp.tileSize;
//		gp.obj[2].worldY = 11 * gp.tileSize;
//		
//		gp.obj[3] = new OBJ_Door(gp);
//		gp.obj[3].worldX = 32 * gp.tileSize;
//		gp.obj[3].worldY = 48 * gp.tileSize;
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_Slime(gp);
		gp.npc[0].worldX = gp.tileSize*21;
		gp.npc[0].worldY = gp.tileSize*21;

	}
}
