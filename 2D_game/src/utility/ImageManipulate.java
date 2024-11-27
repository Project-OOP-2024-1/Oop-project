package utility;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageManipulate {
    public static BufferedImage scaleImage(BufferedImage originalImage, int scaleFactor) throws IOException {
        int newWidth = originalImage.getWidth() * scaleFactor;
        int newHeight = originalImage.getHeight() * scaleFactor;

        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics2D g2d = scaledImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();


        return scaledImage;
    }
    public static BufferedImage concatenateImage(BufferedImage[] list_of_images, int axis) {
        int totalWidth = 0, totalHeight = 0;

        // Calculate the total dimensions based on the axis
        if (axis == 0) { // Horizontal concatenation
            totalWidth = 0;
            for (BufferedImage image : list_of_images) {
                totalWidth += image.getWidth();
            }
            totalHeight = list_of_images[0].getHeight();
        } else { // Vertical concatenation
            totalWidth = list_of_images[0].getWidth();
            for (BufferedImage image : list_of_images) {
                totalHeight += image.getHeight();
            }
        }

        // Create a new BufferedImage to hold the concatenated image
        BufferedImage concatenatedImage = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = concatenatedImage.createGraphics();

        // Concatenate images based on the axis
        int x = 0, y = 0;
        for (BufferedImage image : list_of_images) {
            if (axis == 0) {
                g2d.drawImage(image, x, 0, null);
                x += image.getWidth();
            } else {
                g2d.drawImage(image, 0, y, null);
                y += image.getHeight();
            }
        }

        g2d.dispose();
        return concatenatedImage;
    }
}