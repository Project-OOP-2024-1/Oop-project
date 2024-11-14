package main;

import Monster.SLime;
import entity.Entity;
import entity.NPC_1;
import object.OBJ_Chest;
import object.OBJ_Key;

public class Asset_Setter {
    GamePanel gp;
    public Asset_Setter(GamePanel gp){
        this.gp=gp;
    }
    public void setNPC(){
        gp.npc[0]= new NPC_1(gp);
        gp.npc[0].x=gp.tileSize*21;
        gp.npc[0].y=gp.tileSize*19;
    }
    public void setMonster(){
        gp.monster[0]= new SLime(gp);
        gp.monster[0].x=gp.tileSize*21;
        gp.monster[0].y=gp.tileSize*17;
    }
    public void setObject(){
        gp.object[0]=new OBJ_Chest(gp);
        gp.object[0].x=gp.tileSize*21;
        gp.object[0].y=gp.tileSize*16;
        gp.object[1]=new OBJ_Key(gp);
        gp.object[1].x=gp.tileSize*21;
        gp.object[1].y=gp.tileSize*15;
    }
}
