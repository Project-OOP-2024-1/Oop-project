package sprite;

import java.io.IOException;

public class TestSpriteSheet {

    public static void main(String[] args) {
        String filePath = "/player/walk.png";
        int spriteWidth = 32; // Adjust based on your sprite size
        int spriteHeight = 32;
        int rows = 8;
        int columns = 4;

        SpriteSheet sheet = new SpriteSheet(filePath, spriteWidth, spriteHeight, rows, columns);
        System.out.println("Sprite Sheet loaded successfully!");
    }
}