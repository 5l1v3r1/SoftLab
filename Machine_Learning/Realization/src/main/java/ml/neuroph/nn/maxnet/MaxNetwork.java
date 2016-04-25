package ml.neuroph.nn.maxnet;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MaxNet;

import java.util.Arrays;

/**
 * Maxnet is an implementation of a maximum-finding function. With each iteration, the neurons’ activations
 * will decrease until only one neuron remains active.
 *
 * The “winner” is neuron that had the greatest output.
 *
 * For this type of neural network there is no training process.
 *
 * http://neuroph.sourceforge.net/tutorials/MaxNet.html
 *
 *
 * Created by hdhamee on 4/20/16.
 */
public class MaxNetwork {
    public static void main(String args[]) {

        // create training set (max function)
        DataSet trainingSet = new DataSet(4);
        trainingSet.addRow(new double[]{0.1, 0.2, 0.4, 0.3});

        // create perceptron neural network
        NeuralNetwork myPerceptron = new MaxNet(4);

        // save trained perceptron
        myPerceptron.save("mySamplePerceptron.nnet");


        // load saved neural network
        // NeuralNetwork loadedPerceptron = NeuralNetwork.createFromFile("mySamplePerceptron.nnet");

        // test loaded neural network
        System.out.println("Testing loaded perceptron");
        testNeuralNetwork(myPerceptron, trainingSet);
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
