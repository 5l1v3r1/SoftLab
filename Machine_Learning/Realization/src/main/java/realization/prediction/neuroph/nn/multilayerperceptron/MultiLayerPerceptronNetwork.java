package realization.prediction.neuroph.nn.multilayerperceptron;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.events.LearningEventType;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.Arrays;


/**
 * Multi Layer perceptron (MLP) is a feedforward neural network with one or more layers between input and output layer.
 * <p>
 * Feedforward means that data flows in one direction from input to output layer (forward).
 * <p>
 * This type of network is trained with the backpropagation learning algorithm. MLPs are widely used for pattern classification,
 * recognition, realization.prediction and approximation.
 * <p>
 * Multi Layer PerceptronNetwork can solve problems which are not linearly separable.
 * <p>
 * * Configuration
 * -------------
 * - Input Function: WeightedSum
 * - Transfer Function: TANH
 * - Learning Rule: MomentumBackpropagation
 * - Network Type: MULTI_LAYER_PERCEPTRON
 * - Neuron Type: BiasNeuron (input neuron:2, hidden:3, output neuron:1, total layers: 3)
 * - Bias: yes
 * <p>
 * <p>
 * This sample shows how to create, train, save and load simple XOR logic function for the MultiLayerPerceptronNetwork.
 * http://neuroph.sourceforge.net/tutorials/MultiLayerPerceptron.html
 * <p>
 * <p>
 * This sample shows basics of Neuroph API.
 */
public class MultiLayerPerceptronNetwork implements LearningEventListener {

    public static void main(String[] args) {
        new MultiLayerPerceptronNetwork().run();
    }

    /**
     * Prints network output for the each element from the specified training set.
     */
    public static void testNeuralNetwork(NeuralNetwork neuralNet, DataSet testSet) {

        for (DataSetRow testSetRow : testSet.getRows()) {
            neuralNet.setInput(testSetRow.getInput());
            neuralNet.calculate();
            double[] networkOutput = neuralNet.getOutput();

            System.out.print("Input: " + Arrays.toString(testSetRow.getInput()));
            System.out.println(" Output: " + Arrays.toString(networkOutput));
        }
    }

    public void run() {
        // create training set (logical XOR function)
        DataSet trainingSet = new DataSet(2, 1);
        trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{0}));

        // create multi layer perceptron
        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.TANH, 2, 3, 1);

        // enable batch if using MomentumBackpropagation
        if (myMlPerceptron.getLearningRule() instanceof MomentumBackpropagation)
            ((MomentumBackpropagation) myMlPerceptron.getLearningRule()).setBatchMode(true);

        LearningRule learningRule = myMlPerceptron.getLearningRule();
        learningRule.addListener(this);

        // learn the training set
        System.out.println("Training neural network...");
        myMlPerceptron.learn(trainingSet);

        // test perceptron
        System.out.println("Testing trained neural network");
        testNeuralNetwork(myMlPerceptron, trainingSet);

        // save trained neural network
        myMlPerceptron.save("myMlPerceptron.nnet");

        // load saved neural network
        NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile("myMlPerceptron.nnet");

        // test loaded neural network
        System.out.println("Testing loaded neural network");
        testNeuralNetwork(loadedMlPerceptron, trainingSet);
    }

    public void handleLearningEvent(LearningEvent event) {
        BackPropagation bp = (BackPropagation) event.getSource();
        if (event.getEventType() != LearningEventType.LEARNING_STOPPED)
            System.out.println(bp.getCurrentIteration() + ". iteration : " + bp.getTotalNetworkError());
    }

}