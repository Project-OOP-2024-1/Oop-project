package main;

import monster.Slime;

public class AssestSetter {
    GamePanel gp;

    public AssestSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){}
    public void setNPC(){}
    public void setMonster(){
        gp.monsters[0] = new Slime(gp);
        gp.monsters[0].x = gp.tileSize*21;
        gp.monsters[0].y = gp.tileSize*21;

        gp.monsters[1] = new Slime(gp);
        gp.monsters[1].x = gp.tileSize*1;
        gp.monsters[1].y = gp.tileSize*1;
    }
}
