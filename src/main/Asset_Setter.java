package main;

import Monster.Ghost;
import Monster.SLime;
import Monster.Shit;
import entity.Entity;
import entity.NPC_1;
import object.*;

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
        setObject(2,9,15,new OBJ_Mushroom(gp));
        setObject(3,17,17,new OBJ_Mushroom(gp));
        setObject(4,8,24,new OBJ_Mushroom(gp));
        setObject(5,19,31,new OBJ_Mushroom(gp));
        setObject(6,51,31,new OBJ_Mushroom(gp));
        setObject(7,42,38,new OBJ_Mushroom(gp));
        setObject(8,48,49,new OBJ_Mushroom(gp));
        setEvent(0,41,36,new OBJ_Healing_pool(gp));
        setEvent(1,40,7,new OBJ_TransitionGate(gp));
    }
    public void setNPC(){
        gp.npc[0]= new NPC_1(gp);
        gp.npc[0].x=gp.tileSize*42;
        gp.npc[0].y=gp.tileSize*10;
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
