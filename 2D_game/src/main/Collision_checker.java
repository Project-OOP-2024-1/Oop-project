package main;

import entity.Entity;

public class Collision_checker {
    GamePanel gp;
    public Collision_checker(GamePanel gp){
        this.gp=gp;
    }
    public void checkTile(Entity entity){
        int Left_Xentity= entity.x+entity.solidregion.x;
        int Right_Xentity=entity.x+entity.solidregion.x+entity.solidregion.width;
        int Top_Yentity= entity.y+entity.solidregion.y;
        int Bottom_Yentity= entity.y+entity.solidregion.y+entity.solidregion.height;

        int Left_Col = Left_Xentity/gp.tileSize;
        int Right_Col = Right_Xentity/gp.tileSize;
        int Top_Row = Top_Yentity/gp.tileSize;
        int Bottom_Row = Bottom_Yentity/gp.tileSize;

        int numTile_1;
        int numTile_2; // this is tile corresponding to tile in map;

        switch (entity.direction){
            case "up":
                Top_Row = (Top_Yentity-entity.speed)/gp.tileSize;
                numTile_1=gp.tileM.mapTileNum1[Left_Col][Top_Row];
                numTile_2=gp.tileM.mapTileNum2[Right_Col][Top_Row];
                if (gp.tileM.tile[numTile_1].collision || gp.tileM.tile[numTile_2].collision){
                    entity.collisionOn=true;
                }
                break;
            case "down":
                Bottom_Row = (Bottom_Yentity+entity.speed)/gp.tileSize;
                numTile_1=gp.tileM.mapTileNum1[Left_Col][Bottom_Row];
                numTile_2=gp.tileM.mapTileNum2[Right_Col][Bottom_Row];
                if (gp.tileM.tile[numTile_1].collision || gp.tileM.tile[numTile_2].collision){
                    entity.collisionOn=true;
                }
                break;
            case  "right":
                Right_Col = (Right_Xentity+entity.speed)/gp.tileSize;
                numTile_1=gp.tileM.mapTileNum1[Right_Col][Top_Row];
                numTile_2=gp.tileM.mapTileNum2[Right_Col][Bottom_Row];
                if (gp.tileM.tile[numTile_1].collision || gp.tileM.tile[numTile_2].collision){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                Left_Col = (Left_Xentity-entity.speed)/gp.tileSize;
                numTile_1=gp.tileM.mapTileNum1[Left_Col][Bottom_Row];
                numTile_2=gp.tileM.mapTileNum2[Left_Col][Top_Row];
                if (gp.tileM.tile[numTile_1].collision || gp.tileM.tile[numTile_2].collision){
                    entity.collisionOn=true;
                }
                break;
        }

    }
}
