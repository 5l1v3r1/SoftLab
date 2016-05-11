package realization.prediction.neuroph.imagerecognition;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * - Face has about 80 characteristic parameters some of them are:width of nose, space between eyes, high of eyehole, shape of the zygotic bone and jaw width.
 * - Face specification is made of these parameters and inserted in database as a representation of that person.
 * - There is a process of face recognition where system has a database with pictures taken from different angles.
 * - In this case, system first recognizes a position of face at picture, and with this information compares face with others from database in similar position.
 *
 * Goal:
 * The neural network will take some picture's parameters for input and try to predict a person how has this characteristic.
 *
 * Here we only take 8 characteristics parameter of a person's face:
 *   J1 - distance between middles of the eyes
     J2 - distance between middle of the left eyes and middle point of mouth
     J3 - distance between middle of the right eyes and middle point of mouth
     J4 - distance between middle of the left eyes and middle point of nose
     J5 - distance between middle of the rigth eyes and middle point of nose
     J6 - distance between middle point of mouth and middle point of nose
     J7 - distance of middle point of J1 and middle of nose
     J8 - width of nose
 *
 *
 *
 * Created by hdhamee on 5/11/16.
 */
public class FaceDetection {
    public static void main(String[] args) {

        String trainingSetFileName = "FaceRecognition.txt";
        int inputsCount = 8;
        int outputsCount = 15;

        System.out.println("Running Face Recognition Sample");
        System.out.println("Using training set " + trainingSetFileName);

        // create training set
        DataSet trainingSet = null;
        try {
            trainingSet = TrainingSetImport.importFromFile(trainingSetFileName, inputsCount, outputsCount, ",");
        }catch(FileNotFoundException ex) {
            System.out.println("File not found!");
        }catch(IOException io) {
            System.out.println("Error reading file or bad number format!");
        }


        // create multi layer perceptron
        System.out.println("Creating neural network");
        MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 8, 5, 15);

        // set learning parametars
        MomentumBackpropagation learningRule = (MomentumBackpropagation) neuralNet.getLearningRule();
        learningRule.setLearningRate(0.2);
        learningRule.setMomentum(0.7);

        // learn the training set
        System.out.println("Training neural network...");
        neuralNet.learn(trainingSet);
        System.out.println("Done!");

        // test perceptron
        System.out.println("Testing trained neural network");
        testFaceRecognition(neuralNet, trainingSet);

    }

    public static void testFaceRecognition(NeuralNetwork nnet, DataSet dset) {

        for (DataSetRow trainingElement : dset.getRows()) {

            nnet.setInput(trainingElement.getInput());
            nnet.calculate();
            double[] networkOutput = nnet.getOutput();
            System.out.print("Input: " + Arrays.toString(trainingElement.getInput()));
            System.out.println(" Output: " + Arrays.toString(networkOutput));
        }

    }
}
