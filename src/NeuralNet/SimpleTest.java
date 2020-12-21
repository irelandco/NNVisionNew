package NeuralNet;

public class SimpleTest {

    public static void main(String[] args) {

        NeuronLayer layer1 = new NeuronLayer(1, 2);

        OneLayerNet net = new OneLayerNet(layer1);

        double[][] inputs = new double[][] {
                {0, 0},
                {1, 1},
                {1, 0},
                {0, 1}
        };

        double[][] outputs = new double[][] {
                {1},
                {1},
                {0},
                {0}
        };

        System.out.println("Training the neural net...");
        net.train(inputs, outputs, 10);
        System.out.println("Finished training.");

        System.out.println("Layer 1 Weights");
        System.out.println(layer1.weights);


        // 1, 0, 0
        predict(new double[][] {{1, 0}}, net);

        // 0, 1, 0
        predict(new double[][] {{0, 1}}, net);

        // 1, 1, 0
        predict(new double[][] {{1, 1}}, net);
    }

    public static void predict(double[][] testInput, OneLayerNet net) {
        net.think(testInput);

        // then
        System.out.println("Prediction on data "
                + testInput[0][0] + " "
                + testInput[0][1] + " -> "
                + net.getOutput()[0][0] + ", expected -> " + testInput[0][0]);
    }
}