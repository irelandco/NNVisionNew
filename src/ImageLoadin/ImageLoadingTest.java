package ImageLoadin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoadingTest {

    public static void main(String[] args) {

        BufferedImage[] img = new BufferedImage[10];
        try {
            for (int i = 1; i < 6; i++) {
                img[i-1] = ImageIO.read(new File("img" + i + ".jpg"));
            }

            for (int i = 6; i < 11; i++) {
                img[i-1] = ImageIO.read(new File("img" + (i-5) + " copy.jpg"));
            }

            int[][] reds = new int[10][400*400];


            for (int i = 0; i < 10; i++) {
                BufferedImage currImg = img[i];
                for (int y = 0; y < 400; y++) {
                    for (int x = 0; x < 400; x++) {
                        Color c = new Color(currImg.getRGB(x, y));
                        int red = c.getRed();

                        reds[i][y * 400 + x] = red;
                    }
                }
            }

            System.out.println(reds[0].length);
        } catch (IOException e) {
            System.out.println("AAAHHH");
        }
    }
}
