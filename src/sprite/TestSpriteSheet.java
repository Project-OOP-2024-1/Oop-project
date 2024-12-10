package sprite;

import main.GamePanel;
import tile.Tile;

import java.awt.*;
import java.io.IOException;

public class TestSpriteSheet {

    public static void main(String[] args) {
        SpriteSheet sheet = new SpriteSheet("/tiles/map_tileset.png", 16, 16);
        Tile tile =new Tile();
        tile.image=sheet.getSprite(6,1);
        System.out.println("Sprite Sheet loaded successfully!");
    }
}