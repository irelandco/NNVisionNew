package NeuralNet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class WriteExamples {

    static double[][] inputs;
    static double[][] outputs;

    static double[][] testInputs;
    static double[][] testOutputs;

    static int totElements = 0;

    public static void main(String[] args) {
        PrintWriter out = null;

        try {
            out = new PrintWriter("Neural.txt");
        } catch (FileNotFoundException e) {

        }

        inputs = new double[3000][42];
        outputs = new double[3000][1];

        int goods = 0;

        for (int i = 0; i < 3000; i++) {

            System.out.println(i);

            boolean good = Math.random() > 0.3;

            if (!good) {
                int zeroes = 0, ones = 0;

                for (int n = 0; n < 42; n++) {
                    if (ones == 21) {
                        inputs[i][n] = 0;
                    } else if (zeroes == 21) {
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

                for (int n = 6; n < 42; n++) {
                    if ((int) (inputs[i][n]+inputs[i][n-1]*10+inputs[i][n-2]*100+inputs[i][n-3]*1000+inputs[i][n-4]*10000+inputs[i][n-5]*100000+inputs[i][n-6]*1000000) == 1010101) {

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

                int start = (int) (Math.random() * 35);

                for (int n = 0; n < start; n++) {
                    if (ones == 17) {
                        inputs[i][n] = 0;
                    } else if (zeroes == 18) {
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
                inputs[i][start+5] = 0;
                zeroes++;
                inputs[i][start+6] = 1;
                ones++;

                for (int n = start+7; n < 42; n++) {
                    if (ones == 21) {
                        inputs[i][n] = 0;
                    } else if (zeroes == 21) {
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

        if (out != null) {
            for (int i = 0; i < 3000; i++) {
                String string = "";
                for (int j = 0; j < 42; j++) {
                    string = string + (int) inputs[i][j];
                    string = string + " ";
                    totElements++;

                }
                string = string + (int) outputs[i][0];
                totElements++;
                System.out.println("yee " + totElements);

                out.println(string);
            }
        } else {
            System.out.println("NOOO");
        }









        testInputs = new double[100][42];
        testOutputs = new double[100][1];

        for (int i = 0; i < 100; i++) {

            System.out.println(i);

            boolean good = Math.random() > 0.3;

            if (!good) {
                int zeroes = 0, ones = 0;

                for (int n = 0; n < 42; n++) {
                    if (ones == 21) {
                        testInputs[i][n] = 0;
                    } else if (zeroes == 21) {
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

                for (int n = 6; n < 42; n++) {
                    if ((int) (testInputs[i][n]+testInputs[i][n-1]*10+testInputs[i][n-2]*100+testInputs[i][n-3]*1000+testInputs[i][n-4]*10000+testInputs[i][n-5]*100000+testInputs[i][n-6]*1000000) == 1010101) {

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

                int start = (int) (Math.random() * 35);

                for (int n = 0; n < start; n++) {
                    if (ones == 17) {
                        testInputs[i][n] = 0;
                    } else if (zeroes == 18) {
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
                testInputs[i][start+5] = 0;
                zeroes++;
                testInputs[i][start+6] = 1;
                ones++;

                for (int n = start+7; n < 42; n++) {
                    if (ones == 21) {
                        testInputs[i][n] = 0;
                    } else if (zeroes == 21) {
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

        if (out != null) {
            for (int i = 0; i < 100; i++) {
                String string = "";
                for (int j = 0; j < 42; j++) {
                    string = string + (int) testInputs[i][j];
                    string = string + " ";

                    totElements++;
                }
                string = string + (int) testOutputs[i][0];
                totElements++;
                System.out.println("yee " + totElements);

                out.println(string);
            }
        } else {
            System.out.println("NOOO");
        }

        out.close();


        read();
    }



    private static void read() {
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

                System.out.println(i);

                for (int j = 0; j < 42; j++) {
                    inputs[i][j] = Integer.parseInt(inputStream.next());
                    totElements--;
                }
                outputs[i][0] = Integer.parseInt(inputStream.next());
                totElements--;
                System.out.println("yee " + totElements);
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
