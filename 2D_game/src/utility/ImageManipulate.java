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
        g2d.drawImage(originalImage,
                0, 0, newWidth, newHeight, null);
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
    public static BufferedImage makeBlackPixelsTransparent(BufferedImage image) {
        BufferedImage transparentImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);

                // Check if the pixel is black
                if ((pixel & 0x00FFFFFF) == 0) { // Mask out alpha and check if RGB is 0
                    transparentImage.setRGB(x, y, 0x00000000); // Set to fully transparent
                } else {
                    transparentImage.setRGB(x, y, pixel); // Copy original pixel
                }
            }
        }

        return transparentImage;
    }
    public static BufferedImage rotateImage(BufferedImage image, double angle) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Calculate the new size of the rotated image to accommodate rotation
        double radians = Math.toRadians(angle);
        int newWidth = (int) Math.round(width * Math.abs(Math.cos(radians)) + height * Math.abs(Math.sin(radians)));
        int newHeight = (int) Math.round(height * Math.abs(Math.cos(radians)) + width * Math.abs(Math.sin(radians)));

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // Set the rotation anchor to the center of the image
        g2d.translate((newWidth - width) / 2, (newHeight - height) / 2);
        g2d.rotate(radians, width / 2, height / 2);

        // Draw the original image onto the rotated image
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }
}