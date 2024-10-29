package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, rightPressed, leftPressed, lifeIncPressed,lifeDecPressed;
    public int key_up = KeyEvent.VK_W,key_down = KeyEvent.VK_S,key_left=KeyEvent.VK_A,key_right=KeyEvent.VK_D,keyLifeInc=KeyEvent.VK_I,keyLifeDec=KeyEvent.VK_Y;
    public KeyHandler(int key_up,int key_down,int key_left, int key_right){
        this.key_up=key_up;
        this.key_down=key_down;
        this.key_left = key_left;
        this.key_right = key_right;
    }
    public KeyHandler(){}

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    // Register pressed key
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == key_up) {
            upPressed = true;
        }
        if (code == key_down) {
            downPressed = true;
        }
        if (code == key_right) {
            rightPressed = true;
        }
        if (code == key_left) {
            leftPressed = true;
        }
        if (code == keyLifeInc) {
            lifeIncPressed = true;
        }
        if (code == keyLifeDec) {
            lifeDecPressed = true;
        }
    }

    @Override
    // Register released key
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code ==key_up) {
            upPressed = false;
        }
        if (code ==key_down) {
            downPressed = false;
        }
        if (code == key_right) {
            rightPressed = false;
        }
        if (code == key_left) {
            leftPressed = false;
        }
        if (code == keyLifeInc) {
            lifeIncPressed = false;
        }
        if (code == keyLifeDec) {
            lifeDecPressed = false;
        }
    }
}
