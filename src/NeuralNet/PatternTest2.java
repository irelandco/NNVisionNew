package NeuralNet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class PatternTest2 {

    static double[][] inputs;
    static double[][] outputs;

    static double[][] testInputs;
    static double[][] testOutputs;

    public static void main(String[] args) {

        NeuronLayer layer1 = new NeuronLayer(49, 42);
        NeuronLayer layer2 = new NeuronLayer(21, 49);
        NeuronLayer layer3 = new NeuronLayer(1, 21);

        ThreeLayerNet net = new ThreeLayerNet(layer1, layer2, layer3, 0.001);


        System.out.println("Training the neural net...");
        pullArrays();
        //generateNew();
        //testValues();
        net.applyTests(testInputs, testOutputs);
        net.train(inputs, outputs, 10000);
        System.out.println("Finished training.");

        System.out.println("Layer 1 Weights");
        System.out.println(layer1.weights);


        // NO
        predict(new double[][] {{0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0}}, net);

        // NO
        predict(new double[][] {{1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0}}, net);

        // YES
        predict(new double[][] {{1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0}}, net);
    }

    public static void pullArrays() {
        inputs = new double[3000][42];
        outputs = new double[3000][1];

        testInputs = new double[100][42];
        testOutputs = new double[100][1];

        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new FileReader("/Users/Ralph/IdeaProjects/CompSciA/Neural.txt"));
        } catch (FileNotFoundException exception) {
            System.out.println("FAILURE");
        }

        if (inputStream != null) {
            for (int i = 0; i < 3000; i++) {
                for (int j = 0; j < 42; j++) {
                    inputs[i][j] = Integer.parseInt(inputStream.next());
                }

                String string = "{";
                for (int j = 0; j < 41; j++) {
                    string = string + (int) inputs[i][j] + ", ";
                }
                string = string + (int) inputs[i][41] + "}";

                outputs[i][0] = Integer.parseInt(inputStream.next());
                if (outputs[i][0] == 1) {
                    string = string + " Y";
                }

                System.out.println(string);

            }

            for (int i = 0; i < 100; i++) {
                System.out.println("AGH AGH");
                for (int j = 0; j < 42; j++) {
                    System.out.println(i + " + " + j);
                    testInputs[i][j] = Integer.parseInt(inputStream.next());
                }
                testOutputs[i][0] = Integer.parseInt(inputStream.next());
            }
        }
    }

    public static void generateNew() {
        inputs = new double[3000][30];
        outputs = new double[3000][1];

        int goods = 0;

        for (int i = 0; i < 3000; i++) {
            boolean good = Math.random() > 0.3;

            if (!good) {
                int zeroes = 0, ones = 0;

                for (int n = 0; n < 30; n++) {
                    if (ones == 15) {
                        inputs[i][n] = 0;
                    } else if (zeroes == 15) {
                        inputs[i][n] = 1;
                    } else if (Math.random() > 0.5) {
                        ones++;
                        inputs[i][n] = 1;
                    } else {
                        zeroes++;
                        inputs[i][n] = 0;
                    }
                }

                outputs[i][0] = 0;

                for (int n = 4; n < 30; n++) {
                    if ((int) (inputs[i][n]+inputs[i][n-1]*10+inputs[i][n-2]*100+inputs[i][n-3]*1000+inputs[i][n-4]*10000) == 10101) {

                        /*System.out.println("AGH");
                        String string = "{";

                        for (int j = 0; j < 29; j++) {
                            string = string + inputs[i][j] + ", ";
                        }
                        string = string + inputs[i][29] + "}";
                        System.out.println(string);*/

                        i--;
                        n = 100;
                    }
                }
            } else {
                goods++;
                int zeroes = 0, ones = 0;

                int start = (int) (Math.random() * 25);

                for (int n = 0; n < start; n++) {
                    if (ones == 12) {
                        inputs[i][n] = 0;
                    } else if (zeroes == 13) {
                        inputs[i][n] = 1;
                    } else if (Math.random() > 0.5) {
                        ones++;
                        inputs[i][n] = 1;
                    } else {
                        zeroes++;
                        inputs[i][n] = 0;
                    }
                }

                inputs[i][start] = 1;
                ones++;
                inputs[i][start+1] = 0;
                zeroes++;
                inputs[i][start+2] = 1;
                ones++;
                inputs[i][start+3] = 0;
                zeroes++;
                inputs[i][start+4] = 1;
                ones++;

                for (int n = start+5; n < 30; n++) {
                    if (ones == 15) {
                        inputs[i][n] = 0;
                    } else if (zeroes == 15) {
                        inputs[i][n] = 1;
                    } else if (Math.random() > 0.5) {
                        ones++;
                        inputs[i][n] = 1;
                    } else {
                        zeroes++;
                        inputs[i][n] = 0;
                    }
                }

                outputs[i][0] = 1;
            }
        }

        System.out.println("Goods: " + goods);
    }

    private static void testValues() {
        testInputs = new double[100][30];
        testOutputs = new double[100][1];

        int goods = 0;

        for (int i = 0; i < 100; i++) {
            boolean good = Math.random() > 0.4;

            if (!good) {
                int zeroes = 0, ones = 0;

                for (int n = 0; n < 30; n++) {
                    if (ones == 15) {
                        testInputs[i][n] = 0;
                    } else if (zeroes == 15) {
                        testInputs[i][n] = 1;
                    } else if (Math.random() > 0.5) {
                        ones++;
                        testInputs[i][n] = 1;
                    } else {
                        zeroes++;
                        testInputs[i][n] = 0;
                    }
                }

                testOutputs[i][0] = 0;

                for (int n = 4; n < 30; n++) {
                    if ((int) (testInputs[i][n]+testInputs[i][n-1]*10+testInputs[i][n-2]*100+testInputs[i][n-3]*1000+testInputs[i][n-4]*10000) == 10101) {
                        /*
                        System.out.println("AGH");
                        String string = "{";

                        for (int j = 0; j < 29; j++) {
                            string = string + inputs[i][j] + ", ";
                        }
                        string = string + inputs[i][29] + "}";
                        System.out.println(string);*/

                        i--;
                        n = 100;
                    }
                }
            } else {
                goods++;
                int zeroes = 0, ones = 0;

                int start = (int) (Math.random() * 25);

                for (int n = 0; n < start; n++) {
                    if (ones == 12) {
                        testInputs[i][n] = 0;
                    } else if (zeroes == 13) {
                        testInputs[i][n] = 1;
                    } else if (Math.random() > 0.5) {
                        ones++;
                        testInputs[i][n] = 1;
                    } else {
                        zeroes++;
                        testInputs[i][n] = 0;
                    }
                }

                testInputs[i][start] = 1;
                ones++;
                testInputs[i][start+1] = 0;
                zeroes++;
                testInputs[i][start+2] = 1;
                ones++;
                testInputs[i][start+3] = 0;
                zeroes++;
                testInputs[i][start+4] = 1;
                ones++;

                for (int n = start+5; n < 30; n++) {
                    if (ones == 15) {
                        testInputs[i][n] = 0;
                    } else if (zeroes == 15) {
                        testInputs[i][n] = 1;
                    } else if (Math.random() > 0.5) {
                        ones++;
                        testInputs[i][n] = 1;
                    } else {
                        zeroes++;
                        testInputs[i][n] = 0;
                    }
                }

                testOutputs[i][0] = 1;
            }
        }

        for (double[] array : testInputs) {
            System.out.println("AGH");
            String string = "{";

            for (int j = 0; j < 29; j++) {
                string = string + array[j] + ", ";
            }
            string = string + array[29] + "}";
            System.out.println(string);
        }
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
