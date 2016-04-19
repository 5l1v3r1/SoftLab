package ml.nn;

/**
 * Created by hdhamee on 4/18/16.
 */
public class AND {

    public static void main(String a[]){
        int training[][] = new int[][]{
                {1,1,1,1},
                {1,1,0,0},
                {1,0,1,0},
                {1,0,0,0}};

        NeuralNetwork net = new NeuralNetwork(training);
        net.training();

        net.testing(new int[]{1,1,1,0});
        net.testing(new int[]{1,0,0,0});
    }


    public static class NeuralNetwork {
        Neuron neurons[] = new Neuron[3];
        int training[][] = new int[4][];
        double alpha = 0.1;

        public NeuralNetwork(int trainingdata[][]) {
            training = trainingdata;
            neurons = new Neuron[3];
            for (int j = 0; j < neurons.length; j++) {
                neurons[j] = new Neuron();
            }
        }

        public void training() {
            int k = 1;

            for (int i = 0; i < training.length; i++) {
                System.out.println("["+i+"]th round " + "w1=" + neurons[1].getWeight() + " w2=" + neurons[2].getWeight() );

                int inputs[] = training[i];
                double output = getNeuralNetOutput(neurons, inputs);

                //Update all the neuron's weight
                for (int j = 0; j < neurons.length; j++) {
                    neurons[j].updateWeight(inputs[inputs.length - 1] * inputs[j] * alpha);
                }

            }
        }

        public double getNeuralNetOutput(Neuron[] neurons, int inputs[]) {
            //get all the outputs and add them together
            double output = 0;
            for (int j = 0; j < neurons.length; j++) {
                output += neurons[j].getOutput(inputs[j]);
            }
            return output;
        }

        public void testing(int[] inputs) {
            System.out.println(getNeuralNetOutput(neurons, inputs));
        }
    }


    // Neuron
    public static class Neuron {
        double weight;

        public Neuron(){
            weight=0;
        }

        public double getOutput(int x){
            return x*weight;
        }

        public void updateWeight(double update){
            weight+=update;
        }

        public double getWeight() {
            return weight;
        }
    }

}
