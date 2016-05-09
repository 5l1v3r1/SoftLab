package prediction.neuroph.nn.adaline;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Adaline;

import java.util.Arrays;

/**
 * AdalineNetwork (ADAptive LInear NEuron) is simple two-layer neural network with only input and output layer,
 * having a single output neuron. Output neuron receives input from all input neurons.
 *
 * All neurons in network have linear transfer functions (y = kx+n),
 * and network use Least Mean Squares (LMS) algorithm for learning.
 *
 * This network can be used to recognize patterns, data filtering, or to approximate linear function.
 * Note that this network can be applied only to linear problems.
 *
 * Configuration
 * -------------
 *  - Input Function: WeightedSum (Default)
 *  - Transfer Function: LINEAR(for input neuron) RAMP(for output neuron)
 *  - Learning Rule: LMS
 *  - Network Type: ADALINE
 *  - Neuron Type: BiasNeuron (input neuron:2, output neuron:1, total layers: 2)
 *  - Bias: yes
 *
 *
 * Below example is of AND logic function simulation
 * http://neuroph.sourceforge.net/tutorials/Adaline.html
 *
 *
 * Created by hdhamee on 4/20/16.
 */
public class AdalineNetwork {
    public static void main(String args[]) {

        // create training set (logical AND function)
        DataSet trainingSet = new DataSet(2, 1);
        trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{1}));

        // create perceptron neural network
        NeuralNetwork myPerceptron = new Adaline(2);

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
