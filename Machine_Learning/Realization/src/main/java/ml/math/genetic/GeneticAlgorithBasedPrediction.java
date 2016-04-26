package ml.math.genetic;

import org.apache.commons.math3.genetics.*;

import java.util.LinkedList;
import java.util.List;

/**
 * https://commons.apache.org/proper/commons-math/userguide/genetics.html
 * Created by hdhamee on 4/26/16.
 */
public class GeneticAlgorithBasedPrediction {
    // parameters for the GA
    private static final int DIMENSION = 50;
    private static final int POPULATION_SIZE = 50;
    private static final int TOURNAMENT_ARITY = 5;
    private static final int NUM_GENERATIONS = 50;
    private static final double ELITISM_RATE = 0.2;

    public static void main(String[] args) {
        // initialize a new genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(new OnePointCrossover<Integer>(),1,new RandomKeyMutation(),0.10,
                new TournamentSelection(TOURNAMENT_ARITY));

        // initial population
        Population initial = getInitialPopulation();

        // stopping condition
        StoppingCondition stopCond = new FixedGenerationCount(NUM_GENERATIONS);

        // run the algorithm
        Population finalPopulation = ga.evolve(initial, stopCond);

        // best chromosome from the final population
        Chromosome bestFinal = finalPopulation.getFittestChromosome();
    }

    /**
     * Initializes a random population.
     */
    private static ElitisticListPopulation getInitialPopulation() {
        List<Chromosome> popList = new LinkedList();

        for (int i=0; i<POPULATION_SIZE; i++) {
            BinaryChromosome randChrom = new FindOnes(BinaryChromosome.randomBinaryRepresentation(DIMENSION));
            popList.add(randChrom);
        }
        return new ElitisticListPopulation(popList, popList.size(), ELITISM_RATE);
    }


    /**
     * Chromosomes represented by a binary chromosome.
     *
     * The goal is to set all bits (genes) to 1.
     */
    private static class FindOnes extends BinaryChromosome {

        public FindOnes(List<Integer> representation) {
            super(representation);
        }

        /**
         * Returns number of elements != 0
         */
        public double fitness() {
            int num = 0;
            for (int val : this.getRepresentation()) {
                if (val != 0)
                    num++;
            }
            // number of elements >= 0
            return num;
        }

        @Override
        public AbstractListChromosome<Integer> newFixedLengthChromosome(List chromosomeRepresentation) {
            return new FindOnes(chromosomeRepresentation);
        }

    }
}
