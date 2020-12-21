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

public class ImageTest {

    static double[][] inputs;
    static double[][] outputs;

    static double[][] testInputs;
    static double[][] testOutputs;

    static Scanner inputStream = null;






    public static void main(String[] args) {

        try {
            inputStream = new Scanner(new FileReader("/Users/Ralph/IdeaProjects/CompSciA/ImageNet.txt"));
        } catch (FileNotFoundException exception) {
            System.out.println("Failure to find file!");
        }

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

        ThreeLayerNet net = new ThreeLayerNet(layer1, layer2, layer3, 0.1);


        System.out.println("Training the neural net...");

        final int BAD_TESTS = 15, GOOD_TESTS = 15;
        BufferedImage[] img = new BufferedImage[BAD_TESTS + GOOD_TESTS];
        double[][] reds = new double[GOOD_TESTS + BAD_TESTS][Neurons.imgWidth * Neurons.imgHeight];
        try {
            for (int i = 1; i < 1 + BAD_TESTS; i++) {
                img[i-1] = ImageIO.read(new File("img" + i + ".jpg"));
            }

            for (int i = 1 + BAD_TESTS; i < 1 + BAD_TESTS + GOOD_TESTS; i++) {
                img[i-1] = ImageIO.read(new File("img" + (i-BAD_TESTS) + " copy.jpg"));
            }

            for (int i = 0; i < BAD_TESTS + GOOD_TESTS; i++) {
                BufferedImage currImg = img[i];
                for (int y = 0; y < Neurons.imgHeight; y++) {
                    for (int x = 0; x < Neurons.imgWidth; x++) {
                        Color c = new Color(currImg.getRGB(x, y));
                        int red = c.getRed();

                        reds[i][y * Neurons.imgWidth + x] = red;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: IMAGE NOT FOUND");
        }


        inputs = reds;
        outputs = new double[BAD_TESTS + GOOD_TESTS][Neurons.three];

        for (int i = 0; i < BAD_TESTS + GOOD_TESTS; i++) {
            if (i < BAD_TESTS) {
                outputs[i][0] = 0;
            } else {
                outputs[i][0] = 1;
            }
        }




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
        testOutputs = new double[][] {{0}, {0}, {0}};


        //generateNew();
        //testValues();
        net.applyTests(testInputs, testOutputs);
        net.train(inputs, outputs, 200);
        System.out.println("Finished training.");
        net.exportNet();
        System.out.println("Net exported to file.");
    }

    public static void predict(double[][] testInput, ThreeLayerNet net) {
        net.think(testInput);

        // then
        System.out.println("Prediction on data "
                + testInput[0][0] + " "
                + testInput[0][1] + " "
                + testInput[0][2] + " "
                + testInput[0][3] + " -> "
                + net.getOutput()[0][0] + ", expected -> " + testInput[0][0]);
    }
}
