package sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;//IOException là một loại ngoại lệ (exception) có thể được ném ra khi có sự cố liên quan đến hoạt động nhập/xuất (input/output)

import javax.imageio.ImageIO;
//ImageIO là một lớp tiện ích trong Java cung cấp các phương thức để đọc và ghi hình ảnh từ/đến các tệp

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
        //ImageIO.read: Đây là phương thức của lớp ImageIO dùng để đọc hình ảnh từ một tệp hoặc một luồng (stream) và trả về đối tượng BufferedImage.
            // getClass :  cho phép bạn truy xuất đối tượng Class đại diện cho lớp mà đối tượng hiện tại thuộc về.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Extract sprites
    public BufferedImage getSprite(int col, int row) {
        return spriteSheet.getSubimage(col * spriteWidth, row * spriteHeight, spriteWidth, spriteHeight);
    }
    //x: Tọa độ x của góc trên bên trái của hình ảnh con (subimage) mà bạn muốn cắt từ hình ảnh gốc.
    //y: Tọa độ y của góc trên bên trái.
    //width: Chiều rộng của hình ảnh con.
    //height: Chiều cao của hình ảnh con
}
//SpriteSheet sheet = new SpriteSheet("2D_game/resources/player/walk.png", gp.originalTileSize, gp.originalTileSize, 8, 4);
