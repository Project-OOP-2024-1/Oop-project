package main;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Set;

public class KeyHandler_multi_object {
    HashMap<Integer, String[]> keyMap;
    HashMap<String,HashMap<String, Boolean>> Pressed;
    public void addNewKeySet1(String objectName,int keyUp,int keyDown, int keyLeft,int keyRight){
        if (keyMap.containsKey(keyUp)) System.exit(1);
        if (keyMap.containsKey(keyDown)) System.exit(1);
        if (keyMap.containsKey(keyLeft)) System.exit(1);
        if (keyMap.containsKey(keyRight)) System.exit(1);
        String[] tmp=new String[2];
        tmp[0]=objectName;
        tmp[1]="up";
        keyMap.put(keyUp, tmp);
        tmp[1]="down";
        keyMap.put(keyDown, tmp);
        tmp[1]="left";
        keyMap.put(keyLeft, tmp);
        tmp[1]="right";
        keyMap.put(keyRight, tmp);
        HashMap<String,Boolean> tmp2 = new HashMap<>();
        tmp2.put("up",false);
        tmp2.put("down",false);
        tmp2.put("left",false);
        tmp2.put("right",false);
        Pressed.put(objectName,tmp2);
    }
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if (!keyMap.containsKey(code))return;
        String object_name = keyMap.get(code)[0],dir = keyMap.get(code)[1];
        Pressed.get(object_name).put(dir,true);
    }
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        if (!keyMap.containsKey(code))return;
        String object_name = keyMap.get(code)[0],dir = keyMap.get(code)[1];
        Pressed.get(object_name).put(dir,false);
    }
    public boolean isPressed(String obj_name, String dir){
        HashMap<String, Boolean> innerMap = Pressed.get(obj_name);
        if (innerMap != null) {
            return innerMap.get(dir);
        }
        return false;
    }
}
