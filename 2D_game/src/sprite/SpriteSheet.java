package sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class SpriteSheet {

    private BufferedImage spriteSheet;
    private int spriteWidth;
    private int spriteHeight;
    private int rows;
    private int columns;

    public SpriteSheet(String filePath, int spriteWidth, int spriteHeight, int rows, int columns) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.rows = rows;
        this.columns = columns;

        try {
            // Load the sprite sheet image
            spriteSheet = ImageIO.read(getClass().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Extract sprites
    public BufferedImage getSprite(int col, int row) {
        System.out.println("sprite extracted");
        BufferedImage tmp = spriteSheet.getSubimage(col * spriteWidth, row * spriteHeight, spriteWidth, spriteHeight);

        return tmp;
    }

    public static void saveImageToFile(BufferedImage img , String fileName) {
        try {
            // Save as PNG
            File outputFile = new File(fileName);
            ImageIO.write(img, "png", outputFile);
            System.out.println("Image saved as " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//SpriteSheet sheet = new SpriteSheet("2D_game/resources/player/walk.png", gp.originalTileSize, gp.originalTileSize, 8, 4);
