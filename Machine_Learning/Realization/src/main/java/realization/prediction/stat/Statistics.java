package realization.prediction.stat;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Uni-variate(x) Statistical Functions Realization
 *  - Sum:Σ(x)
 *  - Mean: Σ(x)/n
 *  - GeometricMean: n√a1*a2*a3*..*an  -- nth root of the product of set of numbers: gives the return on investment
 *  - Max: maximum value among given data
 *  - Min: minimum value among given data
 *  - SumOfSquares: Σx^2
 *  - Median:(n+1)/2
 *  - Standard Deviation:
 *  - Percentile: A percentile rank is the percentage of scores that fall at or below a given score
 *  - Variance:
 *  - Kurtosis: (∑(xi−x¯)^4/N)/s4 −3 -- gives whether data are heavily tailed(outliers) or not. zero means uniform data.
 *  - Skewness: (∑(xi−x¯)^3/N)/s3 -- gives if data looks the same to the left and right of the center point.
 *
 *  NOTE:
 *  ======
 *  "Square root" of some number "A" is a number "X" such that "X" multiplied by itself would be "A".
 *  "Nth root" of a number "X" is a number "R" which, when raised to the power of "N", equals "X" and its denoted like Rⁿ = X.
 *
 * Created by hdhamee on 4/26/16.
 */

public class Statistics {
    public static void main(String[] args) {
        double[] inputArray = new double[]{1,2,3,4,1,10,9,10};
        // Get a DescriptiveStatistics instance
        DescriptiveStatistics stats = new DescriptiveStatistics();

        // Add the data from the array
        for (int i = 0; i < inputArray.length; i++) {
            stats.addValue(inputArray[i]);
        }

        // Compute some statistics
        double sum = stats.getSum();
        double mean = stats.getMean();
        double geometricMean = stats.getGeometricMean();
        double max = stats.getMax();
        double min = stats.getMin();
        double median = stats.getPercentile(50);
        double std = stats.getStandardDeviation();
        double kurtosis = stats.getKurtosis();
        double skewness = stats.getSkewness();
        double percentile = stats.getPercentile(49);
        double statsSumsq = stats.getSumsq();

        System.out.println("Sum: " + sum);
        System.out.println("Mean: " + mean);
        System.out.println("GeometricMean: " + geometricMean);
        System.out.println("Max " + max);
        System.out.println("Min " + min);
        System.out.println("Median " + median);
        System.out.println("STD " + std);
        System.out.println("Kurtosis: " + kurtosis);
        System.out.println("skewness: " + skewness);
        System.out.println("Percentile: " + percentile);
        System.out.println("SumOfSquares: " + statsSumsq);
    }
}
