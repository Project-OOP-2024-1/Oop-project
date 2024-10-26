package entity;

import java.awt.Graphics2D;//Represents a 2D rendering context that provides methods for drawing shapes, lines, text, and images onto a surface
import java.awt.image.BufferedImage;//Represents an image with pixel data that can be manipulated and displayed.
                                    //Provides methods for accessing, modifying, and creating images.
//lớp trừu tượng không thể được khoi tao trực tiếp, mà phải được kết thừa boi cac lớp con khác
public abstract class Entity {
    
    public int worldX, worldY;
    public int speed;
// mảng chứa các hình ảnh (sprites) tương ứng với các hướng di chuyen của thực thể
    public BufferedImage[] rightSprites, leftSprites, downSprites , upSprites, idleSprites;
    public String direction;//lưu trữ hướng di chuyển diện tại  của thực the
    public int frameCount, currentFrame, hold, delay;

    public abstract void update();
// Đây là phương thức trừu tượng yêu cầu lớp con phải định nghĩa cach cập nhật trạng thái của thực thể
    public abstract void draw(Graphics2D g2);

    public abstract void getImage();

}


