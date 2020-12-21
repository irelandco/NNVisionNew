package NeuralNet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadExamples {

    static double[][] inputs;
    static double[][] outputs;

    static double[][] testInputs;
    static double[][] testOutputs;

    public static void main(String[] args) {

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
                outputs[i][0] = Integer.parseInt(inputStream.next());
            }

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 42; j++) {
                    testInputs[i][j] = Integer.parseInt(inputStream.next());
                }
                testOutputs[i][0] = Integer.parseInt(inputStream.next());
            }
        }
    }
}
