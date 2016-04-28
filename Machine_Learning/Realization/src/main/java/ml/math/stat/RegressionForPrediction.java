package ml.math.stat;

import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 * Linear regression analysis is a powerful technique used for predicting the unknown value of a variable
 * from the known value of another variable.
 * Formula For Linear Regression: Y = a + bX
 *
 * For example:
 * Age of a human being and maturity are related variables.
 * Then linear regression analyses can predict level of maturity given age of a human being.
 *
 *
 * Created by hdhamee on 4/28/16.
 */
public class RegressionForPrediction {
    public static void main(String[] args) {
        // Linear Regression y=ax+b
        double[] xinput = new double[]{1,2,3,4,5,6,7,8};
        double[] youtput = new double[]{2,3,4,5,6,7,8,9};

        SimpleRegression regression = new SimpleRegression();
        for (int i = 0;i<xinput.length;i++ ){
            regression.addData(xinput[i], youtput[i]);
        }

        // displays intercept of regression line
        System.out.println("Intercept: " + regression.getIntercept());

        // displays slope of regression line
        System.out.println("Slope: " + regression.getSlope());

        // displays slope standard error
        System.out.println("Slope Error: " + regression.getSlopeStdErr());


        // displays predicted y value for x = 1.5
        System.out.println("f(9)=" + regression.predict(9));



    }
}
