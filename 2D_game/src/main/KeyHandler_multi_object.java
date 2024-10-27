package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyHandler_multi_object implements KeyListener {

    HashMap<Integer, String[]> keyMap = new HashMap<>();
    HashMap<String, HashMap<String, Boolean>> Pressed = new HashMap<>();

    public void addNewKeySet1(String objectName, int keyUp, int keyDown, int keyLeft, int keyRight) {
        if (keyMap.containsKey(keyUp) || keyMap.containsKey(keyDown) || keyMap.containsKey(keyLeft) || keyMap.containsKey(keyRight)) {
            System.out.println("Key conflict detected. Exiting.");
            System.exit(1);
        }

        keyMap.put(keyUp, new String[]{objectName, "up"});
        keyMap.put(keyDown, new String[]{objectName, "down"});
        keyMap.put(keyLeft, new String[]{objectName, "left"});
        keyMap.put(keyRight, new String[]{objectName, "right"});

        HashMap<String, Boolean> tmp = new HashMap<>();
        tmp.put("up", false);
        tmp.put("down", false);
        tmp.put("left", false);
        tmp.put("right", false);
        Pressed.put(objectName, tmp);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (!keyMap.containsKey(code)) return;

        String objectName = keyMap.get(code)[0];
        String dir = keyMap.get(code)[1];
        Pressed.get(objectName).put(dir, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (!keyMap.containsKey(code)) return;

        String objectName = keyMap.get(code)[0];
        String dir = keyMap.get(code)[1];
        Pressed.get(objectName).put(dir, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but required for KeyListener interface
    }

    public boolean isPressed(String objName, String dir) {
        HashMap<String, Boolean> innerMap = Pressed.get(objName);
        return innerMap != null && innerMap.getOrDefault(dir, false);
    }
}
