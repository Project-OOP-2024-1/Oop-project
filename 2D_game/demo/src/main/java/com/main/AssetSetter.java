package com.main;

import object.OBJ_Chest;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
        
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Chest(gp);
        gp.obj[0].worldX = 300;
        gp.obj[0].worldY = 800;
        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 800;
        gp.obj[1].worldY = 800;
    }

}   
