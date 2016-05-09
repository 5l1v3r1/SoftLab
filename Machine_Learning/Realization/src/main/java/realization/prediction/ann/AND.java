package realization.prediction.nn;

/**
 * Using Hebbian Learning, the AND logic function simulation
 *
 * Created by hdhamee on 4/18/16.
 */
public class AND {

    public static void main(String a[]){
        //prepare training data
        int trainingData[][] = new int[][]{
                //bias,input1,input2,output
                {1,1,1,1},
                {1,1,-1,-1},
                {1,-1,1,-1},
                {1,-1,-1,-1}};

        // create neural net
        NeuralNetwork net = new NeuralNetwork(trainingData);

        //train the net
        net.train();

        //test trained net
        net.test(new int[]{1, 1, 1, 0});
        net.test(new int[]{1, 1, -1, 0});
        net.test(new int[]{1, -1, 1, 0});

        //save net
        System.out.println("Serialize net");

        //use it for other inputs
        System.out.println("Write code to read net and use it elsewhere");
    }


    public static class NeuralNetwork {
        Neuron neuron = new Neuron();
        int training[][] = new int[4][];

        public NeuralNetwork(int trainingdata[][]) {
            training = trainingdata;
            neuron = new Neuron();
        }

        public void train() {
            System.out.println("[initially] " + "b=" + neuron.getWeights()[0] + " w1=" + neuron.getWeights()[1] + " w2=" + neuron.getWeights()[2]);
            for (int i = 0; i < training.length; i++) {
                //Update all the neuron weight
                neuron.updateWeights(training[i]);

                System.out.println("[" + i + "]th round " + "b=" + neuron.getWeights()[0] + " w1=" + neuron.getWeights()[1] + " w2=" + neuron.getWeights()[2]);
            }
        }

        public double getNeuralNetOutput(Neuron neuron, int inputs[]) {
            //get all the outputs and add them together
            return neuron.getWeightedSum(inputs);
        }

        public void test(int[] inputs) {
            System.out.println(getNeuralNetOutput(neuron, inputs) > 0 ? 1:-1);
        }
    }


    // Neuron
    public static class Neuron {
        double[] weights;

        public Neuron(){
            weights = new double[3];
        }

        public double getWeightedSum(int[] inputs){
            int output = 0;
            for (int j = 0; j < inputs.length - 1; j++) {
                output += (inputs[j] * weights[j]);
            }
            return output;
        }

        public void updateWeights(int[] inputs){
            for (int j = 0; j < inputs.length-1; j++) {
                weights[j] += (inputs[inputs.length-1] * inputs[j]);
            }
        }

        public double[] getWeights(){
            return weights;
        }
    }
}
