package realization.prediction.neuroph.nn.perceptron;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

import java.util.Arrays;

/**
 * PerceptronNetwork is a simple two layer neural network with several neurons in input layer, and one or more neurons in output layer.
 *
 * All neurons use step transfer function and network can use LMS based learning algorithm such as PerceptronNetwork Learning or Delta Rule.
 *
 * This network can be used as a linear classifier, and it can only be applied to linear separable problems.
 *
 * The perceptron was suggest by  Rosenblatt in 1958.
 * It uses an iterative learning  procedure which can be proven  to converge to the correct
 * weights for linearly separable  data.
 *
 * Weights are changed only when an error  occurs.
 *       wi(new) = wi(old) + xiαt
 *       t is either +1 or -1;  α is the learning rate [0.1].
 *
 *
 * Configuration
 * -------------
 *  - Input Function: WeightedSum
 *  - Transfer Function: STEP
 *  - Learning Rule: BinaryDeltaRule
 *  - Network Type: PERCEPTRON
 *  - Neuron Type: ThresholdNeuron/Neuron (output/input neuron) (input neuron:2, output neuron:1, total layers: 2)
 *  - Bias: no
 *
 * Below example is of AND logic function simulation
 * http://neuroph.sourceforge.net/tutorials/Perceptron.html
 *
 *
 * Created by hdhamee on 4/20/16.
 */
public class PerceptronNetwork {

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