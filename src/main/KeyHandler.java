package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyHandler implements KeyListener {
    private HashMap<Integer, Boolean> Key_Set;
    GamePanel gp;
    public KeyHandler(GamePanel gp){
        this.gp = gp;
        //Set dictiondary for key
        Key_Set = new HashMap<>();

        // Set up
        Key_Set.put(KeyEvent.VK_W,false); //"up"
        Key_Set.put(KeyEvent.VK_S,false);//"down"
        Key_Set.put(KeyEvent.VK_D,false);//"right"
        Key_Set.put(KeyEvent.VK_A,false);//"left"
        Key_Set.put(KeyEvent.VK_P,false);//"pause"
        Key_Set.put(KeyEvent.VK_K,false);//"shot"
        Key_Set.put(KeyEvent.VK_L,false);//"attack"
        Key_Set.put(KeyEvent.VK_C,false);//"Character"
        Key_Set.put(KeyEvent.VK_ENTER,false);//for dialog and tilte screen
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    // Register pressed key
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        // TITLE STATE
        if (gp.gameState == gp.titleState){

            if (gp.ui.titleScreenState == 0){
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0){
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 2){
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0){
                        gp.ui.titleScreenState = 1;
                    }
                    if (gp.ui.commandNum == 1){
                        //add later
                    }
                    if (gp.ui.commandNum == 2){
                        System.exit(0);
                    }
                }
            }
            else if (gp.ui.titleScreenState == 1){
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0){
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3){
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0){
                        System.out.println("Do some fighter specific stuff!");
                        gp.gameState = gp.playState;
//                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 1){
                        System.out.println("Do some thief specific stuff!");
                    }
                    if (gp.ui.commandNum == 2){
                        System.out.println("Do some sorcerer specific stuff!");
                    }
                    if (gp.ui.commandNum == 3){
                        gp.ui.titleScreenState = 0;
                    }
                }
            }

        }
        // PLAY STATE
        else {
            if(Key_Set.containsKey(code)){
                Key_Set.put(code,true);
            }
        }
        if (isPressed(80)){
            if (gp.gameState==gp.playState){
                gp.gameState=gp.pauseState;
            }
            else if (gp.gameState==gp.pauseState){
                gp.gameState=gp.playState;
            }
        }
        if (isPressed(10) && gp.ui.messageOn){
            gp.gameState=gp.dialogueState;
            gp.ui.messageCounter++;
        }
        if (isPressed(67)){
            if (gp.gameState == gp.playState){
                gp.gameState = gp.characterState;
            }
            else if (gp.gameState == gp.characterState){
                gp.gameState = gp.playState;
            }
        }
        if (gp.gameState==gp.characterState){
            if(isPressed(87)){
                if(gp.ui.slotRow != 0){
                    gp.ui.slotRow--;
                }
            }
            if(isPressed(65)){
                if(gp.ui.slotCol != 0){
                    gp.ui.slotCol--;
                }
            }
            if(isPressed(83)){
                if(gp.ui.slotRow != 3){
                    gp.ui.slotRow++;
                }
            }
            if(isPressed(68)){
                if(gp.ui.slotCol != 4) {
                    gp.ui.slotCol++;
                }
            }
        }
    }

    @Override
    // Register released key
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if (Key_Set.containsKey(code)){
            Key_Set.put(code,false);
        }
    }
    public boolean isPressed(int code){
        return Key_Set.get(code);

    }
}