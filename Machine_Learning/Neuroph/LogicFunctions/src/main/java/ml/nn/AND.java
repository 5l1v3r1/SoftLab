package ml.nn;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

import java.util.Arrays;

/**
 * This sample shows how to create, train, save and load simple Perceptron neural network
 */
public class AND {

    public static void main(String args[]) {

        // create training set (logical AND function)
        DataSet trainingSet = new DataSet(2, 1);
        trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{1}));

        // create perceptron neural network
        NeuralNetwork myPerceptron = new Perceptron(2, 1);

        // learn the training set
        myPerceptron.learn(trainingSet);

        // test perceptron
        System.out.println("Testing trained perceptron");
        testNeuralNetwork(myPerceptron, trainingSet);

        // save trained perceptron
        myPerceptron.save("mySamplePerceptron.nnet");


        // load saved neural network
        NeuralNetwork loadedPerceptron = NeuralNetwork.createFromFile("mySamplePerceptron.nnet");

        // test loaded neural network
        System.out.println("Testing loaded perceptron");
        testNeuralNetwork(loadedPerceptron, trainingSet);
    }

    /**
     * Prints network output for the each element from the specified training set.
     * @param neuralNet neural network
     * @param testSet data set used for testing
     */
    public static void testNeuralNetwork(NeuralNetwork neuralNet, DataSet testSet) {

        for(DataSetRow trainingElement : testSet.getRows()) {
            neuralNet.setInput(trainingElement.getInput());
            neuralNet.calculate();
            double[] networkOutput = neuralNet.getOutput();

            System.out.print("Input: " + Arrays.toString(trainingElement.getInput()) );
            System.out.println(" Output: " + Arrays.toString(networkOutput) );
        }
    }
}