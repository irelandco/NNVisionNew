package ImageLoadin;

import NeuralNet.NeuronLayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.round;

public class ImageExperiment {

    static double[][] inputs;
    static double[][] outputs;

    static double[][] testInputs;
    static double[][] testOutputs;

    static Scanner inputStream = null;

    final static String imgString = "feet.png";

    public static void main(String[] args) {

        System.out.println("Finding the Neural Net...");

        init_net();

        System.out.println("Loading the Neural Net...");

        NeuronLayer layer1 = new NeuronLayer(Neurons.one, Neurons.imgWidth * Neurons.imgHeight);
        NeuronLayer layer2 = new NeuronLayer(Neurons.two, Neurons.one);
        NeuronLayer layer3 = new NeuronLayer(Neurons.three, Neurons.two);

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

        ThreeLayerNet net = new ThreeLayerNet(layer1, layer2, layer3, 0);
        System.out.println("Neural Net loaded!");

        System.out.println("\nFinding test image...");
        BufferedImage currImg;
        BufferedImage tower;


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

        /*
        for (int y = 0; y < tower.getHeight(); y++) {
            for (int x = 0; x < tower.getWidth(); x++) {
                Color c = new Color(tower.getRGB(x, y));
                int red = c.getRed();

                if (c.getRed() != 0 && c.getGreen() != 0 && c.getBlue() != 0) {
                    reds[(y * tower.getWidth()) + x] = red;
                }
            }
        }*/

        try {
            ImageIO.write(currImg, "png", new File("yeet2.png"));
        } catch (IOException e) {
            System.out.println("ERROR: IMAGE NOT SAVED!!!!");
            return;
        }

        System.out.println("Image stored!\n\nPredicting outcome...");

        predict(new double[][] {reds}, net);
    }

    public static void predict(double[][] testInput, ThreeLayerNet net) {
        net.think(testInput);

        for (int i = 0; i < net.getOutput()[0].length; i++) {
            double output = net.getOutput()[0][i];
            System.out.println("Prediction -> "
                    + round(output * 100) + "% chance of " + imgString + " being position " + i + ".");

            if(output>=0.9) {
                System.out.println("---==== ITS THERE ====---");
            } else {
                System.out.println("---==== NOT THERE ====---");
            }
        }
    }

    private static void init_net() {
        try {
            inputStream = new Scanner(new FileReader(Neurons.netLocation));
        } catch (FileNotFoundException exception) {
            System.out.println("Failure to find file!");
        }
    }
}
