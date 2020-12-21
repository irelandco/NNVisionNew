package NeuralNet;

public class TwoLayerTest {

    public static void main(String[] args) {

        NeuronLayer layer1 = new NeuronLayer(100, 4);
        NeuronLayer layer2 = new NeuronLayer(1, 100);
        NeuronLayer layer3 = new NeuronLayer(1, 10);

        //ThreeLayerNet net = new ThreeLayerNet(layer1, layer2, layer3, 0.9);
        TwoLayerNet net = new TwoLayerNet(layer1, layer2, 0.9);

        double[][] inputs = new double[][] {
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 1}
        };

        double[][] outputs = new double[][] {
                {1},
                {1},
                {0},
                {1},
                {0}
        };

        System.out.println("Training the neural net...");
        net.train(inputs, outputs, 50000);
        System.out.println("Finished training.");

        System.out.println("Layer 1 Weights");
        System.out.println(layer1.weights);


        // 1, 0, 0
        predict(new double[][] {{0, 0, 1, 1}}, net);

        // 0, 1, 0
        predict(new double[][] {{0, 1, 0, 0}}, net);

        // 1, 1, 0
        predict(new double[][] {{1, 1, 0, 0}}, net);
    }

    public static void predict(double[][] testInput, TwoLayerNet net) {
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
