package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height =2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row ++;
            }

        }



    }
    public void checkEvent() {
        // Check if the character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.x - previousEventX);
        int yDistance = Math.abs(gp.player.y - previousEventY);
        int distance = Math.max(xDistance,yDistance);
        if(distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if(canTouchEvent == true ) {
            if(hit(27,16,"right") == true) {damagePit(27,16, gp.dialogueState);}
            if(hit(27,16,"right") == true) {teleport(gp.dialogueState);}
            if(hit(23,12,"up") == true) {healingPool(23,12, gp.dialogueState);}
        }

    }
    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;
        int default_solix = gp.player.solidregion.x;
        int default_soliy = gp.player.solidregion.y;
        gp.player.solidregion.x = gp.player.x + gp.player.solidregion.x;
        gp.player.solidregion.y = gp.player.y + gp.player.solidregion.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = col*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidregion.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.x;
                previousEventY = gp.player.y;
            }
        }
        gp.player.solidregion.x = default_solix;
        gp.player.solidregion.y = default_soliy;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;

    }
    public void teleport(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "teleport!";
        gp.player.x = gp.tileSize * 37;
        gp.player.y = gp.tileSize * 10;

    }
    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    public void healingPool(int col, int row, int gameState) {

        if(gp.keyH.isPressed(10)) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water.\nYour life has been recovered.";
            gp.player.life = gp.player.maxLife;
        }
    }
}