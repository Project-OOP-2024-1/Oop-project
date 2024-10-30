package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, rightPressed, leftPressed;

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
                    gp.ui.commanNum--;
                    if (gp.ui.commandNum < 0)){
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commanNum++;
                    if (gp.ui.commandNum > 2)){
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
                    if (gp.ui.commandNum < 0)){
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3)){
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0){
                        System.out.println("Do some fighter specific stuff!");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
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
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
    }

    @Override
    // Register released key
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
    }
}
