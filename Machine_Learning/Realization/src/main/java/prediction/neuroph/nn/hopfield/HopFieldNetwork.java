package prediction.neuroph.nn.hopfield;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Hopfield;

import java.util.Arrays;

/**
 * The Hopfield network is a recurrent neural network in which all connections are symmetric.
 * Hopfield nets serve as content-addressable (associative) memory systems with binary threshold units.
 *
 * They are guaranteed to converge to a local minimum, but convergence to one of the stored.
 *
 * The simplest use of this network is recognition of characters “T” and “H”, which we will use in our training set for
 * this network. It uses unsupervised learning method.
 *
 *
 * Configuration
 * -------------
 *  - Input Function: WeightedSum
 *  - Transfer Function: STEP
 *  - Learning Rule: BinaryHebbianLearning
 *  - Network Type: HOPFIELD
 *  - Neuron Type: InputOutputNeuron (input neuron:2, output neuron:1, total layers: 2)
 *  - Bias: yes
 *
 * Below example is of H and T character recognition
 * http://neuroph.sourceforge.net/tutorials/Hopfield.html
 *
 *
 * Created by hdhamee on 4/20/16.
 */
public class HopFieldNetwork {
    public static void main(String args[]) {

        // create training set (H and T letter in 3x3 grid)
        DataSet trainingSet = new DataSet(9);
        trainingSet.addRow(new DataSetRow(new double[]{1, 0, 1, 1, 1, 1, 1, 0, 1})); // H letter
        trainingSet.addRow(new DataSetRow(new double[]{1, 1, 1, 0, 1, 0, 0, 1, 0})); // T letter

        // create hopfield network
        Hopfield myHopfield = new Hopfield(9);
        // learn the training set
        myHopfield.learn(trainingSet);

        // test hopfield network
        System.out.println("Testing network");

        // add one more 'incomplete' H pattern for testing - it will be recognized as H
        trainingSet.addRow(new DataSetRow(new double[]{1, 0, 0, 1, 0, 1, 1, 0, 1})); // incomplete H letter

        for(DataSetRow dataRow : trainingSet.getRows()) {

            myHopfield.setInput(dataRow.getInput());
            myHopfield.calculate();
            myHopfield.calculate();
            double[ ] networkOutput = myHopfield.getOutput();

            System.out.print("Input: " + Arrays.toString(dataRow.getInput()) );
            System.out.println(" Output: " + Arrays.toString(networkOutput) );

        }

    }


}
