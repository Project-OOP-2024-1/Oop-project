package sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

    private BufferedImage spriteSheet;
    private int spriteWidth;
    private int spriteHeight;

    public SpriteSheet(String filePath, int spriteWidth, int spriteHeight) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        try {
            // Load the sprite sheet image
            spriteSheet = ImageIO.read(getClass().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Extract sprites
    public BufferedImage getSprite(int col, int row) {
        return spriteSheet.getSubimage(col * spriteWidth, row * spriteHeight, this.spriteWidth, this.spriteHeight);
    }

}