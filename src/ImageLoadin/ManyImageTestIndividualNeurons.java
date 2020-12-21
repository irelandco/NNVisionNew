package ImageLoadin;

import NeuralNet.NeuronLayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ManyImageTestIndividualNeurons {

    static double[][] inputs;
    static double[][] outputs;

    static double[][] testInputs;
    static double[][] testOutputs;

    static Scanner inputStream = null;

    public static void main(String[] args) {


        init_net();

        NeuronLayer layer1 = new NeuronLayer(Neurons.one, Neurons.imgWidth * Neurons.imgHeight);
        NeuronLayer layer2 = new NeuronLayer(Neurons.two, Neurons.one);
        NeuronLayer layer3 = new NeuronLayer(Neurons.three, Neurons.two);

        boolean resuming = true;
        if (resuming) {
            //START
            if (inputStream != null) {
                for (int i = 0; i < layer1.weights.length; ++i) {
                    for (int j = 0; j < layer1.weights[0].length; ++j) {
                        layer1.weights[i][j] = Double.parseDouble(inputStream.next());
                    }
                }

                for (int i = 0; i < layer2.weights.length; ++i) {
                    for (int j = 0; j < layer2.weights[0].length; ++j) {
                        layer2.weights[i][j] = Double.parseDouble(inputStream.next());
                    }
                }

                for (int i = 0; i < layer3.weights.length; ++i) {
                    for (int j = 0; j < layer3.weights[0].length; ++j) {
                        layer3.weights[i][j] = Double.parseDouble(inputStream.next());
                    }
                }
            }
            //END
        }

        ThreeLayerNet net = new ThreeLayerNet(layer1, layer2, layer3, 0.005);


        System.out.println("Training the neural net...");

        final int IMAGES = 5;
        outputs = new double[IMAGES*11][Neurons.three];
        BufferedImage[] img = new BufferedImage[IMAGES];
        double[][] reds = new double[IMAGES*11][Neurons.imgWidth * Neurons.imgHeight];
        try {
            BufferedImage sampleImg = ImageIO.read(new File("img1.jpg"));
            BufferedImage tower = ImageIO.read(new File("Tower only.png"));
            for (int i = 1; i < 1 + IMAGES; i++) {
                img[i-1] = ImageIO.read(new File("img" + i + ".jpg"));
                BufferedImage currImg = img[i-1];

                /*for (int y = 0; y < Neurons.imgHeight; y++) {
                    for (int x = 0; x < Neurons.imgWidth; x++) {
                        Color c = new Color(currImg.getRGB(x, y));
                        int red = c.getRed();

                        reds[(i-1)*11][y * Neurons.imgWidth + x] = red;
                    }
                }
                outputs[((i-1)*11)][0] = 0;*/

                for (int j = 0; j < 11; j++) {
                    for (int y = 0; y < Neurons.imgHeight; y++) {
                        for (int x = 0; x < Neurons.imgWidth; x++) {
                            Color c = new Color(currImg.getRGB(x, y));
                            int red = c.getRed();

                            reds[((i-1)*11)+j][y * Neurons.imgWidth + x] = red;
                        }
                    }

                    for (int y = 0; y < tower.getHeight(); y++) {
                        for (int x = 0; x < tower.getWidth(); x++) {
                            Color c = new Color(tower.getRGB(x, y));
                            int red = c.getRed();

                            if (c.getRed() != 0 && c.getGreen() != 0 && c.getBlue() != 0) {
                                reds[((i-1)*11)+j][(y * tower.getWidth()) + (x + (j * 10))] = red;
                                if (i == 1 && j == 0) {
                                    sampleImg.setRGB((x + (j * 10)), y, new Color((int) reds[((i-1)*11)+j][y * tower.getWidth() + (x + (j * 10))], c.getGreen(), c.getBlue(), 0).getRGB());
                                }
                            }
                        }
                    }

                    outputs[((i-1)*11)+j][j] = 1;
                }
            }
            ImageIO.write(sampleImg, "png", new File("sample.png"));

            //WHY DIS HERE IN DA FIRS PLACE??
            /*for (int i = 0; i < IMAGES; i++) {
                BufferedImage currImg = img[i];
                for (int y = 0; y < Neurons.imgHeight; y++) {
                    for (int x = 0; x < Neurons.imgWidth; x++) {
                        Color c = new Color(currImg.getRGB(x, y));
                        int red = c.getRed();

                        reds[i][y * Neurons.imgWidth + x] = red;
                    }
                }
            }*/
        } catch (IOException e) {
            System.out.println("ERROR: IMAGE NOT FOUND");
        }


        inputs = reds;

        /*BufferedImage image = new BufferedImage(400, 400, BufferedImage.);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0,0,400,400,reds[0]);
        File file = new File("hellothere");
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {

        }*/




        final int TEST_IMAGES = 3;
        img = new BufferedImage[TEST_IMAGES];
        double[][] testReds = new double[TEST_IMAGES][Neurons.imgWidth * Neurons.imgHeight];
        try {
            for (int i = 1; i < TEST_IMAGES + 1; i++) {
                img[i-1] = ImageIO.read(new File("test" + i + ".jpg"));
            }

            for (int i = 0; i < TEST_IMAGES; i++) {
                BufferedImage currImg = img[i];
                for (int y = 0; y < Neurons.imgHeight; y++) {
                    for (int x = 0; x < Neurons.imgWidth; x++) {
                        Color c = new Color(currImg.getRGB(x, y));
                        int testRed = c.getRed();

                        testReds[i][y * Neurons.imgWidth + x] = testRed;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("AAAHHH");
        }

        testInputs = testReds;
        testOutputs = new double[][] {{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}};

        net.applyTests(testInputs, testOutputs);
        net.train(inputs, outputs, 150);
        System.out.println("Finished training.");
        net.exportNet();
        predict(new double[][] {reds[0]}, net);
        System.out.println("Net exported to file.");

        //APPEND(net, reds[0]);
    }

    public static void APPEND(ThreeLayerNet net, double[] otherReds) {
        System.out.println("\nFinding test image...");
        BufferedImage currImg;
        BufferedImage tower;
        String imgString = "img1.jpg";

        try {
            currImg = ImageIO.read(new File(imgString));
            tower = ImageIO.read(new File("Tower only.png"));
        } catch (IOException e) {
            System.out.println("ERROR: IMAGE NOT FOUND!!!!");
            return;
        }

        double[] reds = new double[Neurons.imgWidth * Neurons.imgHeight];
        for (int y = 0; y < Neurons.imgHeight; y++) {
            for (int x = 0; x < Neurons.imgWidth; x++) {
                Color c = new Color(currImg.getRGB(x, y));
                int red = c.getRed();

                reds[y * Neurons.imgWidth + x] = red;
            }
        }

        for (int y = 0; y < tower.getHeight(); y++) {
            for (int x = 0; x < tower.getWidth(); x++) {
                Color c = new Color(tower.getRGB(x, y));
                int red = c.getRed();

                if (c.getRed() != 0 && c.getGreen() != 0 && c.getBlue() != 0) {
                    reds[(y * tower.getWidth()) + x] = red;
                }
            }
        }

        try {
            ImageIO.write(currImg, "png", new File("yeet2.png"));
        } catch (IOException e) {
            System.out.println("ERROR: IMAGE NOT SAVED!!!!");
            return;
        }

        System.out.println("Image stored!\n\nPredicting outcome...");

        predict(new double[][] {reds}, net);
        predict(new double[][] {otherReds}, net);
    }

    public static void predict(double[][] testInput, ThreeLayerNet net) {
        net.think(testInput);

        // then
        System.out.println("Prediction on data "
                + net.getOutput()[0][0]);
    }

    private static void init_net() {
        try {
            inputStream = new Scanner(new FileReader("/Users/Ralph/IdeaProjects/CompSciA/ImageNet.txt"));
        } catch (FileNotFoundException exception) {
            System.out.println("Failure to find file!");
        }
    }
}
