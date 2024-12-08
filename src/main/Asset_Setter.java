package main;

import Monster.Ghost;
import Monster.SLime;
import Monster.Shit;
import entity.Entity;
import entity.NPC_1;
import object.OBJ_Chest;
import object.OBJ_Healing_pool;
import object.OBJ_Key;
import object.OBJ_TransitionGate;

public class Asset_Setter {
    GamePanel gp;
    public Asset_Setter(GamePanel gp){
        this.gp=gp;
    }
    public void setGeneral(){
        setNPC();
        setMonster(0,43,46,new SLime(gp));
        setMonster(1,43,47,new Shit(gp));
        setMonster(2,15,45,new Ghost(gp));
        setObject(0,6,50,new OBJ_Chest(gp));
        setObject(1,7,50,new OBJ_Key(gp));
        setEvent(0,24,44,new OBJ_Healing_pool(gp));
        setEvent(1,13,15,new OBJ_TransitionGate(gp));
    }
    public void setNPC(){
        gp.npc[0]= new NPC_1(gp);
        gp.npc[0].x=gp.tileSize*40;
        gp.npc[0].y=gp.tileSize*13;
    }
    public void setMonster(int index,int x,int y,Entity monster){
        gp.monster[index]= monster;
        gp.monster[index].x=gp.tileSize*x;
        gp.monster[index].y=gp.tileSize*y;
    }
    public void setObject(int index,int x,int y,Entity Object){
        gp.object[index]= Object;
        gp.object[index].x=gp.tileSize*x;
        gp.object[index].y=gp.tileSize*y;
    }
    public void setEvent(int index,int x,int y,Entity Object){
        gp.event[index]= Object;
        gp.event[index].x=gp.tileSize*x;
        gp.event[index].y=gp.tileSize*y;
    }

}
