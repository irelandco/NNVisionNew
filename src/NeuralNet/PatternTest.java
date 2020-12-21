package NeuralNet;

public class PatternTest {

    public static void main(String[] args) {

        NeuronLayer layer1 = new NeuronLayer(15, 15);
        NeuronLayer layer2 = new NeuronLayer(10, 15);
        NeuronLayer layer3 = new NeuronLayer(1, 10);

        ThreeLayerNet net = new ThreeLayerNet(layer1, layer2, layer3, 0.03);

        double[][] inputs = new double[2000][15];
        double[][] outputs = new double[2000][1];

        int goods = 0;

        for (int i = 0; i < 2000; i++) {
            boolean good = Math.random() > 0.5;

            if (!good) {
                int zeroes = 0, ones = 0;

                for (int n = 0; n < 15; n++) {
                    if (ones == 8) {
                        inputs[i][n] = 0;
                    } else if (zeroes == 7) {
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

                for (int n = 4; n < 15; n++) {
                    int val = (int) (inputs[i][n]+inputs[i][n-1]*10+inputs[i][n-2]*100+inputs[i][n-3]*1000+inputs[i][n-4]*10000);
                    if (val%100==1&&val%10000==101&&val/10000==1) {
                        System.out.println("AGH");
                        String string = "{";

                        for (int j = 0; j < 14; j++) {
                            string = string + inputs[i][j] + ", ";
                        }
                        string = string + inputs[i][14] + "}";
                        System.out.println(string);

                        i--;
                    }
                }
            } else {
                goods++;
                int zeroes = 0, ones = 0;

                int start = (int) (Math.random() * 10);

                for (int n = 0; n < start; n++) {
                    if (ones == 8) {
                        inputs[i][n] = 0;
                    } else if (zeroes == 7) {
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
                inputs[i][start+1] = 0;
                inputs[i][start+2] = 1;
                inputs[i][start+3] = 0;
                inputs[i][start+4] = 1;

                for (int n = start+5; n < 15; n++) {
                    if (ones == 8) {
                        inputs[i][n] = 0;
                    } else if (zeroes == 7) {
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

        System.out.println("Training the neural net...");
        net.train(inputs, outputs, 50000);
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
