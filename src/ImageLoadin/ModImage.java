package ImageLoadin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ModImage {

    public static void main(String[] args) {
        try {
            BufferedImage bImage = ImageIO.read(new File("img1.jpg"));
            BufferedImage tower = ImageIO.read(new File("Tower only.png"));

            int[] reds = new int[Neurons.imgWidth * Neurons.imgHeight];

            for (int y = 0; y < Neurons.imgHeight; y++) {
                for (int x = 0; x < Neurons.imgWidth; x++) {
                    Color c = new Color(bImage.getRGB(x, y));
                    int red = c.getRed();

                    reds[y * Neurons.imgWidth + x] = red;
                }
            }

            for (int y = 0; y < tower.getHeight(); y++) {
                for (int x = 0; x < tower.getWidth(); x++) {
                    Color c = new Color(tower.getRGB(x, y));
                    int red = c.getRed();

                    Color tC = new Color(tower.getRGB(x, y));
                    if (tC.getRed() != 0 && tC.getGreen() != 0 && tC.getBlue() != 0) {
                        reds[(y * tower.getWidth()) + (x + (0 * 10))] = red;
                        bImage.setRGB((x + (0 * 10)), y, new Color(reds[y * tower.getWidth() + (x + (0 * 10))], c.getGreen(), c.getBlue(), 0).getRGB());
                    }
                }
            }

            //int argb = alpha << 24 + red << 16 + green << 8 + blue;
            //bImage.setRGB(10, 10, new Color(255, 255, 255, 255).getRGB());

            System.out.println(new Color(tower.getRGB(10, 10)).toString());



            /*for (int i = 0; i < bImage.getHeight(); i++) {
                for (int j = 0; j < bImage.getHeight(); j++) {
                    bImage.setRGB(i, j, 0b1111_1111);
                }
            }*/


            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", new File("sample.png"));
        } catch (IOException e) {

        }
    }
}
